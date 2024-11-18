package com.wonder.blog.web.admin.controller;

import com.wonder.blog.util.web.PathUtils;
import com.wonder.blog.web.admin.req.FileSpaceReq;
import com.wonder.blog.web.admin.vo.FileVO;
import com.wonder.blog.web.admin.vo.UploadVO;
import com.wonder.common.annotation.OperationLogger;
import com.wonder.common.resp.PageResponse;
import com.wonder.common.resp.Response;
import com.wonder.common.utils.env.env.EnvConstant;
import com.wonder.common.utils.env.env.Environment;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yond
 * @date 11/16/2024
 * @description file storage
 */
@RestController
@RequestMapping("/admin/file")
public class FileAdminController {

    private static final Logger log = LoggerFactory.getLogger(FileAdminController.class);

    private static final String PREFIX = "/file/";

    @OperationLogger("上传文件")
    @RequestMapping("/upload")
    public Response<UploadVO> upload(@RequestParam MultipartFile file, @RequestParam String filePath) {
        Assert.hasText(filePath, "文件路径为空");
        Assert.notNull(file, "文件为空");
        try {
            String path = PREFIX + filePath;
            File target = Paths.get(path).toFile();
            File parentDir = target.getParentFile();
            if (!parentDir.exists()) {
                Assert.isTrue(parentDir.mkdirs(), "父目录创建失败");
            }
            if (target.exists()) {
                return Response.<UploadVO>custom().setFailure("文件路径已存在");
            }
            file.transferTo(target);
            UploadVO uploadVO = new UploadVO();
            String domain = Environment.getValue(EnvConstant.BLOG_API_DOMAIN, Function.identity());
            uploadVO.setUrl(domain + "admin/file/download/" + filePath);
            return Response.success(uploadVO);
        } catch (IOException e) {
            log.error("File upload failed", e);
            return Response.<UploadVO>custom().setFailure("文件上传失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during file upload", e);
            return Response.<UploadVO>custom().setFailure("文件上传系统错误: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filePath}/**")
    public ResponseEntity<Resource> download(HttpServletRequest request, @PathVariable String filePath) {
        String requestURL = request.getRequestURI();
        String fullPath = requestURL.substring(requestURL.indexOf("/download/") + "/download/".length());
        String path = PREFIX + fullPath;
        Path file = Path.of(path);

        if (!Files.exists(file)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }

        try {
            String mimeType = Files.probeContentType(file);
            mimeType = mimeType != null ? mimeType : MediaType.APPLICATION_OCTET_STREAM_VALUE;

            Resource resource = new PathResource(file);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .contentLength(Files.size(file))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                                    .build().toString())
                    .body(resource);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while processing file", e);
        }
    }

    @PostMapping("/space")
    public Response<List<FileVO>> space(@RequestBody FileSpaceReq req) {
        String basePath = req.getPath();
        File directory = new File(PathUtils.normalizePath(PREFIX, basePath));

        if (!directory.exists() || !directory.isDirectory()) {
            return Response.fail("Invalid directory path");
        }

        String domain = Environment.getValue(EnvConstant.BLOG_API_DOMAIN, Function.identity());
        List<FileVO> files = new ArrayList<>();
        File[] fileList = directory.listFiles();

        if (fileList != null) {
            List<File> collect = Arrays.stream(fileList).sorted(Comparator.comparing(File::isDirectory).reversed().thenComparing(File::getName))
                    .toList();
            for (File file : collect) {
                FileVO vo = new FileVO();
                vo.setFolder(file.isDirectory());
                vo.setName(file.getName());
                if (!file.isDirectory()) {
                    vo.setUrl(PathUtils.normalizePath(domain, "admin/file/download", basePath, file.getName()));
                }
                files.add(vo);
            }
        }
        return Response.success(files);
    }

}

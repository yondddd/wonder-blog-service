package com.wonder.blog.web.admin.controller;

import com.wonder.blog.entity.BookshelfDO;
import com.wonder.blog.service.BookshelfService;
import com.wonder.blog.web.admin.convert.BookshelfConverter;
import com.wonder.blog.web.admin.convert.CategoryConverter;
import com.wonder.blog.web.admin.req.BookshelfDelReq;
import com.wonder.blog.web.admin.req.BookshelfDetailReq;
import com.wonder.blog.web.admin.req.BookshelfPageReq;
import com.wonder.blog.web.admin.req.CategoryDelReq;
import com.wonder.blog.web.admin.vo.BookshelfVO;
import com.wonder.blog.web.admin.vo.CategoryVO;
import com.wonder.common.annotation.OperationLogger;
import com.wonder.common.resp.PageResponse;
import com.wonder.common.resp.Response;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yond
 * @date 11/16/2024
 * @description book manager
 */
@RestController
@RequestMapping("/admin/book")
public class BookshelfAdminController {

    @Resource
    private BookshelfService bookshelfService;

    @PostMapping("/allAuthor")
    public Response<List<String>> allAuthor() {
        return Response.success(bookshelfService.allAuthor());
    }

    @PostMapping("/page")
    public PageResponse<List<BookshelfVO>> page(@RequestBody BookshelfPageReq req) {
        Pair<Integer, List<BookshelfDO>> pair = bookshelfService.page(req.getPageNo(), req.getPageSize(), req.getAuthorName(), req.getBookName());
        List<BookshelfVO> data = pair.getRight().stream().map(BookshelfConverter::do2vo).toList();
        return PageResponse.<List<BookshelfVO>>custom().setSuccess().setData(data).setTotal(pair.getLeft());
    }

    @PostMapping("/detail")
    public Response<BookshelfVO> detail(@RequestBody BookshelfDetailReq req) {
        BookshelfDO exist = bookshelfService.getById(req.getId());
        Assert.notNull(exist, "书籍不存在" + req.getId());
        return Response.success(BookshelfConverter.do2vo(exist));
    }

    @OperationLogger("新增书籍")
    @PostMapping("/save")
    public Response<Boolean> save(@RequestBody BookshelfVO req) {
        bookshelfService.insertSelective(BookshelfConverter.vo2do(req));
        return Response.success();
    }

    @OperationLogger("更新书籍")
    @PostMapping("/update")
    public Response<Boolean> updateCategory(@RequestBody BookshelfVO req) {
        bookshelfService.updateSelective(BookshelfConverter.vo2do(req));
        return Response.success();
    }

    @OperationLogger("删除书籍")
    @PostMapping("/del")
    public Response<Boolean> del(@RequestBody BookshelfDelReq req) {
        bookshelfService.deleteById(req.getId());
        return Response.success();
    }

}

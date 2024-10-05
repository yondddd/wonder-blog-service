package com.wonder.blog.service.impl;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.wonder.blog.cache.local.LocalCache;
import com.wonder.blog.entity.BlogDO;
import com.wonder.blog.entity.CommentDO;
import com.wonder.blog.mapper.CommentMapper;
import com.wonder.blog.service.BlogService;
import com.wonder.blog.service.CommentService;
import com.wonder.blog.service.FriendService;
import com.wonder.blog.service.SiteConfigService;
import com.wonder.blog.web.admin.dto.CommentDTO;
import com.wonder.blog.web.admin.dto.FriendConfigDTO;
import com.wonder.common.constant.CommonConstant;
import com.wonder.common.constant.SiteConfigConstant;
import com.wonder.common.enums.CommentOpenStateEnum;
import com.wonder.common.enums.CommentPageEnum;
import com.wonder.common.exception.PersistenceException;
import com.wonder.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 博客评论业务层实现
 * @Author: Yond
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private SiteConfigService siteConfigService;
    @Resource
    private FriendService friendService;
    @Resource
    private BlogService blogService;


    private final LoadingCache<String, List<CommentDO>> cache = LocalCache.buildCache(1, new CacheLoader<>() {
        @Override
        public @Nullable List<CommentDO> load(String s) {
            return commentMapper.listAll();
        }
    });

    public List<CommentDO> listAll() {
        return cache.get("listAll");
    }

    private Map<Long, List<CommentDO>> allToMap(List<CommentDO> list) {
        return list.stream().collect(Collectors.groupingBy(CommentDO::getParentId));
    }

    @Override
    public Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize) {
        List<CommentDO> allData = this.listAll();
        Map<Long, List<CommentDO>> map = this.allToMap(allData);
        List<CommentDO> root = map.get(CommonConstant.ROOT_ID);
        root = root == null ? new ArrayList<>() : root;
        root = root.stream().filter(x -> (page == null || page.getId().equals(x.getPage()))
                && (blogId == null || blogId.equals(x.getBlogId()))).toList();
        if (CollectionUtils.isEmpty(root)) {
            return Pair.of(0, Collections.emptyList());
        }
        List<CommentDO> pageList = PageUtil.pageList(root, pageNo, pageSize);
        return Pair.of(root.size(), buildTree(pageList, map));
    }

    @Override
    public CommentDO getById(Long id) {
        return this.listAll().stream()
                .filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSelective(CommentDO comment) {
        commentMapper.updateSelective(comment);
        cache.invalidateAll();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSelective(CommentDO comment) {
        if (commentMapper.insertSelective(comment) != 1) {
            throw new PersistenceException("评论失败");
        }
        cache.invalidateAll();
    }

    @Override
    public CommentOpenStateEnum getPageCommentStatus(Integer page, Long blogId) {
        CommentPageEnum pageEnum = CommentPageEnum.getByValue(page);
        if (pageEnum == null) {
            return CommentOpenStateEnum.NOT_FOUND;
        }
        // 博客页面
        if (CommentPageEnum.BLOG.equals(pageEnum)) {
            BlogDO blog = blogService.getBlogById(blogId);
            if (blog == null) {
                return CommentOpenStateEnum.NOT_FOUND;
            }
            if (!blog.getPublished()) {
                return CommentOpenStateEnum.NOT_FOUND;
            }
            if (!blog.getCommentEnabled()) {
                return CommentOpenStateEnum.CLOSE;
            }
            if (StringUtils.isNotBlank(blog.getPassword())) {
                return CommentOpenStateEnum.PASSWORD;
            }
            return CommentOpenStateEnum.OPEN;
        }
        //友链页面
        if (CommentPageEnum.FRIEND.equals(pageEnum)) {
            FriendConfigDTO friendConfig = friendService.getFriendConfig();
            if (friendConfig.getCommentEnabled()) {
                return CommentOpenStateEnum.OPEN;
            }
        }
        //关于我页面
        if (CommentPageEnum.ABOUT.equals(pageEnum)) {
            String value = siteConfigService.getValue(SiteConfigConstant.ABOUT_COMMENT_ENABLED);
            if (BooleanUtils.toBoolean(value)) {
                return CommentOpenStateEnum.OPEN;
            }
        }
        return CommentOpenStateEnum.CLOSE;
    }

    @Override
    public Pair<Integer, List<CommentDTO>> viewPageBy(Integer page, Long blogId, Integer pageNo, Integer pageSize) {
        // 删除和隐藏的不返回
        List<CommentDO> allData = this.listAll();
        Map<Long, List<CommentDO>> map = this.allToMap(allData);
        Map<Long, String> idToNickName = allData.stream().collect(Collectors.toMap(CommentDO::getId, CommentDO::getNickname));
        List<CommentDO> root = map.get(CommonConstant.ROOT_ID);
        root = root == null ? new ArrayList<>() : root;
        root = root.stream().filter(x -> (page == null || page.equals(x.getPage()))
                && (blogId == null || blogId.equals(x.getBlogId()))).toList();
        if (CollectionUtils.isEmpty(root)) {
            return Pair.of(0, Collections.emptyList());
        }
        List<CommentDO> pageList = PageUtil.pageList(root, pageNo, pageSize);
        return Pair.of(root.size(), buildViewTree(pageList, map, idToNickName));
    }

    public List<CommentDTO> buildTree(List<CommentDO> root, Map<Long, List<CommentDO>> map) {
        List<CommentDTO> result = new ArrayList<>();
        for (CommentDO commentDO : root) {
            CommentDTO commentDTO = convertToDTO(commentDO);
            List<CommentDO> children = map.get(commentDO.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                commentDTO.setReply(buildTree(children, map));
            }
            result.add(commentDTO);
        }
        return result;
    }


    public List<CommentDTO> buildViewTree(List<CommentDO> root, Map<Long, List<CommentDO>> map, Map<Long, String> idToNickName) {
        List<CommentDTO> result = new ArrayList<>();
        for (CommentDO commentDO : root) {
            if (!commentDO.getPublished()) {
                continue;
            }
            CommentDTO commentDTO = convertToDTO(commentDO);
            commentDTO.setParentNickname(idToNickName.get(commentDTO.getParentId()));
            List<CommentDO> children = map.get(commentDO.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                commentDTO.setReply(buildViewTree(children, map, idToNickName));
            }
            result.add(commentDTO);
        }
        return result;
    }

    private CommentDTO convertToDTO(CommentDO commentDO) {
        CommentDTO dto = new CommentDTO();
        dto.setId(commentDO.getId());
        dto.setPage(commentDO.getPage());
        dto.setBlogId(commentDO.getBlogId());
        dto.setParentId(commentDO.getParentId());
        dto.setNickname(commentDO.getNickname());
        dto.setEmail(commentDO.getEmail());
        dto.setContent(commentDO.getContent());
        dto.setAvatar(commentDO.getAvatar());
        dto.setWebsite(commentDO.getWebsite());
        dto.setIp(commentDO.getIp());
        dto.setPublished(commentDO.getPublished());
        dto.setAdminComment(commentDO.getAdminComment());
        dto.setNotice(commentDO.getNotice());
        dto.setQq(commentDO.getQq());
        dto.setStatus(commentDO.getStatus());
        dto.setCreateTime(commentDO.getCreateTime());
        return dto;
    }

}

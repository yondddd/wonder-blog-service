package com.yond.blog.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.yond.blog.cache.local.LocalCache;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.CommentDO;
import com.yond.blog.mapper.CommentMapper;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CommentService;
import com.yond.blog.service.FriendService;
import com.yond.blog.service.SiteConfigService;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.blog.web.blog.admin.dto.FriendConfigDTO;
import com.yond.common.constant.CommonConstant;
import com.yond.common.constant.SiteConfigConstant;
import com.yond.common.enums.CommentOpenStateEnum;
import com.yond.common.enums.CommentPageEnum;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 博客评论业务层实现
 * @Author: Yond
 * @Date: 2020-08-03
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
    
    
    private final Cache<String, List<CommentDO>> cache = LocalCache.buildCache(1, new CacheLoader<>() {
        @Override
        public @Nullable List<CommentDO> load(String s) throws Exception {
            return commentMapper.listAll();
        }
    });
    
    public List<CommentDO> listAll() {
        return cache.getIfPresent("listAll");
    }
    
    private Map<Long, List<CommentDO>> allToMap(List<CommentDO> list) {
        return list.stream().collect(Collectors.groupingBy(CommentDO::getParentId));
    }
    
    @Override
    public Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize) {
        List<CommentDO> allData = this.listAll();
        Map<Long, List<CommentDO>> map = this.allToMap(allData);
        List<CommentDO> root = map.get(CommonConstant.ROOT_ID);
        root = root.stream().filter(x -> (page == null || page.getId().equals(x.getPage()))
                && (blogId == null || blogId.equals(x.getBlogId()))).toList();
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
        if (commentMapper.updateSelective(comment) != 1) {
            throw new PersistenceException("评论修改失败");
        }
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSelective(CommentDO comment) {
        if (commentMapper.insertSelective(comment) != 1) {
            throw new PersistenceException("评论失败");
        }
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
            String value = siteConfigService.getValue(SiteConfigConstant.COMMENT_ENABLED);
            if (BooleanUtils.toBoolean(value)) {
                return CommentOpenStateEnum.OPEN;
            }
        }
        return CommentOpenStateEnum.CLOSE;
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

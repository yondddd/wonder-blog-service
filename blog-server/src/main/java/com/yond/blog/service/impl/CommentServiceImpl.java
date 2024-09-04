package com.yond.blog.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.yond.blog.cache.local.LocalCache;
import com.yond.blog.entity.CommentDO;
import com.yond.blog.mapper.CommentMapper;
import com.yond.blog.service.CommentService;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.blog.web.blog.view.vo.PageComment;
import com.yond.common.enums.CommentPageEnum;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: 博客评论业务层实现
 * @Author: Naccl
 * @Date: 2020-08-03
 */
@Service
public class CommentServiceImpl implements CommentService {
    
    @Resource
    private CommentMapper commentMapper;
    
    private final Cache<String, List<CommentDO>> cache = LocalCache.buildCache(1, new CacheLoader<>() {
        @Override
        public @Nullable List<CommentDO> load(String s) throws Exception {
            return commentMapper.listAll();
        }
    });
    
    private List<CommentDO> listAll() {
        return cache.getIfPresent("listAll");
    }
    
    private Map<Long, CommentDO> allToMap(List<CommentDO> list) {
        return list.stream().collect(Collectors.toMap(CommentDO::getId, Function.identity()));
    }
    
    
    @Override
    public Pair<Integer, List<CommentDTO>> pageBy(CommentPageEnum page, Long blogId, Integer pageNo, Integer pageSize) {
        List<CommentDO> allData = this.listAll();
        Map<Long, CommentDO> allMap = this.allToMap(allData);
        List<CommentDO> filter = allData.stream().filter(x -> (page == null || page.getId().equals(x.getPage()))
                && (blogId == null || blogId.toString().equals(x.getBusinessKey()))).toList();
        List<CommentDO> page = PageUtil.pageList(filter, pageNo, pageSize);
        return Pair.of(allData.size(), );
    }
    
    @Override
    public List<CommentDO> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<CommentDO> comments = commentMapper.getListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (CommentDO c : comments) {
            //递归查询子评论及其子评论
            List<CommentDO> replyComments = getListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }
    
    @Override
    public List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c : comments) {
            List<PageComment> tmpComments = new ArrayList<>();
            getReplyComments(tmpComments, c.getReplyComments());
            //对于两列评论来说，按时间顺序排列应该比树形更合理些
            //排序一下
            Comparator<PageComment> comparator = Comparator.comparing(PageComment::getCreateTime);
            tmpComments.sort(comparator);
            
            c.setReplyComments(tmpComments);
        }
        return comments;
    }
    
    @Override
    public CommentDO getCommentById(Long id) {
        CommentDO comment = commentMapper.getCommentById(id);
        if (comment == null) {
            throw new PersistenceException("评论不存在");
        }
        return comment;
    }
    
    /**
     * 将所有子评论递归取出到一个List中
     *
     * @param comments
     */
    private void getReplyComments(List<PageComment> tmpComments, List<PageComment> comments) {
        for (PageComment c : comments) {
            tmpComments.add(c);
            getReplyComments(tmpComments, c.getReplyComments());
        }
    }
    
    private List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = commentMapper.getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c : comments) {
            List<PageComment> replyComments = getPageCommentListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommentPublishedById(Long commentId, Boolean published) {
        //如果是隐藏评论，则所有子评论都要修改成隐藏状态
        if (!published) {
            List<CommentDO> comments = getAllReplyComments(commentId);
            for (CommentDO c : comments) {
                hideComment(c);
            }
        }
        
        if (commentMapper.updateCommentPublishedById(commentId, published) != 1) {
            throw new PersistenceException("操作失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommentNoticeById(Long commentId, Boolean notice) {
        if (commentMapper.updateCommentNoticeById(commentId, notice) != 1) {
            throw new PersistenceException("操作失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCommentById(Long commentId) {
        List<CommentDO> comments = getAllReplyComments(commentId);
        for (CommentDO c : comments) {
            delete(c);
        }
        if (commentMapper.deleteCommentById(commentId) != 1) {
            throw new PersistenceException("评论删除失败");
        }
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateComment(CommentDO comment) {
        if (commentMapper.updateComment(comment) != 1) {
            throw new PersistenceException("评论修改失败");
        }
    }
    
    @Override
    public int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished) {
        return commentMapper.countByPageAndIsPublished(page, blogId, isPublished);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(com.yond.blog.web.blog.view.dto.Comment comment) {
        if (commentMapper.saveComment(comment) != 1) {
            throw new PersistenceException("评论失败");
        }
    }
    
    @Override
    public int countComment() {
        return commentMapper.countComment();
    }
    
    /**
     * 递归删除子评论
     *
     * @param comment 需要删除子评论的父评论
     */
    private void delete(CommentDO comment) {
        for (CommentDO c : comment.getReplyComments()) {
            delete(c);
        }
        if (commentMapper.deleteCommentById(comment.getId()) != 1) {
            throw new PersistenceException("评论删除失败");
        }
    }
    
    /**
     * 递归隐藏子评论
     *
     * @param comment 需要隐藏子评论的父评论
     */
    private void hideComment(CommentDO comment) {
        for (CommentDO c : comment.getReplyComments()) {
            hideComment(c);
        }
        if (commentMapper.updateCommentPublishedById(comment.getId(), false) != 1) {
            throw new PersistenceException("操作失败");
        }
    }
    
    /**
     * 按id递归查询子评论
     *
     * @param parentCommentId 需要查询子评论的父评论id
     * @return
     */
    private List<CommentDO> getAllReplyComments(Long parentCommentId) {
        List<CommentDO> comments = commentMapper.getListByParentCommentId(parentCommentId);
        for (CommentDO c : comments) {
            List<CommentDO> replyComments = getAllReplyComments(c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }
    
    private List<CommentDTO> do2dto(List<CommentDO> from,Map<Long, CommentDO> map){
        for (CommentDO c : from) {
        
        }
    }
    
}

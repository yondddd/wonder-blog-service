package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.CommentDO;
import com.yond.blog.web.blog.view.vo.PageComment;

import java.util.List;

/**
 * @Description: 博客评论持久层接口
 * @Author: Naccl
 * @Date: 2020-08-03
 */
@Mapper
@Repository
public interface CommentMapper {
	List<CommentDO> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

	List<CommentDO> getListByParentCommentId(Long parentCommentId);

	List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

	CommentDO getCommentById(Long id);

	int updateCommentPublishedById(Long commentId, Boolean published);

	int updateCommentNoticeById(Long commentId, Boolean notice);

	int deleteCommentById(Long commentId);

	int deleteCommentsByBlogId(Long blogId);

	int updateComment(CommentDO comment);

	int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

	int countComment();

	int saveComment(com.yond.blog.web.blog.view.dto.Comment comment);
}

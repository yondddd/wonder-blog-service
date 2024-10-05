package com.wonder.blog.web.view.convert;

import com.wonder.blog.web.admin.dto.CommentDTO;
import com.wonder.blog.web.view.vo.CommentViewVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yond
 * @date 10/1/2024
 * @description
 */
public class CommentConverter {


    public static List<CommentViewVO> dto2vo(List<CommentDTO> from) {
        List<CommentViewVO> result = new ArrayList<>();
        for (CommentDTO dto : from) {
            CommentViewVO vo = new CommentViewVO();
            // Copy properties from DTO to VO
            vo.setId(dto.getId());
            vo.setParentCommentId(dto.getParentId());
            vo.setNickname(dto.getNickname());
            vo.setContent(dto.getContent());
            vo.setAvatar(dto.getAvatar());
            vo.setCreateTime(dto.getCreateTime());
            vo.setWebsite(dto.getWebsite());
            vo.setAdminComment(dto.getAdminComment());
            vo.setParentCommentNickname(dto.getParentNickname());
            // Handle nested replies
            if (dto.getReply() != null && !dto.getReply().isEmpty()) {
                vo.getReplyComments().addAll(dto2vo(dto.getReply()));
            }

            result.add(vo);
        }
        return result;
    }
}

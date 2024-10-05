package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.BlogDO;
import com.wonder.blog.web.admin.dto.CommentDTO;
import com.wonder.blog.web.admin.vo.CommentVO;
import com.wonder.common.enums.CommentPageEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Yond
 */
public class CommentConverter {
    
    
    public static List<CommentVO> dto2vo(List<CommentDTO> from, Map<Long, BlogDO> blogMap) {
        List<CommentVO> result = new ArrayList<>();
        for (CommentDTO dto : from) {
            CommentVO vo = new CommentVO();
            // Copy properties from DTO to VO
            vo.setId(dto.getId());
            vo.setParentId(dto.getParentId());
            vo.setPage(dto.getPage());
            vo.setNickname(dto.getNickname());
            vo.setEmail(dto.getEmail());
            vo.setContent(dto.getContent());
            vo.setAvatar(dto.getAvatar());
            vo.setCreateTime(dto.getCreateTime());
            vo.setWebsite(dto.getWebsite());
            vo.setIp(dto.getIp());
            vo.setPublished(dto.getPublished());
            vo.setAdminComment(dto.getAdminComment());
            vo.setNotice(dto.getNotice());
            vo.setQq(dto.getQq());
            
            if (CommentPageEnum.BLOG.getId().equals(dto.getPage())) {
                BlogDO blog = blogMap.get(dto.getBlogId());
                if (blog != null) {
                    vo.setBlogId(blog.getId());
                    vo.setBlogTitle(blog.getTitle());
                }
            }
            
            // Handle nested replies
            if (dto.getReply() != null && !dto.getReply().isEmpty()) {
                vo.getReply().addAll(dto2vo(dto.getReply(), blogMap));
            }
            
            result.add(vo);
        }
        return result;
    }
    
}

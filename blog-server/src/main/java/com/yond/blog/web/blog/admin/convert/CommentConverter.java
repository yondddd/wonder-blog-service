package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.admin.dto.CommentDTO;
import com.yond.blog.web.blog.admin.vo.BlogVO;
import com.yond.blog.web.blog.admin.vo.CommentVO;
import com.yond.common.enums.CommentPageEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yond
 * @date 9/7/2024
 * @description
 */
public class CommentConverter {


    public static List<CommentVO> dto2vo(List<CommentDTO> from, Map<Long, BlogDO> blogMap) {
        List<BlogVO> result = new ArrayList<>();
        for (CommentDTO commentDTO : from) {
            BlogDO blogDO = blogMap.get(commentDTO.getBlogId());
            BlogVO blogVO = new BlogVO();
            blogVO.setId(commentDTO.getBlogId());
            if (CommentPageEnum.BLOG.getId().equals(item.getPage())) {
                BlogDO exist = blogMap.get(item.getBlogId());
                if (exist == null) {
                    continue;
                }
                item.set
            }
        }
    }
}

package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.web.blog.admin.vo.CategoryVO;

/**
 * @author yond
 * @date 8/20/2024
 * @description
 */
public class CategoryConverter {

    public static CategoryVO do2vo(CategoryDO from) {
        CategoryVO to = new CategoryVO();
        to.setId(from.getId());
        to.setName(from.getName());
        return to;
    }

}

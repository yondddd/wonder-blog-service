package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.web.blog.admin.vo.CategoryVO;

/**
 * @Author Yond
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

    public static CategoryDO vo2do(CategoryVO from) {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setId(from.getId());
        categoryDO.setName(from.getName());
        return categoryDO;
    }

}

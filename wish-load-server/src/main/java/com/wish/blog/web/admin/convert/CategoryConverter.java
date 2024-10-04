package com.wish.blog.web.admin.convert;

import com.wish.blog.entity.CategoryDO;
import com.wish.blog.web.admin.vo.CategoryVO;

/**
 * @Author Yond
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

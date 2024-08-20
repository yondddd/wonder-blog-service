package com.yond.blog.web.blog.admin.controller;

import com.github.pagehelper.PageInfo;
import com.yond.blog.entity.CategoryDO;
import com.yond.blog.service.BlogService;
import com.yond.blog.service.CategoryService;
import com.yond.blog.web.blog.admin.convert.CategoryConverter;
import com.yond.blog.web.blog.admin.vo.CategoryVO;
import com.yond.common.annotation.OperationLogger;
import com.yond.common.resp.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 博客分类后台管理
 * @Author: Naccl
 * @Date: 2020-08-02
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryAdminController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    public CategoryAdminController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping("/listAll")
    public Response<List<CategoryVO>> listAll() {
        List<CategoryDO> list = categoryService.listAll();
        List<CategoryVO> data = list.stream().map(CategoryConverter::do2vo).collect(Collectors.toList());
        return Response.success(data);
    }

    /**
     * 获取博客分类列表
     *
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/categories")
    public Response<PageInfo<CategoryDO>> categories(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        Pair<Integer, List<CategoryDO>> pair = categoryService.page(pageNum, pageSize);
        PageInfo<CategoryDO> pageInfo = new PageInfo<>(pair.getRight());
        pageInfo.setTotal(pair.getLeft());
        return Response.success(pageInfo);
    }

    /**
     * 添加新分类
     *
     * @param category 分类实体
     * @return
     */
    @OperationLogger("添加分类")
    @PostMapping("/category")
    public Response saveCategory(@RequestBody CategoryDO category) {
        return getResult(category, "save");
    }

    /**
     * 修改分类名称
     *
     * @param category 分类实体
     * @return
     */
    @OperationLogger("修改分类")
    @PutMapping("/category")
    public Response updateCategory(@RequestBody CategoryDO category) {
        return getResult(category, "update");
    }

    /**
     * 执行分类添加或更新操作：校验参数是否合法，分类是否已存在
     *
     * @param category 分类实体
     * @param type     添加或更新
     * @return
     */
    private Response getResult(CategoryDO category, String type) {
        if (StringUtils.isBlank(category.getName())) {
            return Response.failure("分类名称不能为空");
        }
        //查询分类是否已存在
        CategoryDO category1 = categoryService.getByName(category.getName());
        //如果 category1.getId().equals(category.getId()) == true 就是更新分类
        if (category1 != null && !category1.getId().equals(category.getId())) {
            return Response.failure("该分类已存在");
        }
        if ("save".equals(type)) {
            categoryService.save(category);
            return Response.ok("分类添加成功");
        } else {
            categoryService.update(category);
            return Response.ok("分类更新成功");
        }
    }

    /**
     * 按id删除分类
     *
     * @param id 分类id
     * @return
     */
    @OperationLogger("删除分类")
    @DeleteMapping("/category")
    public Response<Boolean> delete(@RequestParam Long id) {
        //删除存在博客关联的分类后，该博客的查询会出异常
        int num = blogService.countBlogByCategoryId(id);
        if (num != 0) {
            return Response.<Boolean>custom().setFailure("已有博客与此分类关联，不可删除");
        }
        categoryService.deleteById(id);
        return Response.success();
    }

}

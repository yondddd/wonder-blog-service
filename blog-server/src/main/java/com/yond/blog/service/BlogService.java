package com.yond.blog.service;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.view.vo.ArchiveVO;
import com.yond.blog.web.view.vo.NewBlogVO;
import com.yond.blog.web.view.vo.RandomBlogVO;
import com.yond.blog.web.view.vo.SearchBlogVO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface BlogService {

    Pair<Integer, List<BlogDO>> adminPageBy(String title,
                                            Long categoryId,
                                            Long tagId,
                                            Integer pageNo,
                                            Integer pageSize);

    Pair<Integer, List<BlogDO>> viewPageBy(Long categoryId,
                                           Long tagId,
                                           Integer pageNo,
                                           Integer pageSize);

    List<BlogDO> listByIds(List<Long> ids);

    BlogDO getBlogById(Long id);

    List<BlogDO> listEnable();

    Long insertSelective(BlogDO blog);

    void updateSelective(BlogDO blog);

    void delById(Long id);

    void incrBlogView(List<Long> blogIds, Integer incr);

    List<SearchBlogVO> searchPublic(String query);

    List<NewBlogVO> listNewBlog();

    List<RandomBlogVO> listRandomBlog();

    ArchiveVO blogArchive();

}

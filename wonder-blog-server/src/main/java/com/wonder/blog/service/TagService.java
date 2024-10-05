package com.wonder.blog.service;

import com.wonder.blog.entity.TagDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface TagService {

    List<TagDO> listAll();

    List<TagDO> listByIds(List<Long> ids);

    Pair<Integer, List<TagDO>> page(Integer pageNo, Integer pageSize);

    TagDO getByName(String name);

    void deleteById(Long id);

    Long insertSelective(TagDO tag);

    Long saveIfAbsent(TagDO tag);

    void updateSelective(TagDO tag);

}

package com.wonder.blog.mapper;

import com.wonder.blog.entity.SiteConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 站点设置持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface SiteConfigMapper {

    List<SiteConfigDO> listAll();

    int insertSelective(SiteConfigDO record);

    int updateSelective(SiteConfigDO record);

}

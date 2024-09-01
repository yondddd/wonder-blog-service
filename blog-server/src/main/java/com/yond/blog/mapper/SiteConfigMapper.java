package com.yond.blog.mapper;

import com.yond.blog.entity.SiteConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 站点设置持久层接口
 * @Author: Naccl
 * @Date: 2020-08-03
 */
@Mapper
@Repository
public interface SiteConfigMapper {

    List<SiteConfigDO> listAll();

    int insertSelective(SiteConfigDO record);

    int updateSelective(SiteConfigDO record);

}

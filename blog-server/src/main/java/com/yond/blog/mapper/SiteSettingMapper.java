package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.SiteSettingDO;

import java.util.List;

/**
 * @Description: 站点设置持久层接口
 * @Author: Naccl
 * @Date: 2020-08-03
 */
@Mapper
@Repository
public interface SiteSettingMapper {
	List<SiteSettingDO> getList();

	List<SiteSettingDO> getFriendInfo();

	String getWebTitleSuffix();

	int updateSiteSetting(SiteSettingDO siteSetting);

	int deleteSiteSettingById(Integer id);

	int saveSiteSetting(SiteSettingDO siteSetting);

	int updateFriendInfoContent(String content);

	int updateFriendInfoCommentEnabled(Boolean commentEnabled);
}

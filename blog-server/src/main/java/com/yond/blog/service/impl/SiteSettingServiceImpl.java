package com.yond.blog.service.impl;

import com.yond.blog.cache.local.SiteSettingCache;
import com.yond.blog.entity.SiteSettingDO;
import com.yond.blog.mapper.SiteSettingMapper;
import com.yond.blog.service.SiteSettingService;
import com.yond.blog.util.JacksonUtils;
import com.yond.blog.web.blog.view.vo.Badge;
import com.yond.blog.web.blog.view.vo.Copyright;
import com.yond.blog.web.blog.view.vo.Favorite;
import com.yond.blog.web.blog.view.vo.Introduction;
import com.yond.common.constant.SiteSettingConstant;
import com.yond.common.exception.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 站点设置业务层实现
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService {

    private final SiteSettingMapper siteSettingMapper;

    public SiteSettingServiceImpl(SiteSettingMapper siteSettingMapper) {
        this.siteSettingMapper = siteSettingMapper;
    }

    private static final Pattern PATTERN = Pattern.compile("\"(.*?)\"");

    @Override
    public Map<String, List<SiteSettingDO>> getList() {
        List<SiteSettingDO> siteSettings = this.listAll();
        List<SiteSettingDO> type1 = new ArrayList<>();
        List<SiteSettingDO> type2 = new ArrayList<>();
        List<SiteSettingDO> type3 = new ArrayList<>();
        for (SiteSettingDO s : siteSettings) {
            switch (s.getType()) {
                case 1:
                    type1.add(s);
                    break;
                case 2:
                    type2.add(s);
                    break;
                case 3:
                    type3.add(s);
                    break;
                default:
                    break;
            }
        }
        Map<String, List<SiteSettingDO>> map = new HashMap<>(8);
        map.put("type1", type1);
        map.put("type2", type2);
        map.put("type3", type3);
        return map;
    }

    @Override
    public Map<String, Object> getSiteInfo() {
        List<SiteSettingDO> siteSettings = this.listAll();
        Map<String, Object> siteInfo = new HashMap<>(2);
        List<Badge> badges = new ArrayList<>();
        Introduction introduction = new Introduction();
        List<Favorite> favorites = new ArrayList<>();
        List<String> rollTexts = new ArrayList<>();
        for (SiteSettingDO s : siteSettings) {
            switch (s.getType()) {
                case 1:
                    if (SiteSettingConstant.COPYRIGHT.equals(s.getNameEn())) {
                        Copyright copyright = JacksonUtils.readValue(s.getValue(), Copyright.class);
                        siteInfo.put(s.getNameEn(), copyright);
                    } else {
                        siteInfo.put(s.getNameEn(), s.getValue());
                    }
                    break;
                case 2:
                    switch (s.getNameEn()) {
                        case SiteSettingConstant.AVATAR:
                            introduction.setAvatar(s.getValue());
                            break;
                        case SiteSettingConstant.NAME:
                            introduction.setName(s.getValue());
                            break;
                        case SiteSettingConstant.GITHUB:
                            introduction.setGithub(s.getValue());
                            break;
                        case SiteSettingConstant.TELEGRAM:
                            introduction.setTelegram(s.getValue());
                            break;
                        case SiteSettingConstant.QQ:
                            introduction.setQq(s.getValue());
                            break;
                        case SiteSettingConstant.BILIBILI:
                            introduction.setBilibili(s.getValue());
                            break;
                        case SiteSettingConstant.NETEASE:
                            introduction.setNetease(s.getValue());
                            break;
                        case SiteSettingConstant.EMAIL:
                            introduction.setEmail(s.getValue());
                            break;
                        case SiteSettingConstant.FAVORITE:
                            Favorite favorite = JacksonUtils.readValue(s.getValue(), Favorite.class);
                            favorites.add(favorite);
                            break;
                        case SiteSettingConstant.ROLL_TEXT:
                            Matcher m = PATTERN.matcher(s.getValue());
                            while (m.find()) {
                                rollTexts.add(m.group(1));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    Badge badge = JacksonUtils.readValue(s.getValue(), Badge.class);
                    badges.add(badge);
                    break;
                default:
                    break;
            }
        }
        introduction.setFavorites(favorites);
        introduction.setRollText(rollTexts);
        Map<String, Object> map = new HashMap<>(8);
        map.put("introduction", introduction);
        map.put("siteInfo", siteInfo);
        map.put("badges", badges);
        return map;
    }

    @Override
    public String getWebTitleSuffix() {
        return siteSettingMapper.getWebTitleSuffix();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds) {
        for (Integer id : deleteIds) {
            //删除
            deleteOneSiteSettingById(id);
        }
        for (LinkedHashMap s : siteSettings) {
            SiteSettingDO siteSetting = JacksonUtils.convertValue(s, SiteSettingDO.class);
            if (siteSetting.getId() != null) {
                //修改
                updateOneSiteSetting(siteSetting);
            } else {
                //添加
                saveOneSiteSetting(siteSetting);
            }
        }
        deleteSiteInfoRedisCache();
    }

    @Override
    public List<SiteSettingDO> getFriendInfo() {
        return siteSettingMapper.getFriendInfo();
    }

    @Override
    public int updateFriendInfoContent(String content) {
        return siteSettingMapper.updateFriendInfoContent(content);
    }

    @Override
    public int updateFriendInfoCommentEnabled(Boolean commentEnabled) {
        return siteSettingMapper.updateFriendInfoCommentEnabled(commentEnabled);
    }

    @Override
    public List<SiteSettingDO> listAll() {
        List<SiteSettingDO> siteSettings = SiteSettingCache.get();
        if (siteSettings == null) {
            siteSettings = siteSettingMapper.getList();
            SiteSettingCache.set(siteSettings);
        }
        return siteSettings;
    }

    public void saveOneSiteSetting(SiteSettingDO siteSetting) {
        if (siteSettingMapper.saveSiteSetting(siteSetting) != 1) {
            throw new PersistenceException("配置添加失败");
        }
    }

    public void updateOneSiteSetting(SiteSettingDO siteSetting) {
        if (siteSettingMapper.updateSiteSetting(siteSetting) != 1) {
            throw new PersistenceException("配置修改失败");
        }
    }

    public void deleteOneSiteSettingById(Integer id) {
        if (siteSettingMapper.deleteSiteSettingById(id) != 1) {
            throw new PersistenceException("配置删除失败");
        }
    }

    /**
     * 删除站点信息缓存
     */
    private void deleteSiteInfoRedisCache() {
        SiteSettingCache.del();
    }

}

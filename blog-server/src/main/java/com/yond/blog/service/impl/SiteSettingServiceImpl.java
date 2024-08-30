package com.yond.blog.service.impl;

import com.yond.blog.cache.local.SiteSettingCache;
import com.yond.blog.entity.SiteSettingDO;
import com.yond.blog.mapper.SiteSettingMapper;
import com.yond.blog.service.SiteSettingService;
import com.yond.blog.support.env.env.EnvConstant;
import com.yond.blog.support.env.env.Environment;
import com.yond.blog.util.JacksonUtils;
import com.yond.blog.web.blog.view.vo.Badge;
import com.yond.blog.web.blog.view.vo.Copyright;
import com.yond.blog.web.blog.view.vo.Favorite;
import com.yond.blog.web.blog.view.vo.Introduction;
import com.yond.common.constant.SiteSettingConstant;
import com.yond.common.enums.SiteSettingTypeEnum;
import com.yond.common.exception.PersistenceException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description: 站点设置业务层实现
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService, InitializingBean {
    
    private final SiteSettingMapper siteSettingMapper;
    
    public SiteSettingServiceImpl(SiteSettingMapper siteSettingMapper) {
        this.siteSettingMapper = siteSettingMapper;
    }
    
    private static final Pattern PATTERN = Pattern.compile("\"(.*?)\"");
    
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SiteSettingDO> list = this.listAll();
        for (SiteSettingDO setting : list) {
            if (SiteSettingConstant.TENCENT_IP_KEY.equals(setting.getNameEn())) {
                Environment.setProperty(EnvConstant.TENCENT_IP_KET, setting.getValue());
            }
        }
    }
    
    
    @Override
    public Map<String, Object> getSiteInfoForView() {
        List<SiteSettingDO> siteSettings = this.listAll();
        Map<String, Object> siteInfo = new HashMap<>(2);
        List<Badge> badges = new ArrayList<>();
        Introduction introduction = new Introduction();
        List<Favorite> favorites = new ArrayList<>();
        List<String> rollTexts = new ArrayList<>();
        for (SiteSettingDO s : siteSettings) {
            SiteSettingTypeEnum typeEnum = SiteSettingTypeEnum.getEnum(s.getType());
            switch (typeEnum) {
                case SiteSettingTypeEnum.BLOG_INFO:
                    if (SiteSettingConstant.COPYRIGHT.equals(s.getNameEn())) {
                        Copyright copyright = JacksonUtils.readValue(s.getValue(), Copyright.class);
                        siteInfo.put(s.getNameEn(), copyright);
                    } else {
                        siteInfo.put(s.getNameEn(), s.getValue());
                    }
                    break;
                case SiteSettingTypeEnum.PERSON_INFO:
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
                case SiteSettingTypeEnum.BOTTOM_BADGE:
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
    public String getValue(String key) {
        SiteSettingDO exist = this.listAll()
                .stream().filter(x -> key.equals(x.getNameEn()))
                .findFirst()
                .orElse(null);
        if (exist != null) {
            return exist.getValue();
        }
        return null;
    }
    
    @Override
    public void updateValue(String key, String value) {
        SiteSettingDO exist = this.listAll()
                .stream().filter(x -> key.equals(x.getNameEn()))
                .findFirst()
                .orElse(null);
        if (exist == null) {
            throw new RuntimeException(key + "不存在");
        }
        exist.setValue(value);
        this.updateOneSiteSetting(exist);
    }
    
    @Override
    public List<SiteSettingDO> listByType(SiteSettingTypeEnum typeEnum) {
        return this.listAll().stream()
                .filter(x -> typeEnum.getVal().equals(x.getType())).collect(Collectors.toList());
    }
    
    @Override
    public List<SiteSettingDO> listAll() {
        List<SiteSettingDO> siteSettings = SiteSettingCache.get();
        if (siteSettings == null) {
            siteSettings = siteSettingMapper.listAll();
            SiteSettingCache.set(siteSettings);
        }
        return siteSettings;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void coverUpdate(List<SiteSettingDO> data) {
        Map<String, SiteSettingDO> map = this.listAll().stream()
                .collect(Collectors.toMap(SiteSettingDO::getNameEn, Function.identity(), (key1, key2) -> key1));
        List<SiteSettingDO> insert = new ArrayList<>();
        List<SiteSettingDO> update = new ArrayList<>();
        for (SiteSettingDO datum : data) {
            SiteSettingDO exist = map.get(datum.getNameEn());
            if (datum.getId() == null) {
                Assert.isNull(exist, datum.getNameEn() + " is exist");
                insert.add(datum);
            } else {
                update.add(datum);
            }
        }
        for (SiteSettingDO item : insert) {
            this.saveOneSiteSetting(item);
        }
        for (SiteSettingDO item : update) {
            SiteSettingDO toUpdate = new SiteSettingDO();
            toUpdate.setId(item.getId());
            toUpdate.setValue(item.getValue());
            toUpdate.setNameZh(item.getNameZh());
            this.updateOneSiteSetting(toUpdate);
        }
    }
    
    private void saveOneSiteSetting(SiteSettingDO siteSetting) {
        if (siteSettingMapper.insertSelective(siteSetting) != 1) {
            throw new PersistenceException("配置添加失败");
        }
        this.deleteSiteInfoRedisCache();
    }
    
    private void updateOneSiteSetting(SiteSettingDO siteSetting) {
        if (siteSettingMapper.updateSelective(siteSetting) != 1) {
            throw new PersistenceException("配置修改失败");
        }
        this.deleteSiteInfoRedisCache();
    }
    
    /**
     * 删除站点信息缓存
     */
    private void deleteSiteInfoRedisCache() {
        SiteSettingCache.del();
    }
    
}

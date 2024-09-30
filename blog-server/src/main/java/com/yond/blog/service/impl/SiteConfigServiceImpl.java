package com.yond.blog.service.impl;

import com.yond.blog.cache.local.SiteSettingCache;
import com.yond.blog.entity.SiteConfigDO;
import com.yond.blog.mapper.SiteConfigMapper;
import com.yond.blog.service.SiteConfigService;
import com.yond.common.enums.SiteConfigTypeEnum;
import com.yond.common.exception.PersistenceException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: 站点设置业务层实现
 * @Author: Yond
 */
@Service
public class SiteConfigServiceImpl implements SiteConfigService {

    @Resource
    private SiteConfigMapper siteConfigMapper;

    @Override
    public String getValue(String key) {
        SiteConfigDO exist = this.listAll()
                .stream().filter(x -> key.equals(x.getKey()))
                .findFirst()
                .orElse(null);
        if (exist != null) {
            return exist.getValue();
        }
        return null;
    }

    @Override
    public void updateValue(String key, String value) {
        SiteConfigDO exist = this.listAll()
                .stream().filter(x -> key.equals(x.getKey()))
                .findFirst()
                .orElse(null);
        if (exist == null) {
            throw new RuntimeException(key + "不存在");
        }
        exist.setValue(value);
        this.updateOneSiteSetting(exist);
    }

    @Override
    public List<SiteConfigDO> listByType(SiteConfigTypeEnum typeEnum) {
        return this.listAll().stream()
                .filter(x -> typeEnum.getVal().equals(x.getType())).collect(Collectors.toList());
    }

    @Override
    public List<SiteConfigDO> listAll() {
        List<SiteConfigDO> siteSettings = SiteSettingCache.get();
        if (siteSettings == null) {
            siteSettings = siteConfigMapper.listAll();
            SiteSettingCache.set(siteSettings);
        }
        return siteSettings;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized void coverUpdate(List<SiteConfigDO> data) {
        Map<String, SiteConfigDO> map = this.listAll().stream()
                .collect(Collectors.toMap(SiteConfigDO::getKey, Function.identity(), (key1, key2) -> key1));
        List<SiteConfigDO> insert = new ArrayList<>();
        List<SiteConfigDO> update = new ArrayList<>();
        for (SiteConfigDO datum : data) {
            SiteConfigDO exist = map.get(datum.getKey());
            if (exist == null) {
                insert.add(datum);
            } else {
                update.add(datum);
            }
        }
        for (SiteConfigDO item : insert) {
            this.saveOneSiteSetting(item);
        }
        for (SiteConfigDO item : update) {
            SiteConfigDO toUpdate = new SiteConfigDO();
            toUpdate.setKey(item.getKey());
            toUpdate.setValue(item.getValue());
            toUpdate.setName(item.getName());
            this.updateOneSiteSetting(toUpdate);
        }
    }

    private void saveOneSiteSetting(SiteConfigDO siteSetting) {
        if (siteConfigMapper.insertSelective(siteSetting) != 1) {
            throw new PersistenceException("配置添加失败");
        }
        this.deleteSiteInfoRedisCache();
    }

    private void updateOneSiteSetting(SiteConfigDO siteSetting) {
        if (siteConfigMapper.updateSelective(siteSetting) != 1) {
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

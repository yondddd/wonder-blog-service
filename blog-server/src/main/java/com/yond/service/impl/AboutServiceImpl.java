package com.yond.service.impl;

import com.yond.cache.AboutCache;
import com.yond.common.exception.util.PersistenceExceptionUtil;
import com.yond.constant.AboutConstant;
import com.yond.entity.AboutDO;
import com.yond.mapper.AboutMapper;
import com.yond.service.AboutService;
import com.yond.util.markdown.MarkdownUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 关于我页面业务层实现
 * @Author: Naccl
 * @Date: 2020-08-31
 */
@Service
public class AboutServiceImpl implements AboutService {

    private final AboutMapper aboutMapper;
    private final AboutCache aboutCache;

    public AboutServiceImpl(AboutMapper aboutMapper, AboutCache aboutCache) {
        this.aboutMapper = aboutMapper;
        this.aboutCache = aboutCache;
    }

    @Override
    public Map<String, String> getAbout(boolean useCache) {

        return useCache ?
                getAboutInfoFromCache() :
                getAboutInfoFromDb();
    }

    @Override
    public void updateAbout(Map<String, String> map) {

        for (Map.Entry<String, String> entry : map.entrySet()) {
            int updated = aboutMapper.updateAbout(entry.getKey(), entry.getValue());
            PersistenceExceptionUtil.isTrue(updated == 1, "关于我修改失败");
        }
        aboutCache.del();
    }

    @Override
    public boolean getCommentEnabled() {

        String commentEnabled = this.getAboutInfoFromCache().get(AboutConstant.COMMENT_KEY);
        return Boolean.parseBoolean(commentEnabled);
    }


    private Map<String, String> getAboutInfoFromDb() {

        return this.listAll().stream()
                .collect(Collectors.toMap(AboutDO::getNameEn, AboutDO::getValue, (key1, key2) -> key1));
    }

    /**
     * 注意只给前台view使用
     */
    private Map<String, String> getAboutInfoFromCache() {

        Map<String, String> data = aboutCache.get();
        if (data != null) {
            return data;
        }

        List<AboutDO> aboutDOS = this.listAll();
        data = new HashMap<>(aboutDOS.size());
        for (AboutDO aboutDO : aboutDOS) {
            if (AboutConstant.CONTENT_KEY.equals(aboutDO.getNameEn())) {
                aboutDO.setValue(MarkdownUtils.markdownToHtmlExtensions(aboutDO.getValue()));
            }
            data.put(aboutDO.getNameEn(), aboutDO.getValue());
        }
        aboutCache.set(data);
        return data;
    }


    private List<AboutDO> listAll() {
        return aboutMapper.listAll();
    }

}

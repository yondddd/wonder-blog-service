package com.yond.blog.service;

import java.util.Map;

public interface AboutService {

    /**
     * @param useCache 是否使用缓存
     * @return <nameEn,value>
     */
    Map<String, String> getAbout(boolean useCache);

    /**
     * 更新关于我信息
     *
     * @param map <nameEn,value>
     */
    void updateAbout(Map<String, String> map);

    /**
     * @return 关于我页面评论是否开启
     */
    boolean getCommentEnabled();

}

package com.yond.blog.web.admin.convert;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.web.admin.vo.FriendVO;

/**
 * @Author: Yond
 */
public class FriendConverter {
    
    public static FriendVO do2vo(FriendDO from) {
        FriendVO to = new FriendVO();
        to.setId(from.getId());
        to.setNickname(from.getNickname());
        to.setDescription(from.getDescription());
        to.setWebsite(from.getWebsite());
        to.setAvatar(from.getAvatar());
        to.setPublished(from.getPublished());
        to.setViews(from.getViews());
        to.setCreateTime(from.getCreateTime());
        return to;
    }
    
}

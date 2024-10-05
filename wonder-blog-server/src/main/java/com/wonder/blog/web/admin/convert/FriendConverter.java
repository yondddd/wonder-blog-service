package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.FriendDO;
import com.wonder.blog.web.admin.vo.FriendVO;

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

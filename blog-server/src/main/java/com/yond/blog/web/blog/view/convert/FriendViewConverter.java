package com.yond.blog.web.blog.view.convert;

import com.yond.blog.entity.FriendDO;
import com.yond.blog.web.blog.view.vo.FriendListVO;

/**
 * @Author: Yond
 */
public class FriendViewConverter {
    
    public static FriendListVO do2vo(FriendDO from) {
        FriendListVO to = new FriendListVO();
        to.setId(from.getId());
        to.setNickname(from.getNickname());
        to.setDescription(from.getDescription());
        to.setWebsite(from.getWebsite());
        to.setAvatar(from.getAvatar());
        return to;
    }
    
}

package com.wonder.blog.web.view.convert;

import com.wonder.blog.entity.FriendDO;
import com.wonder.blog.web.view.vo.FriendListVO;

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

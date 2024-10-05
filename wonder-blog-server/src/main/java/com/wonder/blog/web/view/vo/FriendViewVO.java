package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: Yond
 */
public class FriendViewVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 3828309169131446989L;
    
    private String content;
    private Boolean commentEnabled;
    private List<FriendListVO> friendList;
    
    public String getContent() {
        return content;
    }
    
    public FriendViewVO setContent(String content) {
        this.content = content;
        return this;
    }
    
    public Boolean getCommentEnabled() {
        return commentEnabled;
    }
    
    public FriendViewVO setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }
    
    public List<FriendListVO> getFriendList() {
        return friendList;
    }
    
    public FriendViewVO setFriendList(List<FriendListVO> friendList) {
        this.friendList = friendList;
        return this;
    }
    
    @Override
    public String toString() {
        return "FriendViewVO{" +
                "content='" + content + '\'' +
                ", commentEnabled=" + commentEnabled +
                ", friendList=" + friendList +
                '}';
    }
}

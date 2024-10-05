package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: Yond
 */
public class AboutVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7849965494576292034L;

    private String title;
    private String musicId;
    private String content;
    private String commentEnabled;

    public String getTitle() {
        return title;
    }

    public AboutVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMusicId() {
        return musicId;
    }

    public AboutVO setMusicId(String musicId) {
        this.musicId = musicId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AboutVO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCommentEnabled() {
        return commentEnabled;
    }

    public AboutVO setCommentEnabled(String commentEnabled) {
        this.commentEnabled = commentEnabled;
        return this;
    }

    @Override
    public String toString() {
        return "AboutVO{" +
                "title='" + title + '\'' +
                ", musicId='" + musicId + '\'' +
                ", content='" + content + '\'' +
                ", commentEnabled='" + commentEnabled + '\'' +
                '}';
    }
}

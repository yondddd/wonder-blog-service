package com.yond.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: 友链页面信息
 * @Author: Naccl
 * @Date: 2020-09-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -3609256529138860536L;
    private String content;
    private Boolean commentEnabled;
}

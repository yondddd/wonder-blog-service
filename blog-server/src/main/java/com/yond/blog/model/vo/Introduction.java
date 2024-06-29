package com.yond.blog.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 侧边栏资料卡
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Introduction implements Serializable {
    @Serial
    private static final long serialVersionUID = 8908116814963447260L;
    private String avatar;
    private String name;
    private String github;
    private String telegram;
    private String qq;
    private String bilibili;
    private String netease;
    private String email;

    private List<String> rollText = new ArrayList<>();
    private List<Favorite> favorites = new ArrayList<>();

}

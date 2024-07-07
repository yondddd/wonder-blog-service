package com.yond.blog.util.jwt;

import com.yond.blog.entity.UserDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 2023/10/21
 * @description token
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8406474009218062910L;

    private String token;
    private UserDO user;

}

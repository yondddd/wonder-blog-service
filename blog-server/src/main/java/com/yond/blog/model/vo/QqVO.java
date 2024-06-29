package com.yond.blog.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author raxcl
 * @date 2024-01-19 9:54:53
 */
@Data
public class QqVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3044377546418449560L;
    /**
     * qq号
     */
    private Long qq;

    /**
     * qq昵称
     */
    private String name;

    /**
     * qq邮箱
     */
    private String email;

    /**
     * qq头像
     */
    private String avatar;
}

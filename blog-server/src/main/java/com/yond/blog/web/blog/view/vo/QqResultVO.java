package com.yond.blog.web.blog.view.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * @author raxcl
 * @date 2024-01-19 9:54:53
 */
@Data
public class QqResultVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4454455192459503846L;
    private String success;

    private String msg;

    private Map<String, Object> data;

    private String time;

    private String api_vers;
}

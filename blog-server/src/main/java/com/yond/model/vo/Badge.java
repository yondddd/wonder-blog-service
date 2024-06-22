package com.yond.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: GitHub徽标
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Badge implements Serializable {
    @Serial
    private static final long serialVersionUID = 2470572916576310844L;
    private String title;
    private String url;
    private String subject;
    private String value;
    private String color;
}

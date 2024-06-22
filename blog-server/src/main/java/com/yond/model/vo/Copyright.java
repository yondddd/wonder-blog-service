package com.yond.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description: copyright
 * @Author: Naccl
 * @Date: 2020-08-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Copyright implements Serializable {
    @Serial
    private static final long serialVersionUID = 3313951721464223460L;
    private String title;
    private String siteName;
}

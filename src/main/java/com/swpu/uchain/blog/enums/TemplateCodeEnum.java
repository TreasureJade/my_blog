package com.swpu.uchain.blog.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author hobo
 * @description
 */
@Getter
public enum TemplateCodeEnum {
    INSERTUSER("SMS_166665985")
    ;
    private String value;

    TemplateCodeEnum(String value) {
        this.value = value;
    }
}

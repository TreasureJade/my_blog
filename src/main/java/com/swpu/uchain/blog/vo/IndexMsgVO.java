package com.swpu.uchain.blog.vo;

import lombok.Data;

/**
 * @author hobo
 * @description
 */
@Data
public class IndexMsgVO {

    private int articleTotal;

    private int tagTotal;

    private int visitorTotal;

    private int commentTotal;

    private String updateTime;
}


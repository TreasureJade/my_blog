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

    private int leaveMessageTotal;

    private int commentTotal;

    private String updateTime;
}


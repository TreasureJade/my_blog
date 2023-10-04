package com.by.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hobo
 * @description
 */
@Data
public class LeaveMsgListVO {

    private List<LeaveMsgVO> msgVOList;

    private Integer leaveMsgNum;

}

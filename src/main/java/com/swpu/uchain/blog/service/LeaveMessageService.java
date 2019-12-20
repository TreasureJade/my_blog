package com.swpu.uchain.blog.service;

import com.swpu.uchain.blog.form.CreatLeaveMsgForm;
import com.swpu.uchain.blog.vo.ResultVO;
import lombok.Data;

/**
 * @author hobo
 * @description
 */
public interface LeaveMessageService {

    /**
     * 添加留言
     * @return
     */
    ResultVO insertLeaveMsg(CreatLeaveMsgForm form);

    /**
     * 展示所有留言
     * @return
     */
    ResultVO selectAllLeaveMsg();

}

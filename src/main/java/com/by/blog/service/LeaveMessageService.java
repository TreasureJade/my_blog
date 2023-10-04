package com.by.blog.service;

import com.by.blog.form.CreatLeaveMsgForm;
import com.by.blog.vo.ResultVO;

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

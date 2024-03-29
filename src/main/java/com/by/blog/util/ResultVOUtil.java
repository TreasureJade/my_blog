package com.by.blog.util;

import com.by.blog.enums.ResultEnum;
import com.by.blog.vo.ResultVO;
import lombok.Data;

/**
 * @ClassName ResultVOUtil
 * @Author hobo
 * @Date 19-4-22 下午3:40
 * @Description
 **/
@Data
public class ResultVOUtil {

    /**
     * @param object
     * @return 有参成功结果
     * @author hobo
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /***
     *
     * @author hobo
     * @return 返回无参结果
     */
    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

}

package com.by.blog.aspect;
import com.by.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName ControllerLog
 * @Author hobo
 * @Date 19-4-22 下午8:37
 * @Description
 **/

@Aspect
@Component
@Slf4j
public class ControllerLog {

    @Pointcut("execution(public * com.swpu.uchain.blog.controller.*.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getDeclaringTypeName() + "." + signature.getName();
        log.info("-----------------------------------------------------");
        log.info("当前执行controller的方法:  " + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("参数: " + arg);
        }
    }

    @AfterReturning(pointcut = "controller()", returning = "ret")
    public void after(Object ret) {
        if (!ret.getClass().getName().equals(ResultVO.class.getName())){
            log.info("controller返回参数：" + ret);
            log.info("-----------------------------------------------------");
            return;
        }
        ResultVO result = (ResultVO) ret;
        if (result.getCode() != 0) {
            log.error(result.getMsg());
        }
        log.info("controller返回参数：" + ret);
        log.info("-----------------------------------------------------");
    }
}

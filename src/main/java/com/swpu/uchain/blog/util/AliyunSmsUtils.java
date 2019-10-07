package com.swpu.uchain.blog.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云短信服务
 *
 * @author hobo
 */
@Slf4j
public class AliyunSmsUtils {


    private static final String PRODUCT = "Dysmsapi";
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    private static final String ACCESS_KEY_ID = "LTAIOqY6ewyldbO7";
    private static final String ACCESS_KEY_SECRET = "tO28iD4zgRBwnAGw3h87uoi0kgr3oq";

    public static boolean sendInsertUserMsg(String telephone, String code,String templateCode) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(telephone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("hobo");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            log.info("短信发送成功");
            return true;
        } else {
            log.error("短信发送失败");
        }
        return false;
    }
    /**
     * 创建验证码
     * @return java.lang.String
     */
    public static String setCode() {
        int code = (int) ((Math.random() * 9999) + 100);
        return code + "";
    }

}

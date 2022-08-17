/**
 * 
 */
package com.ecc.aipao98.test;


import com.ecc.aipao98.until.*;
import com.google.gson.Gson;




import java.util.UUID;

/**
 * 单条短信测试
 * @author zym
 * @date 2016-6-24 上午11:25:12
 */
public class MsgSubmitJsonTest {

    public static void main(String[] args) throws Exception {
        //String url = "https://www.sms-cly.cn/v7/msg/submit.json";
        String url = "http://sms.test.cly.cn:9001/v7/msg/submit.json";
        String userName = "test";
        String password = "test";
        String content = "【诚立业】您的验证码是1001";
        String mobile = "13800000000";
        String seqid = UUID.randomUUID().toString();

        MsgSubmit msgSubmit = new MsgSubmit();
        msgSubmit.setUserName(userName);
        msgSubmit.setSign(MD5Utils.MD5Encode(userName + password + mobile + content));
        msgSubmit.setMobile(mobile);
        msgSubmit.setContent(content);
        msgSubmit.setSeqid(seqid);

        Gson gson = new Gson();
        StringBuilder jsonSb = new StringBuilder(gson.toJson(msgSubmit));

        HttpResult httpResult = HttpUtils.post(url, jsonSb, Charsets.UTF8);

        System.out.println(jsonSb.toString());
        System.out.println(httpResult.getContent().toString());
    }
}

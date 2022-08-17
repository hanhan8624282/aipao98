package com.ecc.aipao98.controller;



import com.ecc.aipao98.dao.SignupDetailDao;
import com.ecc.aipao98.dao.SmsLogDao;
import com.ecc.aipao98.until.ImageUtil;
import com.ecc.aipao98.until.R;
import com.ecc.aipao98.until.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


@RestController
@Slf4j
public class VerifyCodeController {
    @Autowired
    private SignupDetailDao signupDetailDao;//自动注入报名表

    @Autowired
    private SmsLogDao smsLogDao;

    /**
     * 去除 l、o、I、O、0、1
     */
    private static final char[] CHAR_ARRAY = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();
    /**登录图片验证码
     * @param session
     * @return
     */
    @GetMapping("/getVerifyImage")
    public void getVerifyImage(HttpServletRequest request, HttpServletResponse res, HttpSession session) throws IOException {

        String checkCode = RandomStringUtils.random(4, CHAR_ARRAY);

        log.error("getVerifyImage 开始生产验证码checkCode="+checkCode);

        //TODO 直接返回byte数据
        ByteArrayOutputStream os = (ByteArrayOutputStream) ImageUtil.generator(102, 25, checkCode);
        byte[] bytes = os.toByteArray();
        try {
            os.close();
        } catch (IOException e) {
        }
        session.setAttribute("checkCode", checkCode);//将验证码值存入HttpSession中
        res.setContentType("image/jpg");
        res.setContentLength(bytes.length);
        res.getOutputStream().write(bytes);
    }

    @GetMapping("/getCheckCode")//该方法为验证码值的获取方法
    public Object getCheckCode(HttpSession session) {
        log.error("获取验证码=="+session.getAttribute("checkCode"));
        return session.getAttribute("checkCode");
    }
    @PostMapping
    @RequestMapping("/VerifiCodeImageCheck")
    @ApiOperation("报名第一个验证码接口-通过发送短信验证码")
    public R login(HttpServletRequest request){

        //获取用户输入的验证码
        String userLoginCode = request.getParameter("code");
        //用户的手机号
        String userPhone = request.getParameter("phone");
        //String loginCode = loginFrom.getVerifiCode();
        //获取保存在session域中的验证码
        String sessionCode = (String)request.getSession().getAttribute("checkCode");
        System.out.println("sessionCode="+sessionCode+",userLoginCode="+userLoginCode);
        //判断验证码是否为空
        if(userLoginCode==null || sessionCode==null){
            return R.error().message("验证码为空，或无效。。");
            //return Result.fail().message("验证码为空，或无效。。");
        }
        if(!sessionCode.equalsIgnoreCase(userLoginCode)){
            return R.error().message("验证码输入错误请再次输入。。。");
        }
        //验证通过后清除掉session域中的验证码
        request.getSession().removeAttribute("verifiCode");
        //创建一个存放用户数据map，用来返给前端
        //Map<String,String> map=new LinkedHashMap<>();
        //map.put("phone",userPhone);//用户手机号
        //map.put("code",userLoginCode);//用户第一次的验证码
        //判断该手机号-每日获取的验证码次数
        int codeCount = smsLogDao.checkCount(userPhone);
        if(codeCount>5){
            return R.error().message("每日获取的验证码次数达到上限。。。");
        }
        //验证码五分钟有效; 0=超过五分钟
        int flagcount = smsLogDao.codeFlag(userPhone);
        if(flagcount>0){
            return R.error().message("验证码五分钟有效。。。");
        }
        //调用短信接口发送短信
        //生成验证码
        int messageCode= (int)((Math.random()*9+1)*1000);
        System.out.println("code="+messageCode);
        //创建一个map
        Map<String,String> map=new LinkedHashMap<String,String>();





        return   R.ok();
    }

}
package com.ecc.aipao98.controller;

import com.ecc.aipao98.dao.SignupDetailDao;
import com.ecc.aipao98.dao.SmsLogDao;
import com.ecc.aipao98.until.CreateVerifiCodeImage;
import com.ecc.aipao98.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sunyc
 * @create 2022-08-16 8:14
 */
@RestController
@Slf4j
@Api("系统后台")
public class SystemController {

    @Autowired
    private SignupDetailDao signupDetailDao;//自动注入报名表

    @Autowired
    private SmsLogDao smsLogDao;

    @GetMapping
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }


}

package com.yihukurama.springbootdemo.framework.weixin.c;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yihukurama.springbootdemo.framework.weixin.WXmain;
import com.yihukurama.springbootdemo.framework.weixin.m.Constants;
import com.yihukurama.springbootdemo.sdk.KeyValueDBApi;
import com.yihukurama.springbootdemo.utils.http.HttpClientUtil;
/**
 * 基础控制器
 * @author yizhi01
 *
 */
@Controller
public class CommonController {
	private Logger logger = Logger.getLogger(CommonController.class);
	//刷新微信token
    @RequestMapping("/refreshtoken")
    public void index(@RequestParam(value="pwd", required=true) String pwd) {
        if(pwd!=null && pwd.equals("dengshuai@123")){
        		//刷新微信token并存入野狗
        		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        		url = String.format(url, Constants.APPID,Constants.APPSECRET);
        		String response = HttpClientUtil.doGet(url);
        		logger.info("刷新的微信token是："+response);
        		KeyValueDBApi.saveWeixinToken(response);
        }
        		
    }
    
    

}
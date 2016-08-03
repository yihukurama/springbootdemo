package com.yihukurama.springbootdemo.framework.weixin;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yihukurama.springbootdemo.framework.weixin.c.TulingResponse;
import com.yihukurama.springbootdemo.framework.weixin.m.Constants;
import com.yihukurama.springbootdemo.utils.SHA1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 微信入口
 * @author dengshuai
 *
 */
@RestController
public class WXmain {
	
	TulingResponse tulingResponse;
	
	public WXmain(){
		tulingResponse = new TulingResponse();
	}
	
	@RequestMapping("/weixin")
        String weixinhome(
    		@RequestParam(value="echostr", required=false, defaultValue="null") String echostr,
    		@RequestParam(value="timestamp", required=false, defaultValue="null") String timestamp,
    		@RequestParam(value="nonce", required=false, defaultValue="null") String nonce,
    		@RequestParam(value="encrypt", required=false, defaultValue="null") String encrypt,
    		@RequestParam(value="signature", required=false, defaultValue="null") String signature,
    		@RequestBody(required=false)String requestBody) {
		String response = "Hello weixin!";
		
		if(echostr.equals("null")){//已认证通过，进入响应方法
			response = tulingResponse.response(requestBody);
		}else{//基础认证
			
			String checkResult = check(Constants.TOKEN, timestamp, nonce);
			if(signature.equals(checkResult))
                            response = echostr;
			
		}
		
            return response;
        }


        /**
        * 验证微信服务器
        * @param token
        * @param timestamp
        * @param nonce
        * @return 
        */
        public  String check(String token, String timestamp, String nonce)
        {
                List<String> params = params = new ArrayList<String>();
                params.add(token);  
                params.add(timestamp);  
                params.add(nonce);  
                //1. 将token、timestamp、nonce三个参数进行字典序排序  
                Collections.sort(params, new Comparator<String>() {  
                @Override  
                public int compare(String o1, String o2) {  
                    return o1.compareTo(o2);  
                }  
            });   
                //2. 将三个参数字符串拼接成一个字符串进行sha1加密  
                String temp = SHA1.encode(params.get(0) + params.get(1) + params.get(2));  
                return temp;

        }
	
}

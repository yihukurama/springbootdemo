package com.yihukurama.springbootdemo.framework.weixin;

import java.security.MessageDigest;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yihukurama.springbootdemo.framework.weixin.c.TulingResponse;
import com.yihukurama.springbootdemo.framework.weixin.sourcecode.AesException;

/**
 * 微信入口
 * @author dengshuai
 *
 */
@RestController
public class WXmain {
	final String token = "yihukurama";
	TulingResponse tulingResponse;
	
	public WXmain(){
		tulingResponse = new TulingResponse();
	}
	
	@RequestMapping("/weixin")
    String weixinhome(
    		@RequestParam(value="echostr", required=false, defaultValue="null") String echostr,
    		@RequestParam(value="echostr", required=false, defaultValue="null") String timestamp,
    		@RequestParam(value="echostr", required=false, defaultValue="null") String nonce,
    		@RequestParam(value="echostr", required=false, defaultValue="null") String encrypt,
    		
    		@RequestBody(required=false)String requestBody) {
		String response = "Hello weixin!";
		
		if(echostr.equals("null")){//已认证通过，进入响应方法
			response = tulingResponse.response(requestBody);
		}else{//基础认证
			
			try {
				response = check(token, timestamp, nonce, encrypt);
			} catch (AesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        return response;
    }


		/**
		 * 用SHA1算法生成安全签名
		 * @param token 票据
		 * @param timestamp 时间戳
		 * @param nonce 随机字符串
		 * @param encrypt 密文
		 * @return 安全签名
		 * @throws AesException 
		 */
		public  String check(String token, String timestamp, String nonce, String encrypt) throws AesException
		{
			try {
				String[] array = new String[] { token, timestamp, nonce, encrypt };
				StringBuffer sb = new StringBuffer();
				// 字符串排序
				Arrays.sort(array);
				for (int i = 0; i < 4; i++) {
					sb.append(array[i]);
				}
				String str = sb.toString();
				// SHA1签名生成
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				md.update(str.getBytes());
				byte[] digest = md.digest();

				StringBuffer hexstr = new StringBuffer();
				String shaHex = "";
				for (int i = 0; i < digest.length; i++) {
					shaHex = Integer.toHexString(digest[i] & 0xFF);
					if (shaHex.length() < 2) {
						hexstr.append(0);
					}
					hexstr.append(shaHex);
				}
				return hexstr.toString();
			} catch (Exception e) {
				e.printStackTrace();
				throw new AesException(AesException.ComputeSignatureError);
			}

	}
	
}

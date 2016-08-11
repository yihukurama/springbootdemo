package com.yihukurama.springbootdemo.sdk;

import com.wilddog.client.Wilddog;
import com.yihukurama.springbootdemo.framework.weixin.m.Constants;
import com.yihukurama.springbootdemo.framework.weixin.m.response.AccessToken;
import com.yihukurama.springbootdemo.utils.JsonUtil;

/**
 * 对Key-Value 数据库的增删改查封装，此处使用wilddog
 * @author yizhi01
 *
 */
public class KeyValueDBApi {
	private static Wilddog ref = new Wilddog("https://lovebank.wilddogio.com");
	
	//存储微信accessToken
	public static void saveWeixinToken(String accessToken){
		try {
			AccessToken aT = JsonUtil.jsonToBean(accessToken, AccessToken.class);
			Constants.accessToken = aT.getAccess_token();
			ref.child("WeiXin").child("Response").child("AccessToken").setValue(aT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

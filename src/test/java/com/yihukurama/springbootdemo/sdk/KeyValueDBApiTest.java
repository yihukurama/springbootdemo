package com.yihukurama.springbootdemo.sdk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yihukurama.springbootdemo.framework.weixin.m.response.AccessToken;


public class KeyValueDBApiTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveWeixinToken() {
		String accessToken = "{\"access_token\":\"BOJ\",\"expires_in\":7200}";
		
		KeyValueDBApi.saveWeixinToken(accessToken);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

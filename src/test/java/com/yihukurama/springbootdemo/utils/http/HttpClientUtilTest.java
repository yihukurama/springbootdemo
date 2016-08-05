package com.yihukurama.springbootdemo.utils.http;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import junit.framework.TestCase;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class HttpClientUtilTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPost() {
//		fail("Not yet implemented");
      	String url = "http://www.lovebanks.cn";

  
       

       String result = HttpClientUtil.doPost(url, "", "utf-8");

       System.out.println(result.toString());
	}

	@Test
	public void testDoGet() {
//		fail("Not yet implemented");
      	String url = "http://www.lovebanks.cn";

      	String result = HttpClientUtil.doGet(url);

      	System.out.println(result.toString());
	}

}

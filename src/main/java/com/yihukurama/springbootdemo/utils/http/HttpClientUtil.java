package com.yihukurama.springbootdemo.utils.http;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
	
	/**
	 * POST请求
	 * @param url
	 * @param requestBody
	 * @param charset
	 * @return
	 */
    public static String doPost(String url,String requestBody,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new DefaultHttpClient();  
            httpPost = new HttpPost(url);  
            StringEntity entity = new StringEntity(requestBody,charset);//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");    
            httpPost.setEntity(entity); 
            
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static String doGet(String url){
        //get请求返回结果
    		String strResult = "No data";
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                strResult = EntityUtils.toString(response.getEntity());
                url = URLDecoder.decode(url, "UTF-8");
            } else {	
            		System.out.println("get请求提交失败:" + url);
            }
        } catch (IOException e) {
        		System.out.println("get请求提交失败:" + url);
        }
        return strResult;
    }
  
}  
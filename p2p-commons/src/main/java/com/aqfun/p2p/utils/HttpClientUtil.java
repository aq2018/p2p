package com.aqfun.p2p.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

/**
 * ClassName : HttpClientUtil
 *
 * @author aq
 * @date 2018/9/7
 */
public class HttpClientUtil {

    /**
     * Get 请求方法
     * @param url
     * @return
     */
    public static String doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            //创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            //设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)//连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//请求超时时间
                    .setSocketTimeout(60000)//数据读取超时时间
                    .build();
            //为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            //执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            //通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            //通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static String doPost(String url, Map<String,Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //创建httpClient实例
            httpClient = HttpClients.createDefault();
            //创建httpPost远程连接实例
            HttpPost httpPost = new HttpPost(url);
            //配置请求参数实例
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)//设置连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)//设置连接请求超时时间
                    .setSocketTimeout(60000)//设置读取数据连接超时时间
                    .build();
            //为httpPost实例设置配置
            httpPost.setConfig(requestConfig);

            //封装post请求参数
            if(null != paramMap && paramMap.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                //通过map集成entrySet方法获取entity
                Set<Entry<String, Object>> entrySet = paramMap.entrySet();
                //循环遍历，获取迭代器
                Iterator<Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> mapEntry = iterator.next();
                    nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
                }

                //为httpPost设置封装好的请求参数
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }

            //执行post请求得到返回对象
            response = httpClient.execute(httpPost);
            //通过返回对象获取数据
            HttpEntity entity = response.getEntity();
            //将返回的数据转换为字符串
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 带参数并指定编码的POST请求
     * @param url
     * @param paramMap
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String doPostByEncode(String url, Map<String,Object> paramMap, String charset) throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;

        //创建httpClient连接对象
        httpClient = HttpClients.createDefault();
        //创建post请求连接对象
        HttpPost httpPost = new HttpPost(url);
        //创建连接请求参数对象,并设置连接参数
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(15000)	//连接服务器主机超时时间
                .setConnectionRequestTimeout(60000)	//连接请求超时时间
                .setSocketTimeout(60000)	//设置读取响应数据超时时间
                .build();

        //为httpPost请求设置参数
        httpPost.setConfig(requestConfig);

        //判断参数是否为空
        if(null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvpsList = new ArrayList<NameValuePair>();
            //将map集成转换为Set集合
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            //通过EntrySet集合获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            //循环遍历
            while(iterator.hasNext()) {
                //遍历下一个
                Entry<String, Object> mapEntry = iterator.next();
                nvpsList.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            //将请求参数添加到post请求参数对象中
            //new UrlEncodedFormEntity(nvpsList, charset):将输入的参数指定成合适的字符集
            httpPost.setEntity(new UrlEncodedFormEntity(nvpsList, charset));
        }

        String result = "";
        try {
            //httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            //从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String doPostByXml(String url, String requestDataXml) {
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            String result = "";

            try {
                //创建httpClient实例
                httpClient = HttpClients.createDefault();
                //创建httpPost远程连接实例
                HttpPost httpPost = new HttpPost(url);
                //配置请求参数实例
                RequestConfig requestConfig = RequestConfig.custom()
                        .setConnectTimeout(35000)//设置连接主机服务超时时间
                        .setConnectionRequestTimeout(35000)//设置连接请求超时时间
                        .setSocketTimeout(60000)//设置读取数据连接超时时间
                        .build();
                //为httpPost实例设置配置
                httpPost.setConfig(requestConfig);

                //设置请求头信息
                httpPost.addHeader("Content-Type","text/xml");

                httpPost.setEntity(new StringEntity(requestDataXml,"UTF-8"));

                //封装post请求参数
            /*if(null != paramMap && paramMap.size() > 0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                //通过map集成entrySet方法获取entity
                Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
                //循环遍历，获取迭代器
                Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> mapEntry = iterator.next();
                    nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
                }

                //为httpPost设置封装好的请求参数
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }*/

                //执行post请求得到返回对象
                response = httpClient.execute(httpPost);
                //通过返回对象获取数据
                HttpEntity entity = response.getEntity();
                //将返回的数据转换为字符串
                result = EntityUtils.toString(entity);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //关闭资源
                if(null != response) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(null != httpClient) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }}

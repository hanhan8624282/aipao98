/**
 * 
 */
package com.ecc.aipao98.until;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zym
 * @date 2016-7-8 下午02:38:36 
 */
public class HttpUtils {

    private static final Logger logger              = LoggerFactory.getLogger(HttpUtils.class);

    private static final String DEFAULT_CHARSET     = "UTF-8";                                 // 缺省的字符编码
    private static final int    CONNECTION_TIME_OUT = 3000;                                    //3秒超时
    private static final int    SOCKET_TIME_OUT     = 3 * 60 * 1000;                           //3分钟socket超时
    private static final String HTTPS               = "https";

    public static HttpResult post(String url) {
        return post(url, new StringBuilder(), null, DEFAULT_CHARSET);
    }

    public static HttpResult post(String url, Map<String, String> params) {
        return post(url, params, null, DEFAULT_CHARSET);
    }

    public static HttpResult post(String url, Map<String, String> params, String charset) {
        return post(url, params, null, charset);
    }

    public static HttpResult post(String url, StringBuilder params) {
        return post(url, params, null, DEFAULT_CHARSET);
    }

    public static HttpResult post(String url, StringBuilder params, String charset) {
        return post(url, params, null, charset);
    }

    public static HttpResult postXml(String url, Map<String, String> params) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/xml");
        headers.put("Content-Type", "application/xml; charset=" + DEFAULT_CHARSET);
        headers.put("Accept", "application/xml");
        return post(url, params, headers, DEFAULT_CHARSET);
    }

    public static HttpResult postXml(String url, StringBuilder params) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/xml");
        headers.put("Content-Type", "application/xml; charset=" + DEFAULT_CHARSET);
        headers.put("Accept", "application/xml");
        return post(url, params, headers, DEFAULT_CHARSET);
    }

    public static HttpResult get(String url) {
        return get(url, new StringBuilder(), null, DEFAULT_CHARSET);
    }

    public static HttpResult get(String url, Map<String, String> params) {
        return get(url, params, null, DEFAULT_CHARSET);
    }

    public static HttpResult get(String url, Map<String, String> params, String charset) {
        return get(url, params, null, charset);
    }

    public static HttpResult get(String url, StringBuilder params) {
        return get(url, params, null, DEFAULT_CHARSET);
    }

    public static HttpResult get(String url, StringBuilder params, String charset) {
        return get(url, params, null, charset);
    }

    public static HttpResult getXml(String url, Map<String, String> params) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/xml");
        headers.put("Content-Type", "application/xml; charset=" + DEFAULT_CHARSET);
        headers.put("Accept", "application/xml");
        return get(url, params, headers, DEFAULT_CHARSET);
    }

    public static HttpResult getXml(String url, StringBuilder params) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/xml");
        headers.put("Content-Type", "application/xml; charset=" + DEFAULT_CHARSET);
        headers.put("Accept", "application/xml");
        return get(url, params, headers, DEFAULT_CHARSET);
    }

    public static HttpResult getXml(String url) {
        return getXml(url, new StringBuilder());
    }

    /**
     * post方法
     * @throws KeyStoreException 
     */
    public static HttpResult post(String url, Map<String, String> params,
                                  Map<String, String> headers, String charset) {

        HttpResult httpResult = HttpResult.buildSuccessResult();
        CloseableHttpClient httpClient = null;
        // 创建httppost    
        HttpPost httpPost = null;
        try {
            httpClient = getHttpClient(url);
            httpPost = getHttpPost(url, params, headers, charset);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            httpResult.setStatusCode(response.getStatusLine().getStatusCode());
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    httpResult.setContent(new StringBuilder(EntityUtils.toString(entity,
                        DEFAULT_CHARSET)));
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("http请求抛出异常，请求地址：{}，请求参数：{}", url, params, e);
            httpResult.setSuccess(false);
            httpResult.setResultCode("0");
            httpResult.setResultMsg(e.getMessage());
        } finally {
            // 关闭连接,释放资源    
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭链接抛出异常,{}", url, e);
            }
        }
        return httpResult;
    }

    /**
     * post方法
     * @throws KeyStoreException 
     */
    public static HttpResult post(String url, StringBuilder params, Map<String, String> headers,
                                  String charset) {

        HttpResult httpResult = HttpResult.buildSuccessResult();
        CloseableHttpClient httpClient = null;
        // 创建httppost    
        HttpPost httpPost = null;
        try {
            httpClient = getHttpClient(url);
            httpPost = getHttpPost(url, params, headers, charset);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            httpResult.setStatusCode(response.getStatusLine().getStatusCode());
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    httpResult.setContent(new StringBuilder(EntityUtils.toString(entity,
                        DEFAULT_CHARSET)));
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("http请求抛出异常，请求地址：{}，请求参数：{}", url, params, e);
            httpResult.setSuccess(false);
            httpResult.setResultCode("0");
            httpResult.setResultMsg(e.getMessage());
        } finally {
            // 关闭连接,释放资源    
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭链接抛出异常,{}", url, e);
            }
        }
        return httpResult;
    }

    /** 
     * 发送 get请求 
     */
    public static HttpResult get(String url, Map<String, String> params,
                                 Map<String, String> headers, String charset) {
        HttpResult httpResult = HttpResult.buildSuccessResult();
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient(url);
            // 创建httpget.    
            HttpGet httpGet = getHttpGet(url, params, headers, charset);
            // 执行get请求.    
            CloseableHttpResponse response = httpClient.execute(httpGet);
            httpResult.setStatusCode(response.getStatusLine().getStatusCode());
            try {
                // 获取响应实体    
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    httpResult.setContent(new StringBuilder(EntityUtils.toString(entity, charset)));
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("http请求抛出异常，请求地址：{}，请求参数：{}", url, params, e);
            httpResult.setSuccess(false);
            httpResult.setResultCode("0");
            httpResult.setResultMsg(e.getMessage());
        } finally {
            // 关闭连接,释放资源    
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭链接抛出异常,{}", url, e);
            }
        }
        return httpResult;
    }

    /** 
     * 发送 get请求 
     */
    public static HttpResult get(String url, StringBuilder params, Map<String, String> headers,
                                 String charset) {
        HttpResult httpResult = HttpResult.buildSuccessResult();
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient(url);
            // 创建httpget.    
            HttpGet httpGet = getHttpGet(url, params, headers, charset);
            // 执行get请求.    
            CloseableHttpResponse response = httpClient.execute(httpGet);
            httpResult.setStatusCode(response.getStatusLine().getStatusCode());
            try {
                // 获取响应实体    
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    httpResult.setContent(new StringBuilder(EntityUtils.toString(entity, charset)));
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("http请求抛出异常，请求地址：{}，请求参数：{}", url, params, e);
            httpResult.setSuccess(false);
            httpResult.setResultCode("0");
            httpResult.setResultMsg(e.getMessage());
        } finally {
            // 关闭连接,释放资源    
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭链接抛出异常,{}", url, e);
            }
        }
        return httpResult;
    }

    /**
     * SOAP数据格式发送数据
     * 默认编码URF-8
     */
    public static HttpResult soap(String url, String soapAction) {
        return soap(url, soapAction, DEFAULT_CHARSET);
    }

    /**
     * SOAP数据格式发送
     */
    public static HttpResult soap(String url, String sopaAction, String charset) {
        String params = "";
        int pos = url.indexOf("?");
        if (pos != -1) {
            params = url.substring(pos + 1, url.length());
            url = url.substring(0, pos);
        }
        return soap(url, new StringBuilder(params), sopaAction, charset);
    }

    /**
     * soap数据格式发送
     * 默认编码格式UTF-8
     */
    public static HttpResult soap(String url, StringBuilder soapXml, String soapAction) {
        return soap(url, soapXml, soapAction, DEFAULT_CHARSET);
    }

    /**
     * soap数据格式发送
     */
    public static HttpResult soap(String url, StringBuilder soapXml, String soapAction,
                                  String charset) {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/xml; charset=" + DEFAULT_CHARSET);
        headers.put("SOAPAction", soapAction);
        return post(url, soapXml, headers, DEFAULT_CHARSET);
    }

    /**
     * 获取httpClient
     */
    public static CloseableHttpClient getHttpClient(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        if (url.startsWith(HTTPS)) {
            // 相信自己的CA和所有自签名的证书 
            SSLContext sslcontext = getSSLContext();
            // 只允许使用TLSv1协议  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                new String[] { "TLSv1", "TLSv1.2" }, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    /**
     * 创建SSL Content对象，并使用指定信任管理器初始化
     */
    public static SSLContext getSSLContext() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        TrustManager[] trustManagers = { new UnicomX509TrustManager() };
        sslContext.init(null, trustManagers, new java.security.SecureRandom());
        return sslContext;
    }

    /**
     * 获取httpPost
     */
    public static HttpPost getHttpPost(String url, Map<String, String> params,
                                       Map<String, String> headers, String charset)
                                                                                   throws Exception {
        // 创建httppost    
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
            .setConnectTimeout(CONNECTION_TIME_OUT).build();
        httpPost.setConfig(requestConfig);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 创建参数队列    
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        HttpEntity httpEntity = new UrlEncodedFormEntity(formparams, charset);
        httpPost.setEntity(httpEntity);
        return httpPost;
    }

    /**
     * 获取httpPost
     */
    public static HttpPost getHttpPost(String url, StringBuilder params,
                                       Map<String, String> headers, String charset)
                                                                                   throws Exception {
        // 创建httppost    
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
            .setConnectTimeout(CONNECTION_TIME_OUT).build();
        httpPost.setConfig(requestConfig);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity httpEntity = new StringEntity(params.toString(), charset);
        httpPost.setEntity(httpEntity);
        return httpPost;
    }

    public static HttpGet getHttpGet(String url, Map<String, String> params,
                                     Map<String, String> headers, String charset) throws Exception {
        StringBuilder sb = null;
        if (params != null) {
            sb = toStrParam(params);
        } else {
            sb = new StringBuilder();
        }
        return getHttpGet(url, sb, headers, charset);
    }

    public static HttpGet getHttpGet(String url, StringBuilder params, Map<String, String> headers,
                                     String charset) throws Exception {
        if (params != null) {
            int pos = url.indexOf("?");
            if (pos >= 0) {
                if (pos + 1 < url.length()) {
                    params.append("&").append(url.substring(pos + 1));
                }
                url = url.substring(0, pos);
            }
            url += "?" + params.toString();
        }
        // 创建httpget.    
        HttpGet httpGet = new HttpGet(url);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
            .setConnectTimeout(CONNECTION_TIME_OUT).build();
        httpGet.setConfig(requestConfig);
        return httpGet;
    }

    /**
     * 转换成字符串
     */
    public static StringBuilder toStrParam(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : params.entrySet()) {
            sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }

        if (sb.length() > 0) {
            return new StringBuilder(sb.substring(0, sb.length() - 1));
        } else {
            return sb;
        }
    }

    /**
     * 转换为map
     */
    public static Map<String, String> toMapParam(StringBuilder params) {
        Map<String, String> map = new HashMap<String, String>();
        String[] a = params.toString().split("&");
        for (String str : a) {
            String[] b = str.split("=");
            map.put(b[0], b[1]);
        }
        return map;
    }

}

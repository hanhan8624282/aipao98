package com.ecc.aipao98.until;

import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class UnicomNetwork {

    private static final String CONTENT_CHARSET = "UTF-8";
    private static final int CONNECTION_TIMEOUT = 130000;
    private static final int READ_DATA_TIMEOUT = 130000;

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
     * 发送Https请求
     */
    public static StringBuilder request(String url, StringBuilder params, String method, String contentType,
            String charset) throws Exception {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder content = new StringBuilder();
        try {
            if (StringUtils.isBlank(charset)) {
                charset = CONTENT_CHARSET;
            }
            // 创建URL对象
            URL serverUrl = new URL(url);
            // 创建HttpsURLConnection对象
            HttpsURLConnection connection = (HttpsURLConnection) serverUrl.openConnection();
            SSLContext sslContext = getSSLContext();
            // 设置其SSLSocketFactory对象
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            connection.setSSLSocketFactory(socketFactory);
            // Ignore No subject alternative names
            connection.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            // 设置建立超时时间
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            // Content-Type
            if (StringUtils.isNotBlank(contentType)) {
                connection.setRequestProperty("Content-Type", contentType + "; charset=" + charset);
            }
            // 设置读取数据超时时间
            connection.setReadTimeout(READ_DATA_TIMEOUT);
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            if (params != null) {
                out = new OutputStreamWriter(connection.getOutputStream(), charset);
                out.write(params.toString());
                out.flush();
            }
            if (connection.getResponseCode() != 200) {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), charset));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            }
            // 生成返回内容
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    // Ignore Out Close Exception
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // Ignore In Close Exception
                }
            }
        }
        return content;
    }
}

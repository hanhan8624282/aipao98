/**
 * 
 */
package com.ecc.aipao98.until;

import java.io.Serializable;

/**
 * @author zym
 * @date 2015-6-18 下午06:27:59 
 */
public class HttpResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean           success;
    /** 结果码 */
    private String            resultCode;
    /** 结果消息 */
    private String            resultMsg;
    protected StringBuilder   content          = new StringBuilder(); // http请求返回的内容
    protected int             statusCode;                            // 状态码

    public HttpResult() {
        super();
    }

    public HttpResult(boolean success, StringBuilder content) {
        this.setSuccess(success);
        this.content = content;
    }

    public static HttpResult buildSuccessResult(StringBuilder content) {
        HttpResult result = new HttpResult();
        result.setSuccess(true);
        result.setContent(content);
        return result;
    }

    public static HttpResult buildSuccessResult() {
        HttpResult result = new HttpResult();
        result.setSuccess(true);
        return result;
    }

    public static HttpResult buildFailedResult(String errCode, String errMsg) {
        HttpResult result = new HttpResult();
        result.setSuccess(false);
        result.setResultCode(errCode);
        result.setResultMsg(errMsg);
        return result;
    }

    /**
     * @return the content
     */
    public StringBuilder getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(StringBuilder content) {
        this.content = content;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * @param resultMsg the resultMsg to set
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}

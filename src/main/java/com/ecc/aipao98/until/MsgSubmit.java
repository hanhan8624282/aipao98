/**
 * 
 */
package com.ecc.aipao98.until;

import java.io.Serializable;

/**
 * @author zengyongming
 * @date 2020-5-15 下午02:05:27 
 */
public class MsgSubmit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            userName;             // 用户名
    private String            sign;                 // 签名 Md5(userName,passwd,mobile,content)
    private String            mobile;               // 多个号码用英文逗号隔开。最多不超过1万个
    private String            content;              // 短信内容
    private String            seqid;                // 流水号 如果不指定则系统指定.不要带|字符
    private String            ext;                  // 扩展号 106码号后面扩展的部分
    private String            extraData;            // 用户扩展数据.报告交付时原样返回.不要带|字符
    private Integer           isms;                 // 是否是国际短信 1-是(港澳台国际短信) 0-否(不包含港澳台的国内短信) 默认不填则为否
    private String            dstime;               // 定时时间  时间格式 yyyy-MM-dd HH:mm:ss

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the seqid
     */
    public String getSeqid() {
        return seqid;
    }

    /**
     * @param seqid the seqid to set
     */
    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    /**
     * @return the ext
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext the ext to set
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * @return the extraData
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     * @param extraData the extraData to set
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    /**
     * @return the isms
     */
    public Integer getIsms() {
        return isms;
    }

    /**
     * @param isms the isms to set
     */
    public void setIsms(Integer isms) {
        this.isms = isms;
    }

    /**
     * @return the dstime
     */
    public String getDstime() {
        return dstime;
    }

    /**
     * @param dstime the dstime to set
     */
    public void setDstime(String dstime) {
        this.dstime = dstime;
    }

}

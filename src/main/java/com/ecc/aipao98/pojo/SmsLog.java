package com.ecc.aipao98.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author sunyc
 * @create 2022-08-16 8:32  发送验证码流水表
 */
@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "sms_log")
public class SmsLog {
    private Date createTime;
    private String cellPhone;
    private String ip;
    private Integer code;
    private String resultMessage;
    private String resultStatus;
    private String resultId;
}

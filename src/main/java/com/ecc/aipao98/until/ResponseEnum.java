package com.ecc.aipao98.until;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author sunyc
 * @create 2022-06-30 14:07
 */
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public enum ResponseEnum {

    SUCCESS(0, "成功"),
    ERROR(-1, "服务器内部错误"),
    USER_BIND_IDCARD_EXIST_ERROR(-301, "身份证号码已绑定"),
    BAD_SQL_GRAMMAR_ERROR(-101,"sql语法错误" ),
    BORROW_AMOUNT_NULL_ERROR(-201, "借款额度不能为空"),
    SERVLET_ERROR(-102,"servlet请求异常"),
    STRING_TRANS_ERROR(-202,"字符转换失败"),
    LOGIN_AUTH_ERROR(-211, "未登录"),
    UPLOAD_ERROR(-103, "文件上传错误"),
    MOBILE_NULL_ERROR(-202,"手机号不能为空"),
    MOBILE_ERROR(-203,"手机号不正确"),
    EXPORT_DATA_ERROR(-104,"数据导出失败" ),
    PASSWORD_NULL_ERROR(-205, "验证码不能为空"),
    MOBILE_EXIST_ERROR(-207, "手机号已被注册"),
    LOGIN_DISABLED_ERROR(-210, "用户已被禁用"),
    LOGIN_PASSWORD_ERROR(-209, "密码不正确"),
    LOGIN_MOBILE_ERROR(-208, "用户不存在"),
    CODE_ERROR(-206, "验证码不正确"),
    USER_NO_BIND_ERROR(-401,"用户未绑定"),
    LEND_INVEST_ERROR(-404,"这个标的状态已经不是募集中"),
    LEND_FULL_SCALE_ERROR(-405,"超出标的募集金额"),
    USER_NO_AMOUNT_ERROR(-402,"用户信息审核未通过"),
    USER_AMOUNT_LESS_ERROR(-403,"用户贷款金额不足"),
    CODE_NULL_ERROR(-205, "验证码不能为空"),
    NOT_SUFFICIENT_FUNDS_ERROR(-406,"账户余额不足以投资" );
    // 响应状态码MOBILE_NULL_ERROR
    private Integer code;
    // 响应信息
    private String message;
}

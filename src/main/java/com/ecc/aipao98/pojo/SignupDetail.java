package com.ecc.aipao98.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author sunyc
 * @create 2022-08-16 8:27  报名表
 */
@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "signup_detail")
public class SignupDetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Date createDate;
    private String cellPhone;
    private String sourceChannel;
    private String provinceId;
}

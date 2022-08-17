package com.ecc.aipao98.dao;

import com.ecc.aipao98.pojo.SignupDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sunyc
 * @create 2022-08-16 8:36
 */
@Repository
public interface SignupDetailDao {
    /**
    *@Description: 保存报名数据
    *@Param:
    *@return:
    *@Author: your name
    *@date: 2022/8/16
    */
    @Transactional(rollbackFor = Exception.class)
    @Insert("insert into signup_detail() values(#{create_date},#{cell_phone},#{source_channel},#{province_id})")
    public int insert(SignupDetail signupDetail);

    /**
    *@Description: 手机号是否已报名 大于0 已报名
    *@Param: 
    *@return: 
    *@Author: your name
    *@date: 2022/8/16
    */
    @Select("select count(*)\n" +
            "from signup_detail a\n" +
            "where cell_phone=#{phone}")
    int checkPhone(String phone);
}

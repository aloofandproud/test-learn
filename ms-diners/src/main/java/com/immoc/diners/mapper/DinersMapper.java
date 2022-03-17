package com.immoc.diners.mapper;

import com.imooc.commons.model.dto.DinersDTO;
import com.imooc.commons.model.pojo.Diners;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Service
public interface DinersMapper {
    @Select("select id,username,phone,email,is_valid from t_diners where phone = #{phone}")
    Diners selectByPhone(@Param("phone") String phone);

    @Select("select id,username,phone,email,is_valid from t_diners where username = #{username}")
    Diners selectByUsername(@Param("username") String username);

    @Insert("insert into " +
            " t_diners (username, password, phone, roles, is_valid, create_date, update_date) " +
            " values (#{username}, #{password}, #{phone}, \"ROLE_USER\", 1, now(), now())")
    int save(DinersDTO dinersDTO);

}

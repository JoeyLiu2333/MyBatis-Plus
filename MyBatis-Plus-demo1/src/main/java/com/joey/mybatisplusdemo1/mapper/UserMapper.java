package com.joey.mybatisplusdemo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joey.mybatisplusdemo1.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @MapKey("id")
    Map<String, Object> selectMapById(Long id);
}

package com.joey.mybatisplusdemo1.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joey.mybatisplusdemo1.Service.UserService;
import com.joey.mybatisplusdemo1.mapper.UserMapper;
import com.joey.mybatisplusdemo1.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

package com.joey.mybatisplusdemo1;

import com.joey.mybatisplusdemo1.mapper.UserMapper;
import com.joey.mybatisplusdemo1.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("123@163.com");
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
        System.out.println("id = " + user.getId());
    }

    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(1519847901526315010L);
        System.out.println("i = " + i);
    }

    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        int i = userMapper.deleteByMap(map);
        System.out.println("i = " + i);
    }

    @Test
    public void testDeleteBatchIds() {
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int i = userMapper.deleteBatchIds(list);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(3L);
        user.setEmail("update@163.com");
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println("user = " + user);
    }

    @Test
    public void testSelectSelfDefined() {
        Map<String, Object> map = userMapper.selectMapById(2L);
        System.out.println("map = " + map);
    }


}

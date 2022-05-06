package com.joey.mybatisplusdemo1;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.joey.mybatisplusdemo1.mapper.UserMapper;
import com.joey.mybatisplusdemo1.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectList() {
        // SELECT id,name,age,email,is_deleted
        // FROM user WHERE is_deleted=0
        // AND (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").between("age", 20, 30).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectListByOrder() {
        // SELECT id,name,age,email,is_deleted
        // FROM user WHERE is_deleted=0
        // ORDER BY age DESC,id DESC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void deleteByQuery() {
        // UPDATE user
        // SET is_deleted=1 WHERE is_deleted=0
        // AND (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int i = userMapper.delete(queryWrapper);
        System.out.println("i = " + i);
    }

    @Test
    public void updateByQuery() {
        // UPDATE user
        // SET age=?
        // WHERE is_deleted=0
        // AND (name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "number").and(w -> w.gt("age", 30).or().isNull("email"));
        User user = new User();
        user.setAge(10);
        int i = userMapper.update(user, queryWrapper);
        System.out.println("i = " + i);
    }

    @Test
    public void selectMaps() {
        // SELECT name,age,email FROM user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void inSql() {
        // SELECT id,name,age,email,is_deleted
        // FROM user
        // WHERE is_deleted=0
        // AND (id IN (select id from user where id <= 100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void update() {
        // UPDATE user
        // SET name=?,age=?
        // WHERE is_deleted=0
        // AND (name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "a").and(w -> w.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "目标用户").set("age", -1);
        int update = userMapper.update(null, updateWrapper);
        System.out.println("update = " + update);

    }

    @Test
    public void conditional() {
        String name = "";
        Integer ageLowerMargin = null;
        Integer ageUpperMargin = 50;
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like(StringUtils.isNotBlank(name), "name", "a")
                .gt(!ageLowerMargin.equals(null), "age", ageLowerMargin)
                .lt(!ageUpperMargin.equals(null), "age", ageUpperMargin);
        List<User> users = userMapper.selectList(updateWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void lambdaWrapper() {
        // SELECT id,name,age,email,is_deleted FROM user
        // WHERE is_deleted=0 AND (name LIKE ?)
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, "a");
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }
}

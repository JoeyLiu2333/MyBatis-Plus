package com.joey.mybatisplusdemo1;

import com.joey.mybatisplusdemo1.Service.UserService;
import com.joey.mybatisplusdemo1.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testCount() {
        long count = userService.count();
        System.out.println("count = " + count);
    }

    @Test
    public void testBatchSave() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            User user = new User();
            user.setName("Number" + i);
            user.setAge(30 + i);
            list.add(user);
        }
        boolean ret = userService.saveBatch(list);
        System.out.println("ret = " + ret);
    }
}

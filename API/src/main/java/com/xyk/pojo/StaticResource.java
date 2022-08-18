package com.xyk.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-08-09 15:21
 */
public class StaticResource {
    public static List<User> userList = new ArrayList<>();
    static {
        // 创建用户
        User user01 = new User();
        user01.setId(1).setName("张三");
        User user02 = new User();
        user02.setId(2).setName("李四");
        User user03 = new User();
        user03.setId(3).setName("王五");
        User user04 = new User();
        user04.setId(4).setName("张三十");
        User user05 = new User();
        user05.setId(5).setName("李四十");
        User user06 = new User();
        user06.setId(6).setName("张三百");
        // 将三级数据封装到二级数据中
        user04.setChildren(Arrays.asList(user06));
        // 将二级数据封装到一级数据中
        user01.setChildren(Arrays.asList(user04));
        user02.setChildren(Arrays.asList(user05));
        // 将一级数据封装到集合中
        userList.add(user01);
        userList.add(user02);
        userList.add(user03);
    }


}

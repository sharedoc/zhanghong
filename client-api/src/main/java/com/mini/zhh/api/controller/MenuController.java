package com.mini.zhh.api.controller;

import com.mini.zhh.bean.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 菜单controller
 * @Author zunqiaozhang
 * @Date 2021/11/03
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static List<Menu> menuList = new ArrayList<>();

    static {
        menuList.add(Menu.builder().id(1).name("汤粉").desc("主要材料...").price(8.0F).img("01.JPG").build());
        menuList.add(Menu.builder().id(2).name("拌粉").desc("主要材料...").price(12.0F).img("02.JPG").build());
//        menuList.add(Menu.builder().id(3).name("肉末").desc("主要材料肉...").price(3.0F).img("03.JPG").build());
    }

    /**
     * 拉取菜单列表
     *
     * @return
     */
    @PostMapping(value = "/pullMenuList")
    public List<Menu> pullMenuList() {
        return menuList;
    }

    @GetMapping(value = "/db")
    public void testDb() {
        jdbcTemplate.execute("insert into test(`name`) values('zhangsan');");
    }

}

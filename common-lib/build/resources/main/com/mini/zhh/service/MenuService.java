package com.mini.zhh.service;

import com.mini.zhh.bean.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 菜单服务层
 * @Author zunqiaozhang
 * @Date 2021/11/12
 */
@Service
public class MenuService {

    /**
     * redis的操作
     */
    @Autowired
    private MenuOpt menuOptRedisImpl;

    /**
     * 拉取菜单
     *
     * @return
     */
    public List<Menu> pullMenuList() {
        return menuOptRedisImpl.getMenuList();
    }


}

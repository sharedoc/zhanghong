package com.mini.zhh.service.impl;

import com.mini.zhh.bean.Menu;
import com.mini.zhh.service.MenuOpt;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 菜单（MySQL实现）
 * @Author zunqiaozhang
 * @Date 2021/11/12
 */
@Service
public class MenuOptMysqlImpl implements MenuOpt {

    @Override
    public List<Menu> getMenuList() {
        return null;
    }

    @Override
    public int editMenuInfo(int id, Menu newMenu) {
        return 0;
    }

    @Override
    public int addMenuInfo(Menu menu) {
        return 0;
    }

    @Override
    public int deleteMenu(int id) {
        return 0;
    }

}

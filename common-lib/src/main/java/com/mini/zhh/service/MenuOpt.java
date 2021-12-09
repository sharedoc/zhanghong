package com.mini.zhh.service;

import com.mini.zhh.bean.Menu;

import java.util.List;

/**
 * @Description 菜单操作接口
 * @Author zunqiaozhang
 * @Date 2021/11/12
 */
public interface MenuOpt {

    /**
     * 获取菜单列表
     *
     * @return
     */
    List<Menu> getMenuList();

    /**
     * 修改菜单信息
     *
     * @param id
     * @param newMenu
     * @return
     */
    int editMenuInfo(int id, Menu newMenu);

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    int addMenuInfo(Menu menu);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteMenu(int id);

}

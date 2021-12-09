package com.mini.zhh.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 菜单信息
 * @Author zunqiaozhang
 * @Date 2021/11/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    // id
    private Integer id;
    // 名称
    private String name;
    // 描述
    private String desc;
    // 单价
    private Float price;
    // 图片
    private String img;
}

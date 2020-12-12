package com.workStudy.core.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author JZhi
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 网址
     */
    private String url;

    /**
     * 显示的图标
     */
    private String icon;

    /**
     * 类型，0 菜单，1 连接网址,2 隐藏连接
     */
    private String menutype;

    /**
     * 显示排序
     */
    private Integer display;

    /**
     * 父级的id，引用本表id字段
     */
    private Integer parentid;

    /**
     * 创建者id，0为超级管理员
     */
    private Integer creator;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 更新者id
     */
    private Integer updateuser;

    /**
     * 更新时间
     */
    private LocalDateTime updatetime;

    /**
     * 是否启用，0 禁用，1启用
     */
    private String flag;


}

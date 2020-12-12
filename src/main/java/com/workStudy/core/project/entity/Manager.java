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
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家id
     */
    @TableId(value = "manager_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家密码
     */
    private String password;

    /**
     * 商家地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 商家主营
     */
    private String project;

    /**
     * 营业执照
     */
    private String busLice;

    /**
     * 信用分数
     */
    private Integer credit;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 商家状态(0,正常；1，禁用；2，删除）
     */
    private Integer state;
}

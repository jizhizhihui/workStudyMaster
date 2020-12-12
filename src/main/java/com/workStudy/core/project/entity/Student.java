package com.workStudy.core.project.entity;

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
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    @TableId(value = "student_id")
    private String id;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 密码
     */
    private String password;

    /**
     * 学校名
     */
    private String schoolName;

    /**
     * 性别(1,男；2，女；0，未填写）
     */
    private Boolean sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 信用分数
     */
    private Integer credit;

    /**
     * 状态（0，正常；1，禁用；-1，删除）
     */
    private Integer state;

    /**
     *  创建时间
     */
    private LocalDateTime createTime;

    /**
     * 专业
     */
    private String major;


}

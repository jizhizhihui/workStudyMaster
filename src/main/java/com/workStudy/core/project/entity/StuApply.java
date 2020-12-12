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
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_stu_apply")
public class StuApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生申请ID
     */
    @TableId(value = "stu_apply_id", type = IdType.AUTO)
    private Integer stuApplyId;

    /**
     * 学生学号
     */
    private String studentId;

    /**
     * 岗位id
     */
    private Integer postId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 审核时间
     */
    private LocalDateTime examineTime;

    /**
     * 状态（0，等待处理；1，通过；-1，驳回）
     */
    private Integer state;


}

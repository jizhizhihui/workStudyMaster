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
@TableName("t_job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工作表id
     */
    @TableId(value = "job_id", type = IdType.AUTO)
    private Integer jobId;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 岗位id
     */
    private Integer postId;

    /**
     * 商家id
     */
    private Integer managerId;

    /**
     * 工作进度(0,领取任务；1，任务完成；2，任务失败)
     */
    private Integer workProgress;

    /**
     * 验收(0,等待验收；1，验收成功；2，验收失败)
     */
    private Integer accCheck;

    /**
     * 学生备注
     */
    private String stuRemarks;

    /**
     * 商家备注
     */
    private String manRemarks;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 验收时间
     */
    private LocalDateTime accCheckTime;


}

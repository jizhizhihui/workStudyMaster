package com.workStudy.core.project.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
* 
* </p>
*
* @author JZhi
* @since 2020-06-03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 岗位id
    */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    /**
    * 岗位名
    */
    private String postName;

    /**
    * 岗位数量
    */
    private Integer postNum;

    /**
    * 岗位要求
    */
    private String requirement;

    /**
    * 工作内容
    */
    private String content;

    /**
    * 工作地点
    */
    private String address;

    /**
    * 工作开始时间
    */
    private LocalDateTime strTime;

    /**
    * 工作结束时间
    */
    private LocalDateTime endTime;

    /**
    * 报酬
    */
    private String reward;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 商家id
    */
    private Integer managerId;

    /**
    * 岗位状态(0,正常；1，已满，-1，岗位待审核）
    */
    private Integer state;

    /**
    * 发布时间
    */
    private LocalDateTime relTime;


}
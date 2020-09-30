package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "work_test")
public class Works extends Model<Works> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 任务编号
     */
    @TableField("task_no")
    private String taskNo;
    /**
     * 计划名称
     */
    @TableField("task_name")
    private String taskName;
    /**
     * 任务地点
     */
    @TableField("work_place")
    private String workPlace;
    /**
     * 任务内容
     */
    @TableField("task_content")
    private String taskContent;
    /**
     * 任务开始时间
     */
    @TableField("start_time")
    private String startTime;
    /**
     * 任务结束时间
     */
    @TableField("end_time")
    private String endTime;
    /**
     * 工作人员数量
     */
    @TableField("connected_people_nums")
    private String connectedPeopleNums;



}

package com.jclyzx.job.quartz.job.datasource.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("qrtz_triggers")
public class QuartzTriggerDO implements Serializable{

	/**
	 * @author jclyzx
	 * @time 2022-04-02 14:55:23
	 */
	private static final long serialVersionUID = 1L;
	
	private String schedName;
	private String triggerName;
	private String triggerGroup;
	private String jobName;
	private String jobGroup;
	private String description;
	private Long nextFireTime;
	private Long prevFireTime;
	private Integer priority=0;
	private String triggerState;
	private String triggerType;
	private Long startTime;
	private Long 	endTime;
	private String calendarName;
	private Integer misfireInstr;
}

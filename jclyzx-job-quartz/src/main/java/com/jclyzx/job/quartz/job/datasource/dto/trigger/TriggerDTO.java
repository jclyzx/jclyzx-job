package com.jclyzx.job.quartz.job.datasource.dto.trigger;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 触发器配置
 * @author jclyzx
 * @time 2022-04-02 16:47:02
 */
@Data
public class TriggerDTO implements Serializable{

	/**
	 * @author jclyzx
	 * @time 2022-04-02 15:11:22
	 */
	private static final long serialVersionUID = 1L;
	
	/*---------------公共参数start----------------*/
	@ApiModelProperty(value = "触发器名称", required = true, example = "triggerName")
	@NotNull
	private String name;
	
	@ApiModelProperty(value = "触发器组名称", required = true, example = "triggerGroup")
	@NotNull
	private String group;
	
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	
	@ApiModelProperty(value = "结束时间")
	private Date endTime;
	/*---------------公共参数end----------------*/
	
	/*---------------simple参数end----------------*/
	@ApiModelProperty(value = "执行次数",required = true, example = "1")
	private int repeatCount;
	
	@ApiModelProperty(value = "执行间隔(单位:s)",required = true, example = "1")
	private int repeatInterval;
	/*---------------simple参数start----------------*/
	
	/*---------------cron参数start----------------*/
	@ApiModelProperty(value = "cron表达式",required = true, example = "0/1 * * * * ?")
	private String cron;
	/*---------------cron参数end----------------*/
	
}

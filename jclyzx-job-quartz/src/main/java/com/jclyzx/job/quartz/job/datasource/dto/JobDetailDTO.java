package com.jclyzx.job.quartz.job.datasource.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 任务执行执行逻辑配置
 * @author jclyzx
 * @time 2022-04-02 16:48:02
 */
@Data
public class JobDetailDTO implements Serializable{
	
	/**
	 * @author jclyzx
	 * @time 2022-04-02 15:09:16
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "类名(全路径)" ,required = true, example = "com.jclyzx.job.quartz.job.simple.MainJob")
	@NotNull
	private String className;
	
	@ApiModelProperty(value = "任务名称", required = true, example = "jobName")
	@NotNull
	private String jobName;
	
	@ApiModelProperty(value = "分组名称", required = true, example = "groupName")
	@NotNull
	private String groupName;
	
}

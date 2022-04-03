package com.jclyzx.job.quartz.job.datasource.dto;


import com.jclyzx.job.quartz.job.datasource.dto.trigger.TriggerDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RequestDTO {
	
	@ApiModelProperty(value = "类型，0:simple,1:cron",example = "1")
	private int type = 1;
	
	private JobDetailDTO job;
	
	private TriggerDTO trigger;
}

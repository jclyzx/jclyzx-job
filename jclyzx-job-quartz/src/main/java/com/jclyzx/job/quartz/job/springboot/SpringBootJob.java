package com.jclyzx.job.quartz.job.springboot;

import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * springboot直接集成定时任务，配合QuartzConfig使用
 * @author jclyzx
 * @time 2022-04-03 17:54:43
 */
public class SpringBootJob extends QuartzJobBean{
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("SpringBootJob---"+LocalDateTime.now());
	}

}

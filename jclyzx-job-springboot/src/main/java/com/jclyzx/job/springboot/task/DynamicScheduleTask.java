package com.jclyzx.job.springboot.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

@Component
public class DynamicScheduleTask implements SchedulingConfigurer {

	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 可以使用数据库形式动态修改
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(
				createRunnable("com.xx.xxx","xxx"),
				triggerContext ->new CronTrigger("0/5 * * * *").nextExecutionTime(triggerContext));
		taskRegistrar.addTriggerTask(()->System.out.println("动态执行任务---"+LocalDateTime.now()),
				triggerContext ->new CronTrigger("0/5 * * * *").nextExecutionTime(triggerContext));
	}
	
	protected Runnable createRunnable(String classStr, String methodStr) {
		try {
			Object bean = applicationContext.getBean(Class.forName(classStr));
			return new ScheduledMethodRunnable(bean, methodStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}


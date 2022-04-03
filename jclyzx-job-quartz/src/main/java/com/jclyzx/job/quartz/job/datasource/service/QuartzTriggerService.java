package com.jclyzx.job.quartz.job.datasource.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jclyzx.job.quartz.job.datasource.dto.RequestDTO;
import com.jclyzx.job.quartz.job.datasource.dto.trigger.TriggerDTO;
import com.jclyzx.job.quartz.job.datasource.entity.QuartzTriggerDO;

public interface QuartzTriggerService extends IService<QuartzTriggerDO>{

		/**
		 * 根据 Quartz Cron Expression 调度任务
		 * @param jdm 封装Job数据的model
		 * @param triggerName  Quartz CronTrigger名称
		 * @param triggerGroup Quartz CronTrigger组
		 * @param cronExpression   Quartz Cron 表达式，如 "0/10 * * ? * * *"等
		 */
		public void schedule(RequestDTO dto);


		/**
		 * 暂停触发器运行
		 */
		public void pauseTrigger(TriggerDTO td);

		/**
		 * 恢复触发器运行
		 */
		public void resumeTrigger(TriggerDTO td);
		/**
		 * 删除触发器
		 * @return
		 */
		public boolean removeTrigdger(TriggerDTO td);
		/**
		 * 立即运行一次
		 */
		public void triggerJob(TriggerDTO td);
	
}
package com.jclyzx.job.quartz.job.datasource.service.impl;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jclyzx.job.quartz.job.datasource.dto.JobDetailDTO;
import com.jclyzx.job.quartz.job.datasource.dto.RequestDTO;
import com.jclyzx.job.quartz.job.datasource.dto.trigger.TriggerDTO;
import com.jclyzx.job.quartz.job.datasource.entity.QuartzTriggerDO;
import com.jclyzx.job.quartz.job.datasource.mapper.QuartzTriggerMapper;
import com.jclyzx.job.quartz.job.datasource.service.QuartzTriggerService;

@Service
public class QuartzTriggerServiceImpl extends ServiceImpl<QuartzTriggerMapper, QuartzTriggerDO>
		implements QuartzTriggerService {

	@Autowired
	private Scheduler scheduler;

	@Override
	public void schedule(RequestDTO dto) {
		// 创建jobDetail
		JobDetail jobDetail = this.createJobDetail(dto.getJob());
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		TriggerDTO triggerDTO = dto.getTrigger();
		
		Date startTime = triggerDTO.getStartTime();
		triggerBuilder.startAt(startTime==null?new Date():startTime);
		triggerBuilder.endAt(triggerDTO.getEndTime());
		triggerBuilder.forJob(jobDetail.getKey());
		
		TriggerKey triggerKey  = getTriggerKey(triggerDTO);;
		switch (dto.getType()) {
		case 0: //SimpleTrigger
			triggerBuilder.withSchedule(
					SimpleScheduleBuilder.repeatSecondlyForTotalCount(
							triggerDTO.getRepeatInterval(), 
							triggerDTO.getRepeatCount()));
			break;
		case 1: //CronTrigger
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(triggerDTO.getCron()));
			break;
		default:
			triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule());
			break;
		}
		triggerBuilder.withIdentity(triggerKey);
		try {
			scheduler.addJob(jobDetail, true);
			Trigger trigger = triggerBuilder.build();
			if (scheduler.checkExists(triggerKey)) {
				scheduler.rescheduleJob(triggerKey, trigger);
			} else {
				scheduler.scheduleJob(trigger);
			}
		} catch (SchedulerException e) {
			log.error("",e);
		}
	}

	@Override
	public void pauseTrigger(TriggerDTO td) {
		try {
			scheduler.pauseTrigger(getTriggerKey(td));
		} catch (SchedulerException e) {
			log.error("",e);
		}
	}

	@Override
	public void resumeTrigger(TriggerDTO td) {
		try {
			scheduler.resumeTrigger(getTriggerKey(td));
		} catch (SchedulerException e) {
			log.error("",e);
		}
	}
 
	@Override
	public boolean removeTrigdger(TriggerDTO td) {
		TriggerKey triggerKey = getTriggerKey(td);
		try {
			scheduler.pauseTrigger(triggerKey);
			return scheduler.unscheduleJob(triggerKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void triggerJob(TriggerDTO td) {
		try {
			Trigger t = scheduler.getTrigger(getTriggerKey(td));
			scheduler.triggerJob(t.getJobKey());
		} catch (SchedulerException e) {
			log.error("",e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JobDetail createJobDetail(JobDetailDTO jdm) {
		JobDetail myJob = null;
		try {// 先查找一下
			Class cls = Class.forName(jdm.getClassName());
			myJob = JobBuilder.newJob(cls).withIdentity(jdm.getJobName(), jdm.getGroupName()).storeDurably().build();
		} catch (Exception err) {
			log.error("",err);
		}
		return myJob;
	}
	
	private TriggerKey getTriggerKey(TriggerDTO td) {
		return new TriggerKey(td.getName(), td.getGroup());
	}
}

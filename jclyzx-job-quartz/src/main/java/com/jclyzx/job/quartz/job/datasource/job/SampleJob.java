package com.jclyzx.job.quartz.job.datasource.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例不带参定时任务
 * @author jclyzx
 * @time 2022-04-01 18:18:08
 */
@Slf4j
public class SampleJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format(" 普通定时任务 SampleJob !  时间:" + LocalDateTime.now()));
    }
    
}

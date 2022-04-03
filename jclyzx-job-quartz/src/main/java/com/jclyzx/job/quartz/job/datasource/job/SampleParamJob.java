package com.jclyzx.job.quartz.job.datasource.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例带参定时任务
 * @author jclyzx
 * @time 2022-04-01 18:31:59
 */
@Slf4j
public class SampleParamJob implements Job {

    /**
     * 若参数变量名修改 QuartzJobController中也需对应修改
     */
    private String parameter;

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info(String.format("welcome %s! Jeecg-Boot 带参数定时任务 SampleParamJob !   时间:" + LocalDateTime.now(), this.parameter));
    }
}

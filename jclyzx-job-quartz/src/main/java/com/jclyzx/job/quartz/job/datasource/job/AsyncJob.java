package com.jclyzx.job.quartz.job.datasource.job;

import java.time.LocalDateTime;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import lombok.extern.slf4j.Slf4j;

/**
 * 此处的同步是指 当定时任务的执行时间大于任务的时间间隔时
 * 会等待第一个任务执行完成才会走第二个任务
 * @author jclyzx
 * @time 2022-04-01 18:31:45
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class AsyncJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(" --- 同步任务调度开始 --- ");
//        try {
//            //此处模拟任务执行时间 5秒  任务表达式配置为每秒执行一次：0/1 * * * * ? *
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("定时任务");
        //测试发现 每5秒执行一次
        log.info(" --- 执行完毕，时间：" + LocalDateTime.now() + "---");
    }

}

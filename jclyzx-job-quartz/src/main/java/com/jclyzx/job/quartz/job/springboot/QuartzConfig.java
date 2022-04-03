//package com.jclyzx.job.quartz.job.springboot;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class QuartzConfig {
//    
//    @Bean
//    public JobDetail jobDetail(){
//        return JobBuilder.newJob(SpringBootJob.class)
//                .withIdentity("quarz_job", "job_group_1")
//                // Jobs added with no trigger must be durable
//                .storeDurably()
//                .build();
//    }
//    /**
//     * 触发器
//     */
//    @Bean
//    public Trigger trigger(){
//        // 每5秒执行一次
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? ");
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail())
//                .withIdentity("quarz_trigger", "trigger_group_1")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//    }
//}

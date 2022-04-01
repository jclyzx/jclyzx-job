package com.jclyzx.job.springboot.job;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 1.fixedDelay(项目启动立即执行一次)控制⽅法执⾏的间隔时间(毫秒)，是以上⼀次⽅法执⾏完开始算起，如上⼀次⽅法执⾏阻塞住了，那么直到上⼀次执⾏
完，并间隔给定的时间后，执⾏下⼀次。上个过程结束后，等待300ms，执⾏下个过程

2.fixedRate(项目启动立即执行一次)是按照⼀定的速率执⾏，是从上⼀次⽅法执⾏开始的时间算起，如果上⼀次⽅法阻塞住了，下⼀次也是不会执⾏，但是在阻
塞这段时间内累计应该执⾏的次数，当不再阻塞时，⼀下⼦把这些全部执⾏掉，⽽后再按照固定速率继续执⾏。以固定300ms的频率
执⾏某个过程，不管前⾯的过程是否还在进⾏，⼀般⽤于可以独⽴、并⾏的执⾏过程

3.cron表达式可以定制化执⾏任务，但是执⾏的⽅式是与fixedDelay相近的，也是会按照上⼀次⽅法结束时间开始算起。

4.initialDelay 如： @Scheduled(initialDelay = 10000,fixedRate = 15000,这个定时器就是在上⼀个的基础上加了⼀个
initialDelay = 10000 意思就是在容器启动后,延迟10秒后再执⾏⼀次定时器,以后每15秒再执⾏⼀次该定时器
注意：若定时任务写在同一个类中，则执行顺序从上至下
若需要异步执行则使用@Async注解即可，同时在启动类上添加异步支持@EnableAsync或写添加线程配置(ThreadConfig)，在线程配置类中添加异步支持
 * @author jclyzx
 * @time 2022-04-01 14:30:25
 */
@Component
public class JobDemo {
	/* 1minute */
	private static final long SPACE_MIN = 1000 * 60;
	/* 1hour */
	private static final long SPACE_HOUR = SPACE_MIN * 60;

	private static final String CRON_MIN = "0 * * * * ?";
	
	private static final String CRON_HOUR = "0 0 * * * ?";

	private static final String CRON_DAY = "0 0 0 * * ?";

	private static final String CRON_MONTH = "0 0 0 1 * ?";
	
	@Scheduled(cron = CRON_MIN)
    public void cronMin() {
		doSomething();
    }
	
	@Scheduled(fixedRate = SPACE_MIN)
	@Async
    public void fixedRateMin() {
		doSomething();
    }
	
	@Scheduled(fixedDelay = SPACE_MIN)
	@Async
    public void fixedDelayMin() {
		doSomething();
    }
	
	private void doSomething() {
		try {
			//do something
			StackTraceElement[] stes = Thread.currentThread().getStackTrace();
			System.out.println(stes[2]+"---"+LocalDateTime.now());
		} catch (Exception e) {
		}
	}
}

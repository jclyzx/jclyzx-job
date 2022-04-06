#### 介绍
定时任务demo

#### 软件架构
软件架构说明
jclyzx-job 架构  

jclyzx-job  根项目  
├──jclyzx-job-springboot  -- springboot普通定时任务  
├──jclyzx-job-quartz  -- quartz定时任务  
├──jclyzx-job-xxljob  -- xxljob定时任务  
   ───jclyzx-job-xxljob-admin  -- 调度器  
   ───jclyzx-job-xxljob-executor  -- 执行器  

#### 介绍

#### 安装教程
官方文档https://www.xuxueli.com/xxl-job/
1.  初始化数据库，脚本jclyzx-job-xxljob-admin/doc/db/tables_xxl_job.sql
2.  修改调度器项目jclyzx-job-xxljob-admin中的application.yml中的datasource配置后启动项目
3.  根据调度器信息修改执行器项目jclyzx-job-xxljob-executor中的application.yml配置，
主要修改xxl.job.admin.addresses配置
4.  访问http://localhost:9080/xxl-job-admin，默认登录账号 “admin/123456”
5.  访问后有一个默认的定时任务，对应的类在执行器项目jclyzx-job-xxljob-executor中
具体位置为com.xxl.job.executor.service.jobhandler.SampleXxlJob.demoJobHandler()
根据@XxlJob注解名称对应

#### 使用说明


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

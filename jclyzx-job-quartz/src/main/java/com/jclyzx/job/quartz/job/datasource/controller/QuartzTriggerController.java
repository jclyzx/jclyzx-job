package com.jclyzx.job.quartz.job.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.jclyzx.job.quartz.job.datasource.dto.RequestDTO;
import com.jclyzx.job.quartz.job.datasource.dto.trigger.TriggerDTO;
import com.jclyzx.job.quartz.job.datasource.entity.QuartzTriggerDO;
import com.jclyzx.job.quartz.job.datasource.service.QuartzTriggerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/job")
@Api(tags = "定时任务")
public class QuartzTriggerController {

	@Autowired
	private QuartzTriggerService quartzTriggerService;

	@GetMapping(value = "/list")
	@ApiOperation(value = "获取列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
        @ApiImplicitParam(name = "size", value = "页码大小", dataType = "int"),
	})
	public Page<QuartzTriggerDO> getTriggers(@RequestParam(defaultValue = "1") int current,
			@RequestParam(defaultValue = "10") int size) {
		Page<QuartzTriggerDO> page = new Page<QuartzTriggerDO>(current, size);
		quartzTriggerService.page(page, null);
		return page;
	}

	@PostMapping(value = "/add-simple")
	@ApiOperation(value = "添加简单任务")
	@ApiOperationSupport(ignoreParameters = {"trigger.cron"})
	public String addSimple(@RequestBody RequestDTO dto) {
		quartzTriggerService.schedule(dto);
		return "添加成功";
	}
	
	@PostMapping(value = "/add-cron")
	@ApiOperation(value = "添加cron任务")
	@ApiOperationSupport(ignoreParameters = {"trigger.repeatCount","trigger.repeatInterval"})
	public String addCron(@RequestBody RequestDTO dto){
		quartzTriggerService.schedule(dto);
		return "添加成功";
	}
	
	@PostMapping(value="/pause")
	@ApiOperation(value = "根据名称和组名暂停")
	@ApiOperationSupport(ignoreParameters = {"startTime","endTime","repeatCount","repeatInterval","cron"})
	public String pauseTrigger(@RequestBody TriggerDTO td){
		quartzTriggerService.pauseTrigger(td);
		return "success";
	}

	@PostMapping(value="/resume")
	@ApiOperation(value = "根据名称和组名恢复")
	@ApiOperationSupport(ignoreParameters = {"startTime","endTime","repeatCount","repeatInterval","cron"})
	public String resumeTrigger(@RequestBody TriggerDTO td) {
		quartzTriggerService.resumeTrigger(td);
		return "success";
	}

	@PostMapping(value="/remove")
	@ApiOperation(value = "根据名称和组名删除")
	@ApiOperationSupport(ignoreParameters = {"startTime","endTime","repeatCount","repeatInterval","cron"})
	public String removeTrigdger(@RequestBody TriggerDTO td) {
		quartzTriggerService.removeTrigdger(td);
		return "success";
	}

	@PostMapping(value="/execute")
	@ApiOperation(value = "立即运行")
	@ApiOperationSupport(ignoreParameters = {"startTime","endTime","repeatCount","repeatInterval","cron"})
	public String triggerJob(@RequestBody TriggerDTO td) {
		quartzTriggerService.triggerJob(td);
		return "success";
	}
}

package com.xfl.schedule.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * Created by XFL.
 * time on 2016/10/4 11:40
 * description:定时任务
 */
public class QuartzTestJobBean extends QuartzJobBean {
    /**
     * 日志打印.
     */
    private final Logger log = LoggerFactory.getLogger(QuartzTestJobBean.class);
    /**
     * 测试定时任务.
     * @param jobExecutionContext 定时任务
     * @throws JobExecutionException 抛出异常
     */
    @Override
    protected final void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("执行定时任务1");
        System.out.println("执行定时任务");
    }
}

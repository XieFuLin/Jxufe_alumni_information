package com.xfl.schedule.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by XFL.
 * time on 2016/10/15 16:42
 * description:定时任务2
 */
public class QuartzTest2JobBean extends QuartzJobBean {
    /**
     * 日志打印.
     */
    private final Logger log = LoggerFactory.getLogger(QuartzTestJobBean.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("执行定时任务2");
        System.out.println("执行定时任务");
    }
}

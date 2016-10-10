package com.xfl.schedule.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * Created by XFL.
 * time on 2016/10/4 11:40
 * description:定时任务
 */
public class QuartzTestJobBean extends QuartzJobBean {

    /**
     * 测试定时任务.
     * @param jobExecutionContext 定时任务
     * @throws JobExecutionException 抛出异常
     */
    @Override
    protected final void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行定时任务");
    }
}

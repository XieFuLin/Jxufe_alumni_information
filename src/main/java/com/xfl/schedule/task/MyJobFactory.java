package com.xfl.schedule.task;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.util.Assert;

/**
 * Created by XFL.
 * time on 2016/10/4 23:13
 * description:自定义JobFactory以便在JobBean中可以获取Spring注解的bean
 */
public class MyJobFactory extends AdaptableJobFactory {
    /**
     * 这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
     */
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * 注入capableBeanFactory.
     * @param capableBeanFactory AutowireCapableBeanFactory
     */
    @Autowired
    public MyJobFactory(final AutowireCapableBeanFactory capableBeanFactory) {
        Assert.notNull(capableBeanFactory, "自动注入AutowireCapableBeanFactory失败");
        this.capableBeanFactory = capableBeanFactory;
    }
    /**
     * 重写该方法,以便在quartz中可以获取注解的bean.
     * @param bundle TriggerFiredBundle
     * @return 返回实例(Spring bean)
     * @throws Exception 异常
     */
    protected final Object createJobInstance(
            final TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}

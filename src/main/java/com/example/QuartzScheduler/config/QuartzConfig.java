package com.example.QuartzScheduler.config;


import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

@Configuration
public class QuartzConfig {
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return new SchedulerFactoryBeanCustomizer() {
            @Override
            public void customize(SchedulerFactoryBean bean) {
                bean.setQuartzProperties(createQuartzProperties());
            }
        };
    }

    protected Properties createQuartzProperties() {
        Properties props = new Properties();
        props.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
        return props;
    }
}

package com.feng.springboot.demo.receive;

import com.feng.spring.demo.event.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println("---------------"+demoEvent.getMessage());
    }
}

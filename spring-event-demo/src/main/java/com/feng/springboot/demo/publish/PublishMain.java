package com.feng.springboot.demo.publish;

import com.feng.spring.demo.SpringBootDemoMain;
import com.feng.spring.demo.event.DemoEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PublishMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(SpringBootDemoMain.class.getPackage().getName());
        context.refresh();

        DemoEvent testSpringEvent = new DemoEvent(new PublishMain());
        testSpringEvent.setCode(10000);
        testSpringEvent.setMessage("---------My Spring task event------------");
        context.publishEvent(testSpringEvent);
    }
}

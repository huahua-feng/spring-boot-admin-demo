package com.feng.springboot.demo.event;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {
    public DemoEvent(Object source) {
        super(source);
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


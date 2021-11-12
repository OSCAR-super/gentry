package com.oscar.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
    }
}

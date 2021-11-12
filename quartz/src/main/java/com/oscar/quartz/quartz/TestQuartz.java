package com.oscar.quartz.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestQuartz {
    public void execute(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行TestQuartz");
    }

}

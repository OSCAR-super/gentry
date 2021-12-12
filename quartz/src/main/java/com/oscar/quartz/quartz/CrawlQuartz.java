package com.oscar.quartz.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrawlQuartz {
    public void execute() throws IOException, InterruptedException {
        StringBuilder tv_result= new StringBuilder();
        Process p = null;
        p = Runtime.getRuntime().exec("python crawl.python");
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            tv_result.append(line).append("\n");
        }
        p.waitFor();
        is.close();
        reader.close();
        p.destroy();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行CrawlQuartz");
        System.out.println("结果是：\n" +tv_result.toString());
    }

}

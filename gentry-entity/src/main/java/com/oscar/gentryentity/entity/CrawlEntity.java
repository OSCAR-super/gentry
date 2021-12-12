package com.oscar.gentryentity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_crawl")
public class CrawlEntity {
    @Id
    private int id;
    private String proname;
    private String web;
    private String sign;
}

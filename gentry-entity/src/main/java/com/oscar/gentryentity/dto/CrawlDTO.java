package com.oscar.gentryentity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrawlDTO {
    private int id;
    private String proname;
    private String web;
    private String sign;
}

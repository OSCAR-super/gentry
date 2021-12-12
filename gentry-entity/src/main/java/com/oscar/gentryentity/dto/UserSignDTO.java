package com.oscar.gentryentity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignDTO {
    private String id;
    private String openId;
    private Date time;
    private int version;
}

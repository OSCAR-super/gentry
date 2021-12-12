package com.oscar.gentryentity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchDTO {
    private String id;
    private String openId;
    private String searchWords;
    private Date time;
    private int version;
}

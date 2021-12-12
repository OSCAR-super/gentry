package com.oscar.gentryentity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_sign")
public class UserSignEntity {
    @Id
    private String id;
    private String openId;
    private Date time;
    @Version
    private int version;
}

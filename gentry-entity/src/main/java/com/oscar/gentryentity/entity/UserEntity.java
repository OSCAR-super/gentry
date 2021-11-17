package com.oscar.gentryentity.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "tb_wx_user")
public class UserEntity {
    @Id
    private String id;
    private String openId;
    @Version
    private int version;
}

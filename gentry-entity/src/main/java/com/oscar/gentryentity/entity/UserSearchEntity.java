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
@TableName(value = "tb_user_search")
public class UserSearchEntity {
    @Id
    private String id;
    private String openId;
    private String searchWords;
    private Date time;
    @Version
    private int version;
}

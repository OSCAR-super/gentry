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
@TableName(value = "tb_user_role")
public class UserRoleEntity {
    @Id
    private String id;
    private String openId;
    private String role;
    @Version
    private int version;
}

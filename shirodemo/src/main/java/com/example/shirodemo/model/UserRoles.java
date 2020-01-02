package com.example.shirodemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRoles {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户的角色")
    private String roleName;
}

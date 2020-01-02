package com.example.shirodemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RolePermissions {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @ApiModelProperty("角色")
    private String roleName;
    @ApiModelProperty("角色拥有的权限")
    private String permission;
}

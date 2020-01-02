package com.example.shirodemo.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shirodemo.model.RolePermissions;
import com.example.shirodemo.model.UserRoles;
import com.example.shirodemo.model.Users;
import com.example.shirodemo.service.RolePermissionsService;
import com.example.shirodemo.service.UserRolesService;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    UserRolesService userRolesService;
    @Autowired
    RolePermissionsService rolePermissionsService;

    /**认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken mtoken = (UsernamePasswordToken) authenticationToken;
        String username = mtoken.getUsername();
        if(username == null){
            throw new AccountException("没接收到用户名");
        }
        QueryWrapper<Users> qw = new QueryWrapper<Users>();
        qw.eq("username", username);
        Users user = userService.findUserByUsername(qw);
        if(user == null){
            throw new UnknownAccountException("没找到用户");
        }
        if(user.getPassword().isEmpty()){
            throw new UnknownAccountException("用户没有密码，请先设置密码");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getPasswordSalt()));
        return info;
    }

    /**授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if(principalCollection == null){
            throw new AuthorizationException("未找到请求体");
        }
        String username = (String) getAvailablePrincipal(principalCollection);
        System.out.println(username);
        Set<UserRoles> userRoles = userRolesService.findRolesByUsername(username);
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for (UserRoles role: userRoles){
            roles.add(role.getRoleName());
            Set<RolePermissions> rolePermissions = rolePermissionsService.findPermissionByRoleName(role.getRoleName());
            for(RolePermissions permission: rolePermissions){
                permissions.add(permission.getPermission());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setStringPermissions(permissions);
        return info;
    }
}

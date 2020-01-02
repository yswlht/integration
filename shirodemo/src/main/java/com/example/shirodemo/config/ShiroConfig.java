package com.example.shirodemo.config;

import com.example.shirodemo.realm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**配置自己的Realm*/
    @Bean
    MyRealm myRealm(){
        return new MyRealm();
    }

    /**把自己的Realm加入容器*/
    @Bean
    DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean(name = "shiroFilter")
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //就是被拦截下来的请求都换成这个
        bean.setLoginUrl("/login");
        //保留参数，不知道有什么用
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorizedurl");
        //配置拦截规则，一定要是有序的，anon是不拦截的，authc是需要拦截的，'/**'要放最后，shiro是按顺序检索下来的
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/doLogin", "anon");
        map.put("/doc.html", "anon");
        map.put("/swagger-resources", "anon");
        map.put("/v2/**", "anon");
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}

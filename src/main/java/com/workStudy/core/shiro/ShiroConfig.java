package com.workStudy.core.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.*;

/**
 * ShiroConfig:shiro 配置类,配置哪些拦截,哪些不拦截,哪些授权等等各种配置都在这里
 *
 * 很多都是老套路,按照这个套路配置就行了
 *
 */
@Configuration
public class ShiroConfig {
    /**
     * 注入安全过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/media/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger**/**", "anon");
        filterChainDefinitionMap.put("/**/swagger**/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        // 配置不会被拦截的链接 顺序判断

        //测试开放的接口
        filterChainDefinitionMap.put("/login/**", "anon");

        filterChainDefinitionMap.put("/favicon.ico", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/**", "anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/303");

//        // 添加自己的过滤器并且取名为jwt
//        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
//        filterMap.put("jwt", new JwtFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);
//        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
//        filterChainDefinitionMap.put("/**", "jwt");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

//    /**
//     * 注入安全管理器
//     * @param shiroRealm
//     * @return
//     */
//    @Bean("securityManager")
//    public SecurityManager securityManager(ShiroRealm shiroRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(shiroRealm);
//
//        /*
//         * 关闭shiro自带的session，详情见文档
//         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
//         */
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);
//        return securityManager;
//    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean(name = "adminRealm")
    public AdminRealm adminRealm() {
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        return adminRealm;
    }

    @Bean(name = "managerRealm")
    public ManagerRealm managerRealm() {
        ManagerRealm managerRealm = new ManagerRealm();
        managerRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        return managerRealm;
    }

    @Bean(name = "studentRealm")
    public StudentRealm studentRealm() {
        StudentRealm studentRealm = new StudentRealm();
        studentRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        return studentRealm;
    }


    @Bean(name = "SecurityManager")
        public SecurityManager securityManager() {
            DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            //设置realm.
            securityManager.setAuthenticator(modularRealmAuthenticator());
            List<Realm> realms = new ArrayList<>();
            //添加多个Realm
            realms.add(adminRealm());
            realms.add(managerRealm());
            realms.add(studentRealm());
            securityManager.setRealms(realms);

            return securityManager;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //数据库异常处理
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("UnauthorizedException", "/403");
        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error");
        r.setExceptionAttribute("ex");
        return r;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }
}
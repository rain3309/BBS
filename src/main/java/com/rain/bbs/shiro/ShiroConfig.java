package com.rain.bbs.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * Shiro提供了与WEB集成的支持 ShiroFilter入口拦截了需要安全控制的URL
     * 在Spring中使用Shiro 需要配置一个DelegationFilterProxy, DelegatingFilterProxy作用是
     * 自动到Spring容器中查找名字为shiroFilter(filter-name)的bean并把所有的操作委托给它
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle","true");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }

    @Bean(name = "shiroDBRealm")
    @DependsOn(value = "userDao")
    public ShiroDBRealm shiroDBRealm(){
        ShiroDBRealm realm = new ShiroDBRealm();
        return realm;
    }

    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     *
     * SecurityManager在Shiro中的地位是一个安全大管家 是shiro的心脏 所有的交互都通过SecurityManager进行控制
     * 它管理所有的Subject 负责进行认证和授权 会话 缓存的管理
     * PathMatchingFilterChainResolver 是ChainResolver的实现类其中包含两个重要组件：FilterChainManager和PatternMatcher
     * FilterChainManager 管理着FIlter和Filter链 配合PathManagerFilterChainResolver解析出Filter链
     * PatternMatcher用来进行请求路径匹配
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroDBRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

//    @Bean(name = "authcFilter")
//    public CmsAuthenticationFilter authcFilter(){
//        return new CmsAuthenticationFilter();
//    }
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * 配置好DelegationFilterProxy之后 只要把ShiroFilter配置到Spring容器中即可
     * 使用ShiroFilterFactoryBean来创建shiroFilter,Bean的名字为shiroFilter
     *
     * @return
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/success");

        Map<String,Filter> filters = new LinkedHashMap<>();
        CmsLogoutFilter logoutFilter = new CmsLogoutFilter();
        CmsAuthenticationFilter authcFilter = new CmsAuthenticationFilter();
        filters.put("logout",logoutFilter);
        filters.put("authc",authcFilter);
        shiroFilterFactoryBean.setFilters(filters);
        //先后顺序
        Map<String,String> filterChainDefinitions = new LinkedHashMap<>();
        filterChainDefinitions.put("/logout","logout");
        filterChainDefinitions.put("/","anon");
        filterChainDefinitions.put("/login","authc");
        filterChainDefinitions.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
        return shiroFilterFactoryBean;
    }


}

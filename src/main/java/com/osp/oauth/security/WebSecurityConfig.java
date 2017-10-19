package com.osp.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 
 * @author fly
 *
 */
@Configuration
//@EnableWebSecurity完成的工作便是加载了
//WebSecurityConfiguration，AuthenticationConfiguration这两个核心配置类，
//也就此将spring security的职责划分为了配置安全信息，配置认证信息两部分。
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .requestMatchers().anyRequest()
            .and()
            	//配置路径拦截，表明路径访问所对应的权限，角色，认证信息
                .authorizeRequests()
                //不需要验证
                .antMatchers("/oauth/*").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            //对应表单认证相关的配置
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            //对应了注销相关的配置
            .logout()
                .permitAll();;
        // @formatter:on
    }
    
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER1").build());
//        manager.createUser(User.withUsername("user_2").password("123456").authorities("USER2").build());
//        return manager;
//    }
    
	
    /**
     * @Autowired注入的AuthenticationManagerBuilder是全局的身份认证器，
     * 作用域可以跨越多个WebSecurityConfigurerAdapter，以及影响到基于Method的安全控制
     * @param auth
     * @throws Exception
     */
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("USER");
//    }
	
    /**
     * 自定义验证
     * @return
     */
    @Bean
    CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }
    
    /**
     * 自定义用户服务
     */
    @Autowired
    private MyUserDetailsService userDetailsService;
    
	/**
	 * 类似于一个匿名内部类，它的作用域局限于一个WebSecurityConfigurerAdapter内部
	 * 都存在的情况下走configure验证
	 */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("admin1").password("admin1").roles("USER1");
		
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

        auth.authenticationProvider(customAuthenticationProvider());
    }
    
}

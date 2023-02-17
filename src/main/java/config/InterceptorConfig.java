package config;

import interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author :luo wei
 * @description:
 * @date 2023/2/16/016 15:18
 */
@Configuration
@ComponentScan(basePackageClasses = LoginInterceptor.class)
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/test/login")   //设置拦截名单
                .excludePathPatterns("/index.html");  //设置白名单
    }

}

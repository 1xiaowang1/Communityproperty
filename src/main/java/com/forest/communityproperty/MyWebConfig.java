package com.forest.communityproperty;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
/*    //设置静态文件的目录
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/uploadImg/**").addResourceLocations("file:D:\\XiangMuDemo\\CommunityProperty\\src\\main\\resources\\static\\assets\\car\\");
    }*/
}

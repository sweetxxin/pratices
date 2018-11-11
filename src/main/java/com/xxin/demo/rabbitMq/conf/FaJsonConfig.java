package com.xxin.demo.rabbitMq.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class FaJsonConfig extends WebMvcConfigurerAdapter {
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**1  预先定义一个Converters 转换消息的对象
         * 2 添加fastjson的配置信息
         * 3 早converter中添加配置信息
         * 4.将converter添加到converters中
         */
        super.configureMessageConverters(converters);//
        FastJsonHttpMessageConverter fastConstructor=new FastJsonHttpMessageConverter();//1
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);//2
        fastConstructor.setFastJsonConfig(fastJsonConfig);//3
        converters.add(fastConstructor);//4
    }
}

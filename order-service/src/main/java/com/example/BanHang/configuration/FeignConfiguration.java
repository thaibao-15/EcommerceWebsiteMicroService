package com.example.BanHang.configuration;

import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    public SpringFormEncoder multipartFormEncoder(){
        return new SpringFormEncoder();
    }
}

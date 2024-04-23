package com.ucl.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ucl.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns(
				"/main/**"
				,"/usr/**"
				,"/index"
				,"/indexUsr"
				,"/loginUsr"
				,"/memberSdmLoginConfirm"
				,"/memberUsrCreate"
				,"/memberUsrInsert"
				,"/sdm/**"
				,"/loginSdm"
				,"/loginSdmMemberCreate"
				,"/memberSdmLoginInsert"
		);
	}
}
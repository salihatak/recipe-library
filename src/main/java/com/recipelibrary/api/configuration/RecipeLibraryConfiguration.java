package com.recipelibrary.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.recipelibrary.api.interceptor.LogExecutionInterceptor;

@Configuration
public class RecipeLibraryConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogExecutionInterceptor());
	}

}

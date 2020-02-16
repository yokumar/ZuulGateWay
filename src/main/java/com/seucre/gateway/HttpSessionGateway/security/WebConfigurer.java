package com.seucre.gateway.HttpSessionGateway.security;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer  extends InstantiationAwareBeanPostProcessorAdapter implements WebMvcConfigurer   {
	
	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(authInterceptor).addPathPatterns("/**","/*").excludePathPatterns("/login");
	}

	//because Zuul Interceptors are different from Web interceptor
	 @Override
	    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {

	        if (bean instanceof ZuulHandlerMapping) {

	        	ZuulHandlerMapping zuulHandlerMapping = (ZuulHandlerMapping) bean;
	            zuulHandlerMapping.setInterceptors(authInterceptor);
	        }

	        return super.postProcessAfterInstantiation(bean, beanName);
	    }

	
}

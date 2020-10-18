package com.jylee.tft.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.CookieProcessor;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index");
	}	
	
	
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {

	    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
	    
	    factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/error/403.do"));
	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404.do"));
	    factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500.do"));
	    factory.addErrorPages(new ErrorPage("/error/error.do"));

	    TomcatContextCustomizer tomcatContextCustomizers = new TomcatContextCustomizer(){
	    
			@Override
			public void customize(Context context) {

			    CookieProcessor cookieProcessor = new LegacyCookieProcessor();
			    		
				context.setCookieProcessor(cookieProcessor);
			}
		};
	    
	    factory.addContextCustomizers(tomcatContextCustomizers);
	    
	    return factory;
	}
	
}

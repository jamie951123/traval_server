package com.jamie.travel;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.jamie.travel.jwt.security.JwtAuthenticationFilter;
import com.jamie.travel.logger.LogMsg;
import com.jamie.travel.system.initial.service.InitialServer;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Autowired
	private InitialServer initialServer;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void showIt() {
		System.out.println("Serve Base Path :" + servletContext.getContextPath());
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}

	// Token filter
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		return registrationBean;
	}

	// Test API
	@GetMapping("/test")
	public @ResponseBody String test() {
		return "API Successful";
	}

	@Bean
	public boolean initial() {
		try {
			System.out.println("Server initial beginning");
			initialServer.photo_folder();
			System.out.println("Server initial finished");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(LogMsg.errLog("Bean", new String[] {"initial",e.getMessage()}));
			return false;
		}
	}
}

/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.newfound.rest.server.service.PersonDaoServiceImpl;

@Configuration
public class RestConfig {

	@Bean(name = "personRepoImpl")
	public PersonDaoServiceImpl personRepoImpl() {
		return new PersonDaoServiceImpl();
	}

	// @Bean
	// public MappingJackson2JsonView jackson2JsonView() {
	// MappingJackson2JsonView view = new MappingJackson2JsonView();
	// view.setPrettyPrint(true);
	// return view;
	// }
	//
	// @Bean
	// public ViewResolver viewResolver() {
	// return new BeanNameViewResolver();
	// }

	@Bean
	public MappingJackson2HttpMessageConverter jackson2Converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setPrettyPrint(true);
		return converter;
	}
}

/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.newfound.rest.server.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.newfound.rest.server.service.PersonDaoServiceImpl;

@Configuration
public class RestConfig extends WebMvcConfigurerAdapter {

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
	
	/**
	 * Message Converters
	 */
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageJsonConverter());
        converters.add(new StringHttpMessageConverter());
    }

	/**
	 * Json Message Converter
	 * 
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageJsonConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setPrettyPrint(true);
		return converter;
	}

	// /**
	// * Xml Message Converter
	// *
	// * @return
	// */
	// @Bean
	// public MappingJackson2XmlHttpMessageConverter
	// jackson2HttpMessageXmlConverter() {
	// MappingJackson2XmlHttpMessageConverter converter = new
	// MappingJackson2XmlHttpMessageConverter();
	// converter.setPrettyPrint(true);
	// return converter;
	// }
}

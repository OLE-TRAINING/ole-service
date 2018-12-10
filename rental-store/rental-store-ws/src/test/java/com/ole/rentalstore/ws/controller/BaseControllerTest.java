package com.ole.rentalstore.ws.controller;

import java.lang.reflect.Method;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.ole.rentalstore.ws.error.GlobalExceptionHandler;

public class BaseControllerTest {

	@InjectMocks
	protected GlobalExceptionHandler exceptionHandler;
	
	protected MockMvc mockMvc;

	@Mock
	protected Environment env;

	public <T> void setUp(T controller) {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setHandlerExceptionResolvers(generateExceptionHandler()).build();
	}
	
	private ExceptionHandlerExceptionResolver generateExceptionHandler() {
		ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
			protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod,
					Exception exception) {
				Method method = new ExceptionHandlerMethodResolver(GlobalExceptionHandler.class)
						.resolveMethod(exception);
				return new ServletInvocableHandlerMethod(exceptionHandler, method);
			}
		};
		exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		exceptionResolver.afterPropertiesSet();
		return exceptionResolver;
	}
}
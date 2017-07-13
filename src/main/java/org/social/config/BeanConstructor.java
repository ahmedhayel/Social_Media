package org.social.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanConstructor implements ApplicationContextAware {

	private static ApplicationContext context;

	public BeanConstructor() {
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static <T> T getBean(String name, Class<T> aClass) {
		return context.getBean(name, aClass);
	}

	/**
	 * Get a Spring bean by type.
	 **/
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

}

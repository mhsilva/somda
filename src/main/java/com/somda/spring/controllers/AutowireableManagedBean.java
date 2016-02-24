package com.somda.spring.controllers;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class AutowireableManagedBean {

	protected AutowireCapableBeanFactory ctx;

	@PostConstruct
	protected void init() {
		ctx = WebApplicationContextUtils
				.getWebApplicationContext(
						(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getAutowireCapableBeanFactory();
		// The following line does the magic
		ctx.autowireBean(this);
	}
}

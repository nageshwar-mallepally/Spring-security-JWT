package com.project.real.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PreDestroy;
import java.io.Serializable;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionWrapper implements Serializable{
	private Session session;
	private String tenantId;

	public void setSession(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return this.session;
	}


	@PreDestroy
	public void closeSession() {
		//SessionManager.get().removeSession(session);
		//session = null;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}

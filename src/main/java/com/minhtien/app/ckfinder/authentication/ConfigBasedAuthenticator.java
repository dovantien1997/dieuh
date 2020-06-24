package com.minhtien.app.ckfinder.authentication;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;

import com.cksource.ckfinder.authentication.Authenticator;
import com.minhtien.app.ckfinder.config.CustomConfig;

@Named
public class ConfigBasedAuthenticator implements Authenticator {
	
    @Inject
    private ApplicationContext applicationContext;

    @Override
    public boolean authenticate() {
        CustomConfig config = applicationContext.getBean(CustomConfig.class);

        return config.isEnabled();
    }
}

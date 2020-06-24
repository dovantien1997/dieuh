package com.minhtien.app.ckfinder.config;

import com.cksource.ckfinder.config.Config;

public class CustomConfig extends Config {
	private static final long serialVersionUID = 3515234845860361735L;

	public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private boolean enabled = false;
}

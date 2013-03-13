package com.gentics.rest.foldertemplatelist;

import java.net.URISyntaxException;

import com.gentics.cr.configuration.EnvironmentConfiguration;


public final class ConfigDirectory {
	
	/**
	 * private constructor to prevent instantiation.
	 */
	private ConfigDirectory() { }
	
	/**
	 * Executing this method configures the {@link EnvironmentConfiguration} to use the directory of this class (that contains a cache.ccf
	 * and the nodelog.properties).
	 */
	public static void useThis() throws URISyntaxException {
		String configLocation = ConfigDirectory.class.getResource(".").toURI().getPath();
		EnvironmentConfiguration.setConfigPath(configLocation);
		EnvironmentConfiguration.loadEnvironmentProperties();
	}
}

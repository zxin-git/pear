package com.zxin.apache.common;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration2Test {

	private static Logger logger = LoggerFactory.getLogger(Configuration2Test.class);
	
	public static void main(String[] args) {
		try {
			Configurations configs = new Configurations();
			Configuration propConfig = configs.properties("test.properties");
			Configuration xmlConfig = configs.xml("test.xml");
			
//			Parameters params = new Parameters();
//			// Read data from this file
//			File propertiesFile = new File("config.properties");
//
//			ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder =
//			    new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
//			    .configure(params.fileBased()
//			        .setFile(propertiesFile));
//			PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(),
//			    null, 1, TimeUnit.MINUTES);
//			trigger.start();
		} catch (Exception e) {
			logger.debug("",e);
		}
	}
}


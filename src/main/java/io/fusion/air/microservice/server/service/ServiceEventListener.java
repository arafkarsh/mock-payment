/**
 * (C) Copyright 2021 Araf Karsh Hamid 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fusion.air.microservice.server.service;
 
import io.fusion.air.microservice.server.config.ServiceConfiguration;
import io.fusion.air.microservice.server.config.ServiceHelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import io.fusion.air.microservice.utils.CPU;

//Logging System
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 
 * @author arafkarsh
 * @version 1.0
 */
@Configuration
public class ServiceEventListener {

	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());
	
	@Autowired
	private ServiceConfiguration serviceConfig;
	
	/**
	 * 
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		log.info("Service is getting ready...... ");
	    // log.info(CPU.printCpuStats());
	    // System.out.println(LocalDateTime.now()+"|Service is getting ready...... ");
	    // System.out.println(LocalDateTime.now()+"|"+CPU.printCpuStats());
		showLogo();
	}
	
	/**
	 * Shows the Service Logo and Version Details. 
	 */
	public void showLogo() {
		String version="v0.1.0", name="NoName";
		if(serviceConfig != null) {
			version = serviceConfig.getServerVersion();
			name =serviceConfig.getServiceName();
		}
		String logo =ServiceHelp.LOGO.replaceAll("SIGMA", name).replaceAll("VERSION", version);
		log.info(name+" Service is ready! ....... ..."
				+ logo
				+ "Build No. = "+serviceConfig.getBuildNumber()
				+ " :: Build Date = "+serviceConfig.getBuildDate()
				+ " :: Restart = "+ServiceHelp.getCounter() 
				+ ServiceHelp.NL + ServiceHelp.DL
				+ ServiceHelp.NL + "API URL : " + serviceConfig.apiURL()
				+ ServiceHelp.NL + ServiceHelp.DL
				);
		/**
		System.out.println(LocalDateTime.now()+"|"+serviceConfig.getServiceName()
				+" Service is ready! ....... ..." 
				+ logo
				+ "Build No. = "+serviceConfig.getBuildNumber()
				+ " :: Build Date = "+serviceConfig.getBuildDate()
				+ " :: Restart = "+ServiceHelp.getCounter()
				+ ServiceHelp.NL + ServiceHelp.DL
				);
		 */
	}
}
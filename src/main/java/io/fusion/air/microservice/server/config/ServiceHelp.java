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
package io.fusion.air.microservice.server.config;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Logging System
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;

/**
 * 
 * @author arafkarsh
 * @version 1.0
 * 
 */
@Component
public class ServiceHelp {
	
	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());
	
	private static int counter;
	
	@Autowired
	private ServiceConfiguration serviceConfig;
	
	public static final String NL = System.getProperty("line.separator");
	
	public static final String PADDING = "                 ";
	public static final String LINE = "                 --------------------------------------------";

	
	public static final String DL = "----------------------------------------------------------------------------";
	
	public static final String VERSION = "0.1.0";
	
	public static final String LOGO = "" +NL
	+"============================================================================" + NL
	+":: SIGMA Service :: (VERSION) " + NL
	+"============================================================================" + NL;
		 


	public ServiceHelp() {
		counter++;
	}
	
	/**
	 * Returns the Restart Counter
	 * @return
	 */
	public static int getCounter() {
		return counter;
	}
	
	/**
	 * Print Properties
	 */
	@PostConstruct
	public void printProperties() {
		HashMap<String, String> sysProps = serviceConfig.systemProperties();

		// Environment Variables
		for(String key: sysProps.keySet()) {
			log.debug("|System Property Key   = "+key+" | Value = "+sysProps.get(key));
		}

		// Property Map (Application.Properties)
		HashMap<String, String> map = serviceConfig.getAppPropertyMap();
		for(String k : map.keySet()) {
			log.debug("|Service Property Key  = "+k+" | Value = "+map.get(k));
		}

		// Property List (Application.properties)
		ArrayList<String> properties = serviceConfig.getAppPropertyList();
		for(String p: properties) {
			log.debug("|Service Property List = "+p);
		}
 	}
}

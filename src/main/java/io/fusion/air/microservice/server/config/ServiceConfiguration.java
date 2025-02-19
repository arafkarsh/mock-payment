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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.fusion.air.microservice.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Service Configuration
 *
 * @author arafkarsh
 *
 */
@Component
@Configuration
@PropertySource(
		name = "serviceConfig",
		// Expects file in the directory the jar is executed
		value = "file:./application.properties")
		// Expects the file in src/main/resources folder
		// value = "classpath:application.properties")
		// value = "classpath:application2.properties,file:./application.properties")
public class ServiceConfiguration implements Serializable {

	/**
	 * Return the JSON String
	 * @return
	 */
	public String toJSONString()  {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"springdoc.swagger-ui.path\": \"").append(apiDocPath).append("\",");
		sb.append("\"service.org\": \"").append(serviceOrg).append("\",");
		sb.append("\"service.name\": \"").append(serviceName).append("\",");
		sb.append("\"service.api.prefix\": \"").append(serviceApiPrefix).append("\",");
		sb.append("\"service.api.version\": \"").append(serviceApiVersion).append("\",");
		sb.append("\"service.api.name\": \"").append(serviceApiName).append("\",");
		sb.append("\"service.api.path\": \"").append(serviceApiPath).append("\",");
		sb.append("\"service.url\": \"").append(serviceUrl).append("\",");
		sb.append("\"build.number\": ").append(buildNumber).append(",");
		sb.append("\"build.date\": \"").append(buildDate).append("\",");
		sb.append("\"serverVersion\": \"").append(serverVersion).append("\",");
		sb.append("\"server.port\": ").append(serverPort).append(",");
		sb.append("\"remote.host\": \"").append(remoteHost).append("\",");
		sb.append("\"remote.port\": ").append(remotePort).append(",");
		sb.append("\"server.restart\": ").append(serverRestart).append(",");
		sb.append("\"spring.codec.max-in-memory-size\": \"").append(springCodecMaxMemory).append("\",");
		sb.append("\"token.key\": \"").append(tokenKey).append("\",");
		sb.append("\"app.property.list\": ").append(Utils.toJsonString(appPropertyList)).append(",");
		sb.append("\"app.property.map\": ").append(Utils.toJsonString(appPropertyMap));
		sb.append("}");
		return sb.toString();
	}
	// Config Path
	public static final String CONFIG = "/config";

	// Health Path
	public static final String HEALTH = "/service";

	@Value("${service.org:OrgNotDefined}")
	private String serviceOrg;

	@Value("${service.name:NameNotDefined}")
	private String serviceName;

	@Value("${service.api.prefix:API}")
	private String serviceApiPrefix;

	@Value("${service.api.version:V1}")
	private String serviceApiVersion;

	@Value("${service.api.name:NAME}")
	private String serviceApiName;

	@Value("${service.api.path:PATH}")
	private String serviceApiPath;

	@Value("${service.container:ContainerName}")
	private String serviceContainer;

	@Value("${service.api.repository:GitRepo}")
	private String serviceApiRepository;

	@Value("${service.url:URL}")
	private String serviceUrl;

	@Value("${springdoc.swagger-ui.path}")
	private String apiDocPath;

	@Value("${build.number:0}")
	private int buildNumber = 0;
	
	@Value("${build.date}")
	private String buildDate;
	
	@Value("${server.version:0.5.0}")
	private String serverVersion;
	
	@Value("${server.port:9080}")
	private int serverPort;
	
	@Value("${payment.gateway.host:localhost}")
	private String paymentGWHost;
	@Value("${payment.gateway.port:9091}")
	private int paymentGWPort;
	
	@Value("${remote.host:localhost}")
	private String remoteHost;
	@Value("${remote.port:9091}")
	private int remotePort;
	
	@Value("${server.restart:false}")
	private boolean serverRestart;
	
	// @Value("${logging.level}")
	// private String loggingLevel;
	
	@Value("${spring.codec.max-in-memory-size:3MB}")
	private String springCodecMaxMemory;
	
	@Value("${token.key:sigmaEpsilon6109871597}")
	private String tokenKey;

	// Get All the System Properties
	@JsonIgnore
	@Value("#{systemProperties}")
	private HashMap<String, String> systemProperties;
	
	// Deployed App Property List
	@JsonIgnore
	@Value("${app.property.list}")
	private ArrayList<String> appPropertyList;
	
	// Deployed App Properties Map
	@JsonIgnore
	@Value("#{${app.property.map}}")
	private HashMap<String, String> appPropertyMap;
	
	/**
	 * To be used outside SpringBoot Context
	 * For WireMock Testing the External Services
	 */
	public ServiceConfiguration() {
		this("localhost", 8080);
	}
	
	/**
	 * To be used outside SpringBoot Context
	 * For WireMock Testing the External Services
	 * 
	 * @param rHost
	 * @param rPort
	 */
	public ServiceConfiguration(String rHost, int rPort) {
		this.remoteHost = rHost;
		this.remotePort = rPort;
	}

	/**
	 * Returns Service Details as HTML
	 * @return
	 */
	public String getServiceDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("<b>").append(serviceName).append(" Service </b><br>");
		sb.append("Build No:<b> ").append(buildNumber).append("</b>, ");
		sb.append("Build Date:<b> ").append(buildDate).append("</b> ");
		return sb.toString();
	}

	/**
	 * Show the API URL
	 * @return
	 */
	public String apiURL() {
		return "http://localhost:"+serverPort+"/"+ apiDocPath;
	}

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}

	/**
	 * @return the paymentGWHost
	 */
	public String getPaymentGWHost() {
		return paymentGWHost;
	}

	/**
	 * @return the paymentGWPort
	 */
	public int getPaymentGWPort() {
		return paymentGWPort;
	}

	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * @return the remotePort
	 */
	public int getRemotePort() {
		return remotePort;
	}

	/**
	 * @return the springCodecMaxMemory
	 */
	public String getSpringCodecMaxMemory() {
		return springCodecMaxMemory;
	}

	/**
	 * @return the tokenKey
	 */
	public String getTokenKey() {
		return tokenKey;
	}
	
	/**
	 * @return the serverVersion
	 */
	public String getServerVersion() {
		return serverVersion;
	}

	/**
	 * @return the serverRestart
	 */
	public boolean isServerRestart() {
		return serverRestart;
	}

	/**
	 * @return the buildNumber
	 */
	public int getBuildNumber() {
		return buildNumber;
	}

	/**
	 * @return the buildDate
	 */
	public String getBuildDate() {
		return buildDate;
	}
	
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @return the appPropertyList
	 */
	public ArrayList<String> getAppPropertyList() {
		return appPropertyList;
	}

	/**
	 * @return the appPropertyMap
	 */
	public HashMap<String, String> getAppPropertyMap() {
		return appPropertyMap;
	}
	
	/**
	 * @return the systemProperties
	 */
	public HashMap<String, String> systemProperties() {
		return systemProperties;
	}

	public String getApiDocPath() {
		return apiDocPath;
	}

	public String getServiceApiPrefix() {
		return serviceApiPrefix;
	}

	public String getServiceApiVersion() {
		return serviceApiVersion;
	}

	public String getServiceApiName() {
		return serviceApiName;
	}

	public String getServiceApiPath() {
		return serviceApiPath;
	}

	public String getServiceApiRepository() {
		return serviceApiRepository;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public String getServiceOrg() {
		return serviceOrg;
	}

	public String getServiceContainer() {
		return serviceContainer;
	}
}

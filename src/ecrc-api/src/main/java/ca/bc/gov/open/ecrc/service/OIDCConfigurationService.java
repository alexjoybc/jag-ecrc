package ca.bc.gov.open.ecrc.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ca.bc.gov.open.ecrc.configuration.EcrcProperties;
import ca.bc.gov.open.ecrc.model.OIDCConfiguration;


/**
 * 
 * Service Returns a JAVA object which represents OpenID Connect configuration values from the provider's 
 * Well-Known Configuration Endpoint. 
 * 
 * @author shaunmillargov
 *
 */
@Service
@Configuration
@EnableConfigurationProperties(EcrcProperties.class)
public class OIDCConfigurationService {
	
	private Logger logger = LoggerFactory.getLogger(OIDCConfigurationService.class);
	
	private OIDCConfiguration config = null; 
	
	@Autowired
	private EcrcProperties ecrcProps;
	
	/**
	 * 
	 * Loads the OIDC Well-known config endpoint if not already loaded. 
	 * 
	 * @return
	 */
    public OIDCConfiguration getConfig() {
        if ( null == config ) 
        	loadConfig();
        return config;
    }

    /**
     * 
     * Loads config object. 
     * 
     */
	private void loadConfig() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		URI uri = null;
		try {
			uri = new URI(ecrcProps.getOauthWellKnown());
			config = restTemplate.getForObject(uri, OIDCConfiguration.class);
		} catch (URISyntaxException e2) {
			logger.error("Unable to load remote server well-known configuration endpoint. Check Oauth2 well-known endpoint configuration. ", e2);
		}
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory
				= new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(300000);
		clientHttpRequestFactory.setReadTimeout(300000);
		return clientHttpRequestFactory;
	}
}


package ca.bc.gov.open.ecrc;

import ca.bc.gov.open.ecrc.objects.DoAuthenticateUser;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.bc.gov.open.ecrc.exception.EcrcExceptionConstants;
import ca.bc.gov.open.ecrc.exception.EcrcServiceException;
import ca.bc.gov.open.ecrc.objects.GetProvinceList;
import reactor.core.publisher.Mono;

/**
 * 
 * Service Implementation class. 
 * 
 * @author shaunmillargov
 *
 */
@Service
public class EcrcServicesImpl implements EcrcServices {
	private final WebClient webClient;
	Logger logger = LoggerFactory.getLogger(EcrcServicesImpl.class);
	@Autowired
	ObjectMapper objectMapper;

	@Value("${rest.getProvincesList}")
	private String provinceListUri;

	@Value("${rest.doAuthenticateUser}")
	private String doAuthenticateUserUri;

	public EcrcServicesImpl(@Value("${rest.baseUrl}") final String baseUrl,
							@Value("${rest.userName}") final String userName, @Value("${rest.password}") final String password) {
		this.webClient = WebClient.builder().baseUrl(baseUrl)
				.defaultHeaders(header -> header.setBasicAuth(userName, password)).build();
	}

	public String doAuthenticateUser(String accessCode) throws EcrcServiceException  {
		doAuthenticateUserUri = String.format(doAuthenticateUserUri, accessCode);
		Mono<DoAuthenticateUser> responseBody = this.webClient.get().uri(doAuthenticateUserUri).retrieve()
				.bodyToMono(DoAuthenticateUser.class);
		String response;
		try {
			if (responseBody.block().getResponseCode() == 1) {
				return objectMapper.writeValueAsString(responseBody.block());
			} else {
				return null;
			}
		} catch (JsonProcessingException e) {
			logger.error("Failed to convert to json processing exception");
			throw new EcrcServiceException(EcrcExceptionConstants.CONVERT_TO_JSON_ERROR, e);
		} catch (Exception e) {
			logger.error("Failed to convert to json general exception");
			throw new EcrcServiceException(EcrcExceptionConstants.WEBSERVICE_RESPONSE_ERROR, e);
		}
	}

	public String getProvinceList() throws EcrcServiceException {
		Mono<GetProvinceList> responseBody = this.webClient.get().uri(provinceListUri).retrieve()
				.bodyToMono(GetProvinceList.class);
		String response;
		try {
			response = objectMapper.writeValueAsString(responseBody.block());
		} catch (JsonProcessingException e) {
			// TODO - Log exception
			throw new EcrcServiceException(EcrcExceptionConstants.CONVERT_TO_JSON_ERROR, e);
		} catch (Exception e) {
			// TODO - Log exception
			throw new EcrcServiceException(EcrcExceptionConstants.WEBSERVICE_RESPONSE_ERROR, e);
		}
		return response;
	}
}

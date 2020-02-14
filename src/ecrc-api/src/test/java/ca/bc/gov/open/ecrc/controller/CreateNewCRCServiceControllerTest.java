package ca.bc.gov.open.ecrc.controller;

import ca.bc.gov.open.ecrc.EcrcServicesImpl;
import ca.bc.gov.open.ecrc.exception.EcrcExceptionConstants;
import ca.bc.gov.open.ecrc.exception.EcrcServiceException;
import ca.bc.gov.open.ecrc.model.RequestNewCRCService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateNewCRCServiceControllerTest {
    @InjectMocks
    CreateNewCRCServiceController createNewCRCServiceController;

    @Mock
    EcrcServicesImpl ecrcServices;

    RequestNewCRCService requestNewCRCService;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    @DisplayName("Success - createNewCRCService controller")
    @Test
    public void testSuccessfulCreate() throws EcrcServiceException {
        requestNewCRCService = new RequestNewCRCService();
        Mockito.when(ecrcServices.createNewCRCService(requestNewCRCService)).thenReturn(new ResponseEntity<>("SOMESTRING", HttpStatus.OK));
        ResponseEntity<String> result = createNewCRCServiceController.createNewCRCService(requestNewCRCService);
        Assertions.assertEquals("SOMESTRING", result.getBody());
    }

    @DisplayName("Failure - createNewCRCService controller")
    @Test
    public void testNotFoundValidOrg() throws EcrcServiceException {
        requestNewCRCService = new RequestNewCRCService();
        Mockito.when(ecrcServices.createNewCRCService(requestNewCRCService)).thenReturn(new ResponseEntity<>(String.format(EcrcExceptionConstants.WEBSERVICE_ERROR_JSON_RESPONSE,
                EcrcExceptionConstants.DATA_NOT_FOUND_ERROR), HttpStatus.NOT_FOUND));
        ResponseEntity<String> result = createNewCRCServiceController.createNewCRCService(requestNewCRCService);
        Assertions.assertEquals(String.format(EcrcExceptionConstants.WEBSERVICE_ERROR_JSON_RESPONSE,
                EcrcExceptionConstants.DATA_NOT_FOUND_ERROR), result.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @DisplayName("Error - createNewCRCService controller")
    @Test
    public void testServiceExceptionValidOrg() throws EcrcServiceException {
        requestNewCRCService = new RequestNewCRCService();
        Mockito.when(ecrcServices.createNewCRCService(requestNewCRCService)).thenReturn(new ResponseEntity<>(String.format(EcrcExceptionConstants.WEBSERVICE_ERROR_JSON_RESPONSE,
                EcrcExceptionConstants.DATA_NOT_FOUND_ERROR), HttpStatus.BAD_REQUEST));
        ResponseEntity<String> result = createNewCRCServiceController.createNewCRCService(requestNewCRCService);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
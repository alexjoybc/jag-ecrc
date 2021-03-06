package ca.bc.gov.open.ecrc.service;

import ca.bc.gov.open.ecrc.model.ValidationResponse;

/**
 * 
 * BCSC JWT Token Validation Services.  
 * 
 * @author shaunmillargov
 *
 */
public interface ECRCJWTValidationService {
   public abstract ValidationResponse validateBCSCAccessToken(String token);
   public abstract ValidationResponse validateBCSCIDToken(String token);
   public abstract ValidationResponse PERValidate(String token);
}

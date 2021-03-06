package ca.bc.gov.open.ecrc.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.bc.gov.open.ecrc.util.AES256;

/**
 * Tests for AES256 encryption util
 * 
 * @author BrendanBeachBCJ
 *
 */
public class AES256Test {
	private final String strToEncrypt = "string for testing encryption";
	private final String secret = "encryptionSecret";
	
	@DisplayName("Success - encrypt success, decrypt success")
	@Test
	public void testEncryptSuccessDecryptSuccess() {
		String encryptedString = AES256.encrypt(strToEncrypt);
		String decryptedString = AES256.decrypt(encryptedString);
		assertEquals(decryptedString, strToEncrypt);
	}
	
	@DisplayName("Error - decrypt error")
	@Test
	public void testDecryptError() {
		String decryptedString = AES256.decrypt(null);
		assertEquals(null, decryptedString);
	}
	
	@DisplayName("Error - encrypt error")
	@Test
	public void testEncryptError() {
		String encryptedString = AES256.encrypt(null);
		assertEquals(null, encryptedString);
	}
	
	
}

package ee.degeetia.dumonitor.storage;

import static org.junit.Assert.*;

import javax.servlet.ServletException;

import org.junit.Test;

import ee.degeetia.dumonitor.common.config.BuildProperty;

/**
 * Klassi {@link Heartbeat} ühiktestid.
 * 
 */
public class HeartbeatTest {

	/**
	 * Testib, et init() meetod laeb sisse konfiguratsioonifaili
	 * @throws ServletException 
	 */
	@Test
	public void testInit() throws ServletException {
		Heartbeat h = new Heartbeat();
		h.init();
		assertNotNull("Parameetrite laadimine ei õnnestunud", BuildProperty.NAME.getValue());
	}

}

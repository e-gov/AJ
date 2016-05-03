package ee.degeetia.dumonitor.storage;

import javax.servlet.ServletException;

import ee.degeetia.dumonitor.common.config.BuildProperty;
import ee.degeetia.dumonitor.common.config.PropertyLoader;
import ee.degeetia.dumonitor.common.heartbeat.HeartbeatServlet;

/**
 * Klass realiseerib andmesalvestaja nn. heartbeat teenuse, mis tagastab lihtsa JSON struktuuri teenuse põhiinfoga.
 * Täpsemalt vt vanemklassi kirjeldust.
 */
public class Heartbeat extends HeartbeatServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Servleti initsialiseerimine. Lotakse sisse teenuse konfiguratsioon failist build.properties
	 */
	@Override
	public void init() throws ServletException {
		loadProperties();
	}

	protected void loadProperties() {
		if (BuildProperty.NAME.getValue() == null) {
			PropertyLoader.loadProperties(BuildProperty.class, "build.properties");
		}
	}
}

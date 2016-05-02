package ee.degeetia.dumonitor.storage;

import java.io.IOException;

import javax.servlet.ServletException;

import ee.degeetia.dumonitor.common.config.BuildProperty;
import ee.degeetia.dumonitor.common.config.PropertyLoader;
import ee.degeetia.dumonitor.common.heartbeat.HeartbeatServlet;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;

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
			PropertyLoader propertyLoader = new PropertyLoader();
			try {
				propertyLoader.loadProperties(BuildProperty.class, "build.properties");
			} catch (IOException e) {
				ExceptionUtil.uncheck("Failed to load properties", e);
			}
		}
	}
}

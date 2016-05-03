package ee.degeetia.dumonitor.query;

import java.net.MalformedURLException;
import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.servlets.ProxyServlet;

import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.common.config.PropertyLoader;
import ee.degeetia.dumonitor.common.util.ExceptionUtil;

public class Proxy extends ProxyServlet {
	
	URI proxyServer;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Get proxy URL from configuration:
		loadProperties();
	}

	@Override
	protected HttpURI proxyHttpURI(String scheme, String serverName, int serverPort, String uri)
			throws MalformedURLException {
		return new HttpURI(proxyServer.resolve(uri));
	}

	protected void loadProperties() {
		PropertyLoader.loadProperties(Property.class, "dumonitor.properties", "default.properties");
		try {
			proxyServer = URI.create(Property.QUERY_TURVASERVER_URL.getValue());
		} catch (IllegalArgumentException e) {
			ExceptionUtil.uncheck("Invalid security server URL", e);
		}
	}
}

package ee.degeetia.dumonitor.query;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.eclipse.jetty.http.HttpURI;
import org.junit.Test;

import ee.degeetia.dumonitor.common.config.Property;

public class ProxyTest {

	@Test
	public void testInitServletConfig() throws ServletException {
		Proxy p = new Proxy();
		
		p.loadProperties();
		// Kontrollime tulemust:
		assertNotNull("Vigane konfiguratsioon", Property.QUERY_TURVASERVER_URL.getValue());
		assertNotNull("Proxy serveri URL on NULL", p.proxyServer);
		assertEquals("Proxy serveri URL pole õige", Property.QUERY_TURVASERVER_URL.getValue(), p.proxyServer.toString());
	}

	@Test
	public void testProxyHttpURIStringStringIntString() throws MalformedURLException, ServletException {

		Proxy p = new Proxy();
		p.loadProperties();
		assertNotNull("Vigane konfiguratsioon", Property.QUERY_TURVASERVER_URL.getValue());

		String path = "/test/123?param=value&param2=value2";
		String requiredResult = Property.QUERY_TURVASERVER_URL.getValue();
		if (requiredResult.charAt(requiredResult.length()-1) == '/') requiredResult = requiredResult.substring(0,  requiredResult.length()-1);
		requiredResult = requiredResult + path;
		
		HttpURI result = p.proxyHttpURI("http", "mingi.server.ee", 1234, path);
		
		String resultStr = result.toString();
		
		assertNotNull("Teisendatud URL on null", result);
		assertNotNull("Teisendatud URL on vigane", resultStr);
		assertEquals("Teisendus on vigane", requiredResult, resultStr);
	}

}

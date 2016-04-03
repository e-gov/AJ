package ee.degeetia.dumonitor.filter.log;

import ee.degeetia.dumonitor.filter.config.properties.Property;
import ee.degeetia.dumonitor.filter.config.properties.RuntimeProperty;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {

  @InjectMocks
  private LogService logService;
  @Mock
  private HttpClient httpClient;

  private Map<String, String> testEntry;

  @Before
  public void setUp() {
    testEntry = new HashMap<String, String>();
    testEntry.put("personcode", "33311110000");
    testEntry.put("action", "action");

    RuntimeProperty.APPLICATION_URL.setValue("http://localhost:8123");
  }

  @After
  public void tearDown() {
    RuntimeProperty.APPLICATION_URL.setValue(null);
  }

  @Test
  public void testCreateEntry_absolutePath() throws Exception {
    Property.LOGGER_REST_URL.setValue("http://127.0.0.1/api/log");
    logService.createEntry(testEntry);
    verify(httpClient).post(new URL("http://127.0.0.1/api/log"), testEntry);
  }

  @Test
  public void testCreateEntry_relativePath() throws Exception {
    Property.LOGGER_REST_URL.setValue("/api/log");
    logService.createEntry(testEntry);
    verify(httpClient).post(new URL("http://localhost:8123/api/log"), testEntry);
  }

  @Test
  public void testCreateEntry_relativePath_missingSlash() throws Exception {
    Property.LOGGER_REST_URL.setValue("api/log");
    logService.createEntry(testEntry);
    verify(httpClient).post(new URL("http://localhost:8123/api/log"), testEntry);
  }

}
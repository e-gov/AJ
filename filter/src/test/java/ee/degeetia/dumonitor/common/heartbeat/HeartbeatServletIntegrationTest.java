package ee.degeetia.dumonitor.common.heartbeat;

import ee.degeetia.dumonitor.common.heartbeat.Heartbeat;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import ee.degeetia.dumonitor.filter.http.HttpResponse;
import ee.degeetia.testutils.jetty.EmbeddedJettyHttpServer;
import ee.degeetia.testutils.jetty.EmbeddedJettyIntegrationTest;
import org.junit.Test;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeartbeatServletIntegrationTest extends EmbeddedJettyIntegrationTest {

  private final HttpClient httpClient = new HttpClient();

  public HeartbeatServletIntegrationTest() {
    super(new EmbeddedJettyHttpServer());
  }

  @Test
  public void test() throws Exception {
    HttpResponse<Heartbeat> response = httpClient.get(new URL(getApplicationUrl() + "/heartbeat"), Heartbeat.class);

    assertEquals(200, response.getStatus().getCode());

    Heartbeat heartbeat = response.getBody();
    assertEquals("dumonitor-filter", heartbeat.getSystemName());
    assertEquals("1.0", heartbeat.getVersion());
    assertEquals(getDate(2016, 3, 29, 18, 54, 0), heartbeat.getBuildDate());
    assertTrue(roughlyEquals(heartbeat.getStartupTime(), new Date(), 5));
    assertTrue(roughlyEquals(heartbeat.getServerTime(), new Date(), 5));
  }

  private Date getDate(int year, int month, int day, int hour, int minute, int second) {
    GregorianCalendar calendar = new GregorianCalendar(year, month, day, hour, minute, second);
    calendar.setTimeZone(new SimpleTimeZone(1000 * 3600 * 3, "+0300"));
    return calendar.getTime();
  }

  private boolean roughlyEquals(Date date1, Date date2, int maxDifferenceSeconds) {
    return Math.abs(date1.getTime() - date2.getTime()) < maxDifferenceSeconds * 1000;
  }

}

package ee.degeetia.dumonitor.filter.heartbeat;

import com.google.gson.Gson;
import ee.degeetia.dumonitor.common.config.BuildProperty;
import ee.degeetia.dumonitor.common.config.RuntimeProperty;
import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.filter.http.GsonFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class HeartbeatServlet extends HttpServlet {

  private final Gson gson = GsonFactory.createGson();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Heartbeat heartbeat = new Heartbeat();

    heartbeat.setSystemName(BuildProperty.NAME.getValue());
    heartbeat.setVersion(BuildProperty.VERSION.getValue());
    heartbeat.setBuildDate(BuildProperty.BUILD_DATE.getDate());
    heartbeat.setStartupTime(RuntimeProperty.APPLICATION_STARTUP_TIME.getDate());
    heartbeat.setServerTime(new Date());

    String json = gson.toJson(heartbeat);

    resp.setStatus(HttpServletResponse.SC_OK);
    resp.setContentLength(json.length());
    IOUtil.writeString(json, resp.getOutputStream());
  }

}

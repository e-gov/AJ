/**
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.degeetia.dumonitor.common.heartbeat;

import com.google.gson.Gson;
import ee.degeetia.dumonitor.common.config.BuildProperty;
import ee.degeetia.dumonitor.common.config.RuntimeProperty;
import ee.degeetia.dumonitor.common.util.GsonFactory;
import ee.degeetia.dumonitor.common.util.IOUtil;

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

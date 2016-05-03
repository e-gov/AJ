/*
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
package ee.degeetia.dumonitor.filter.log;

import ee.degeetia.dumonitor.common.config.Property;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import ee.degeetia.dumonitor.filter.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * This class handles sending logs to the logging component.
 */
public class LogService {

  private static final Logger LOG = LoggerFactory.getLogger(LogService.class);

  private HttpClient httpClient = new HttpClient();

  /**
   * Sends the loggable fields to the URL specified by the dumonitor.filter.logger.rest.url property.
   *
   * @param logEntry the log entry to create
   */
  public void createEntry(LogEntry logEntry) {
    logEntry.setLogtime(new Date());
    try {
      httpClient.post(Property.LOGGER_REST_URL.getURL(), logEntry);
    } catch (HttpException e) {
      LOG.error("Failed to send logs over REST", e);
    }
  }

}

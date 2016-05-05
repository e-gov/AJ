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
import ee.degeetia.dumonitor.common.config.RuntimeProperty;
import ee.degeetia.dumonitor.filter.http.HttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {

  @InjectMocks
  private LogService logService;
  @Mock
  private HttpClient httpClient;

  private LogEntry testEntry;

  @Before
  public void setUp() {
    testEntry = new LogEntry();
    testEntry.setPersoncode("33311110000");
    testEntry.setAction("action");

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
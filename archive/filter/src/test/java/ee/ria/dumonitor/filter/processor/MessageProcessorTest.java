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
package ee.ria.dumonitor.filter.processor;

import ee.ria.dumonitor.common.util.IOUtil;
import ee.ria.dumonitor.common.util.ResourceUtil;
import ee.ria.dumonitor.filter.log.LogEntry;
import ee.ria.dumonitor.filter.log.LogService;
import ee.ria.testutils.jetty.EmbeddedJettyHttpServer;
import ee.ria.testutils.jetty.EmbeddedJettyIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static ee.ria.dumonitor.common.util.ObjectUtil.eq;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class MessageProcessorTest extends EmbeddedJettyIntegrationTest {

  @InjectMocks
  private MessageProcessor messageProcessor;

  @Mock
  private LogService logService;

  public MessageProcessorTest() {
    super(new EmbeddedJettyHttpServer());
  }

  @Test
  public void testProcess() throws Exception {
    byte[] content = IOUtil.readBytes(ResourceUtil.getClasspathResourceAsStream("test_response.xml"));
    messageProcessor.process(content, "text/xml");

    LogEntry logEntry = getLogEntry("47101010033");

    verify(logService).createEntry(argThat(matches(logEntry)));
  }

  @Test
  public void testProcessBlacklisted() throws Exception {
    byte[] content = IOUtil.readBytes(ResourceUtil.getClasspathResourceAsStream("test_response_blacklisted.xml"));
    messageProcessor.process(content, "text/xml");
    verifyZeroInteractions(logService);
  }

  @Test
  public void testProcessMultipleIsikukood() throws Exception {
    byte[] content =
        IOUtil.readBytes(ResourceUtil.getClasspathResourceAsStream("test_response_multiple_isikukood.xml"));
    messageProcessor.process(content, "text/xml");

    LogEntry logEntry1 = getLogEntry("47101010033");
    LogEntry logEntry2 = getLogEntry("47101010044");

    verify(logService).createEntry(argThat(matches(logEntry1)));
    verify(logService).createEntry(argThat(matches(logEntry2)));
  }

  private LogEntry getLogEntry(String isikukood) {
    LogEntry logEntry1 = new LogEntry();
    logEntry1.setLogtime(new Date());
    logEntry1.setPersoncode(isikukood);
    logEntry1.setAction("Get Person Data");
    logEntry1.setSender("Test AK");
    logEntry1.setReceiver("Test receiver");
    logEntry1.setRestrictions("A");
    logEntry1.setSendercode("MEMBER1");
    logEntry1.setReceivercode("MEMBER2");
    logEntry1.setActioncode("getPersonData.v1");
    logEntry1.setXroadrequestid("4894e35d-bf0f-44a6-867a-8e51f1daa7e1");
    logEntry1.setXroadservice("getPersonData");
    logEntry1.setUsercode("EE12345678901");
    return logEntry1;
  }

  private ArgumentMatcher<LogEntry> matches(final LogEntry logEntry) {
    return new ArgumentMatcher<LogEntry>() {
      @Override
      public boolean matches(Object argument) {
        if (!(argument instanceof LogEntry)) {
          return false;
        }
        LogEntry l = (LogEntry) argument;
        return eq(l.getPersoncode(), logEntry.getPersoncode()) &&
               eq(l.getAction(), logEntry.getAction()) &&
               eq(l.getSender(), logEntry.getSender()) &&
               eq(l.getReceiver(), logEntry.getReceiver()) &&
               eq(l.getRestrictions(), logEntry.getRestrictions()) &&
               eq(l.getSendercode(), logEntry.getSendercode()) &&
               eq(l.getReceivercode(), logEntry.getReceivercode()) &&
               eq(l.getActioncode(), logEntry.getActioncode()) &&
               eq(l.getXroadrequestid(), logEntry.getXroadrequestid()) &&
               eq(l.getXroadservice(), logEntry.getXroadservice()) &&
               eq(l.getUsercode(), logEntry.getUsercode());
      }
    };
  }

}
package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.common.util.ResourceUtil;
import ee.degeetia.dumonitor.filter.log.LogEntry;
import ee.degeetia.dumonitor.filter.log.LogService;
import ee.degeetia.testutils.jetty.EmbeddedJettyHttpServer;
import ee.degeetia.testutils.jetty.EmbeddedJettyIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static ee.degeetia.dumonitor.common.util.ObjectUtil.eq;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

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

    LogEntry logEntry = new LogEntry();
    logEntry.setLogtime(new Date());
    logEntry.setPersoncode("47101010033");
    logEntry.setAction("Get Person Data");
    logEntry.setSender("Test AK");
    logEntry.setReceiver("Test receiver");
    logEntry.setRestrictions("A");
    logEntry.setSendercode("MEMBER1");
    logEntry.setReceivercode("MEMBER2");
    logEntry.setActioncode("getPersonData.v1");
    logEntry.setXroadrequestid("4894e35d-bf0f-44a6-867a-8e51f1daa7e1");
    logEntry.setXroadservice("getPersonData");
    logEntry.setUsercode("EE12345678901");

    verify(logService).createEntry(argThat(matches(logEntry)));
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
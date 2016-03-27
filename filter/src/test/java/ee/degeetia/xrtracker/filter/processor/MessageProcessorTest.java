package ee.degeetia.xrtracker.filter.processor;

import ee.degeetia.testutils.soap.SoapTestUtil.XmlElement;
import ee.degeetia.xrtracker.filter.log.LogEntry;
import ee.degeetia.xrtracker.filter.log.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static ee.degeetia.testutils.soap.SoapTestUtil.createByteArray;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageProcessorTest {

  @InjectMocks
  private MessageProcessor messageProcessor;

  @Mock
  private LogService logService;

  @Test
  public void testProcess() throws Exception {
    byte[] message = createByteArray(new XmlElement("testElement", "test"), new XmlElement("action", "actionValue"));
    messageProcessor.process(message, "text/xml");

    LogEntry logEntry = new TestLogEntry();
    logEntry.setAction("actionValue");
    verify(logService).createEntry(logEntry);
  }

  private class TestLogEntry extends LogEntry {

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || !o.getClass().isAssignableFrom(getClass())) {
        return false;
      }

      LogEntry logEntry = (LogEntry) o;

      if (getPersoncode() != null
          ? !getPersoncode().equals(logEntry.getPersoncode())
          : logEntry.getPersoncode() != null) {
        return false;
      }
      if (getAction() != null ? !getAction().equals(logEntry.getAction()) : logEntry.getAction() != null) {
        return false;
      }
      if (getSender() != null ? !getSender().equals(logEntry.getSender()) : logEntry.getSender() != null) {
        return false;
      }
      if (getReceiver() != null ? !getReceiver().equals(logEntry.getReceiver()) : logEntry.getReceiver() != null) {
        return false;
      }
      if (getRestrictions() != null
          ? !getRestrictions().equals(logEntry.getRestrictions())
          : logEntry.getRestrictions() != null) {
        return false;
      }
      if (getSendercode() != null
          ? !getSendercode().equals(logEntry.getSendercode())
          : logEntry.getSendercode() != null) {
        return false;
      }
      if (getReceivercode() != null
          ? !getReceivercode().equals(logEntry.getReceivercode())
          : logEntry.getReceivercode() != null) {
        return false;
      }
      if (getActioncode() != null
          ? !getActioncode().equals(logEntry.getActioncode())
          : logEntry.getActioncode() != null) {
        return false;
      }
      if (getXroadrequestid() != null
          ? !getXroadrequestid().equals(logEntry.getXroadrequestid())
          : logEntry.getXroadrequestid() != null) {
        return false;
      }
      if (getXroadservice() != null
          ? !getXroadservice().equals(logEntry.getXroadservice())
          : logEntry.getXroadservice() != null) {
        return false;
      }
      return getUsercode() != null ? getUsercode().equals(logEntry.getUsercode()) : logEntry.getUsercode() == null;

    }

  }

}
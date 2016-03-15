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
    byte[] message = createByteArray(new XmlElement("testElement", "testElementValue"));
    messageProcessor.process(message, "text/xml");

    LogEntry logEntry = new LogEntry();
    logEntry.setTestElement("testElementValue");
    verify(logService).createEntry(logEntry);
  }

}
package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.filter.log.LogService;
import ee.degeetia.dumonitor.filter.util.IOUtil;
import ee.degeetia.dumonitor.filter.util.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageProcessorTest {

  @InjectMocks
  private MessageProcessor messageProcessor;

  @Mock
  private LogService logService;

  @Test
  public void testProcess() throws Exception {
    byte[] content = IOUtil.readBytes(ResourceUtil.getClasspathResourceAsStream("test_response.xml"));
    messageProcessor.process(content, "text/xml");

    Map<String, String> fields = new HashMap<String, String>();
    fields.put("personcode", "47101010033");
    fields.put("action", "Get Person Data");
    fields.put("sender", "Test AK");
    fields.put("receiver", "Test receiver");
    fields.put("restrictions", "A");
    fields.put("sendercode", "MEMBER1");
    fields.put("receivercode", "MEMBER2");
    fields.put("actioncode", "getPersonData.v1");
    fields.put("xroadrequestid", "4894e35d-bf0f-44a6-867a-8e51f1daa7e1");
    fields.put("xroadservice", "getPersonData");
    fields.put("usercode", "EE12345678901");

    verify(logService).createEntry(fields);
  }

}
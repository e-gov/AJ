package ee.degeetia.xrtracker.filter;

import ee.degeetia.xrtracker.filter.core.ExecutorManager;
import ee.degeetia.xrtracker.filter.util.IOUtil;
import ee.degeetia.xrtracker.filter.util.SoapUtil;
import ee.degeetia.xrtracker.filter.util.XPathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class XRoadMessageProcessor {

  private static final Logger LOG = LogManager.getLogger(XRoadMessageProcessor.class);

  private final ExecutorService executor = Executors.newCachedThreadPool();

  public XRoadMessageProcessor() {
    ExecutorManager.getManager().addManagedExecutor(executor);
  }

  public void submitForProcessing(final byte[] content, final String contentType) {
    executor.submit(new Runnable() {
      @Override
      public void run() {
        try {
          process(content, contentType);
        } catch (Exception e) {
          LOG.error("Failed to process message: " + new String(content, IOUtil.UTF_8), e);
        }
      }
    });
  }

  private void process(byte[] content, String contentType) throws IOException, SOAPException {
    SOAPMessage message = SoapUtil.parseMessage(content, contentType);
    SOAPBody body = message.getSOAPBody();

    if (XPathUtil.evaluateAny(body, "//testElement")) {
      LOG.debug("Expression evaluated to true, this message is going to be logged");
    }
  }

}

package ee.degeetia.xrtracker.filter;

import ee.degeetia.testutils.jetty.EmbeddedJettyIntegrationTest;
import ee.degeetia.testutils.soap.SoapTestUtil.XmlElement;
import ee.degeetia.xrtracker.filter.config.properties.Property;
import ee.degeetia.xrtracker.filter.util.IOUtil;
import ee.degeetia.xrtracker.filter.util.ResourceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import static ee.degeetia.testutils.soap.SoapTestUtil.createMessage;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class XRoadInterceptorServletIntegrationTest extends EmbeddedJettyIntegrationTest {

  private static final String TEST_WS_URL = "http://localhost:8123" + Property.ANDMEKOGU_INTERCEPTOR_PATH.getString();

  private SOAPConnection connection;

  @BeforeClass
  public static void createFakeAndmekoguServlet() {
    createServlet(new HttpServlet() {
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(request.getContentType());
        response.setContentLength(request.getContentLength());
        IOUtil.pipe(request.getInputStream(), response.getOutputStream());
      }
    }, Property.ANDMEKOGU_URL.getURL().getPath());
  }

  @BeforeClass
  public static void createFakeLoggerRestEndpoint() {
    createServlet(new HttpServlet() {
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentLength(0);
      }
    }, Property.LOGGER_REST_URL.getURL().getPath());
  }

  @Before
  public void createConnection() throws SOAPException {
    connection = SOAPConnectionFactory.newInstance().createConnection();
  }

  @After
  public void closeConnection() throws SOAPException {
    connection.close();
  }

  @Test
  public void testEmptyBody() throws SOAPException {
    SOAPMessage request = createMessage();
    SOAPMessage response = connection.call(request, TEST_WS_URL);

    Iterator childElements = response.getSOAPBody().getChildElements();
    assertFalse(childElements.hasNext());
  }

  @Test
  public void testSingleElement() throws SOAPException {
    SOAPMessage request = createMessage(new XmlElement("testElement", "testElementValue"));
    SOAPMessage response = connection.call(request, TEST_WS_URL);

    Node firstChild = response.getSOAPBody().getFirstChild();
    assertThat(firstChild.getNodeName(), is("testElement"));
    assertThat(firstChild.getTextContent(), is("testElementValue"));
  }

  @Test
  public void testSpecialCharacters() throws SOAPException {
    SOAPMessage request = createMessage(new XmlElement("õäöü", "šž"));
    SOAPMessage response = connection.call(request, TEST_WS_URL);

    Node firstChild = response.getSOAPBody().getFirstChild();
    assertThat(firstChild.getNodeName(), is("õäöü"));
    assertThat(firstChild.getTextContent(), is("šž"));
  }

  @Test
  public void testAttachment() throws SOAPException {
    SOAPMessage request = createMessage(new XmlElement("testElement", "testElementValue"));

    URL attachmentUrl = ResourceUtil.getClasspathResource("test_attachment.jpg");
    AttachmentPart originalAttachment = request.createAttachmentPart(new DataHandler(attachmentUrl));
    originalAttachment.setContentId("test_attachment");
    request.addAttachmentPart(originalAttachment);

    SOAPMessage response = connection.call(request, TEST_WS_URL);

    Iterator attachments = response.getAttachments();
    assertTrue(attachments.hasNext());

    AttachmentPart responseAttachment = (AttachmentPart) attachments.next();
    assertThat(responseAttachment.getContentId(), is(originalAttachment.getContentId()));
    assertThat(responseAttachment.getSize(), is(originalAttachment.getSize()));
  }

  @Test
  public void testLargeRequest() throws SOAPException {
    int numElements = 10000;

    XmlElement[] elements = new XmlElement[numElements];
    for (int i = 0; i < numElements; i++) {
      elements[i] = new XmlElement("element" + i, "value" + i);
    }

    SOAPMessage request = createMessage(elements);
    SOAPMessage response = connection.call(request, TEST_WS_URL);

    NodeList childNodes = response.getSOAPBody().getChildNodes();
    assertThat(childNodes.getLength(), is(numElements));
  }

  @Test
  public void testConcurrentRequests() throws InterruptedException, ExecutionException, SOAPException {
    int numThreads = 10;
    int numRequests = 1000;

    ExecutorService executor = Executors.newFixedThreadPool(numThreads);

    List<Callable<SOAPMessage>> tasks = new ArrayList<Callable<SOAPMessage>>(numRequests);
    for (int i = 0; i < numRequests; i++) {
      tasks.add(new Callable<SOAPMessage>() {
        @Override
        public SOAPMessage call() throws Exception {
          return connection.call(createMessage(new XmlElement("test", "test")), TEST_WS_URL);
        }
      });
    }

    List<Future<SOAPMessage>> futureResponses = executor.invokeAll(tasks);
    executor.shutdown();

    if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
      for (Future<SOAPMessage> futureResponse : futureResponses) {
        SOAPBody body = futureResponse.get().getSOAPBody();
        assertThat(body.getFirstChild().getNodeName(), is("test"));
        assertThat(body.getFirstChild().getTextContent(), is("test"));
      }
    } else {
      executor.shutdownNow();
      fail();
    }
  }

}

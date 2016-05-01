package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.filter.core.ExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/**
 * This class acts as a queue for the MessageProcessor.
 */
public class MessageProcessorQueue {

  private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorQueue.class);

  private final ExecutorService executor = ExecutorManager.newThreadPoolExecutor();
  private final MessageProcessor processor = new MessageProcessor();

  /**
   * Adds a message to the queue. Messages will be processed by a thread pool executor. The number of threads and the
   * capacity of the queue is configured in the application properties. If the queue is full, the message is not
   * processed and an error is printed to the application log.
   *
   * @param content     the raw message content
   * @param contentType the type of the message (usually text/xml)
   */
  public void submit(final byte[] content, final String contentType) {
    try {
      executor.submit(new Runnable() {
        @Override
        public void run() {
          processor.process(content, contentType);
        }
      });
    } catch (RejectedExecutionException e) {
      LOG.error("Message processor queue is full, discarding message");
    }
  }

}

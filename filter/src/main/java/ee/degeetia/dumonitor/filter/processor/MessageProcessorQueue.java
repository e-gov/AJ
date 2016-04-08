package ee.degeetia.dumonitor.filter.processor;

import ee.degeetia.dumonitor.common.util.IOUtil;
import ee.degeetia.dumonitor.filter.core.ExecutorManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageProcessorQueue {

  private static final Logger LOG = LogManager.getLogger(MessageProcessorQueue.class);

  private final ExecutorService executor = Executors.newCachedThreadPool();
  private final MessageProcessor processor = new MessageProcessor();

  public MessageProcessorQueue() {
    ExecutorManager.getManager().addManagedExecutor(executor);
  }

  public void submit(final byte[] content, final String contentType) {
    executor.submit(new Runnable() {
      @Override
      public void run() {
        try {
          processor.process(content, contentType);
        } catch (Exception e) {
          if (LOG.isErrorEnabled()) {
            LOG.error("Failed to process message: " + new String(content, IOUtil.UTF_8), e);
          }
        }
      }
    });
  }

}

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ee.ria.dumonitor.filter.core.ExecutorManager;

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

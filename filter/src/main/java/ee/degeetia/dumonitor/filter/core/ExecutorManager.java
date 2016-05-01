package ee.degeetia.dumonitor.filter.core;


import ee.degeetia.dumonitor.common.config.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Class for managing ExecutorServices, i.e. gracefully letting them finish and shutting them down in case of
 * an application shutdown.
 */
public final class ExecutorManager {

  private static final Logger LOG = LoggerFactory.getLogger(ExecutorManager.class);

  private static final Set<ExecutorService> MANAGED_EXECUTORS;

  static {
    MANAGED_EXECUTORS = Collections.newSetFromMap(new ConcurrentHashMap<ExecutorService, Boolean>());
  }

  private ExecutorManager() {
    throw new UnsupportedOperationException();
  }

  /**
   * Creates a new thread pool executor with the parameters specified in application properties. The created executor
   * will be shut down when {@link #shutdownAll()} is called.
   *
   * @return the created thread pool executor
   */
  public static ExecutorService newThreadPoolExecutor() {
    int corePoolSize = Property.EXECUTOR_THREAD_POOL_SIZE.getInteger();
    int maximumPoolSize = corePoolSize == 0 ? Integer.MAX_VALUE : corePoolSize;

    BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(Property.EXECUTOR_QUEUE_CAPACITY.getInteger());
    ExecutorService executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 1L, TimeUnit.MINUTES, queue);
    MANAGED_EXECUTORS.add(executor);
    return executor;
  }

  /**
   * Attempts to shut down all managed executors, allowing submitted tasks to be completed. In case of a timeout,
   * terminates all tasks and forces the executors to shut down immediately.
   */
  public static void shutdownAll() {
    if (MANAGED_EXECUTORS.isEmpty()) {
      return;
    }

    LOG.info("Shutting down {} thread pool executors", MANAGED_EXECUTORS.size());

    final Iterator<ExecutorService> iterator = MANAGED_EXECUTORS.iterator();

    ExecutorService shutdownExecutor = Executors.newFixedThreadPool(MANAGED_EXECUTORS.size());
    while (iterator.hasNext()) {
      final ExecutorService executorService = iterator.next();
      shutdownExecutor.submit(new Runnable() {
        @Override
        public void run() {
          shutdown(executorService);
          iterator.remove();
        }
      });
    }
    shutdown(shutdownExecutor);
  }

  private static void shutdown(ExecutorService executorService) {
    int timeout = Property.EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS.getInteger();

    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      LOG.error("Failed to shutdown thread pool executor", e);
    }
  }

}

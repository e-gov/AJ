package ee.degeetia.dumonitor.filter.core;

import ee.degeetia.dumonitor.filter.config.properties.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Singleton class for managing ExecutorServices, i.e. gracefully letting them finish and shutting them down in case of
 * an application shutdown.
 */
public final class ExecutorManager {

  private static final Logger LOG = LogManager.getLogger(ExecutorManager.class);

  private static final ExecutorManager INSTANCE = new ExecutorManager();

  private final Set<ExecutorService> managedExecutors;

  private ExecutorManager() {
    managedExecutors = Collections.newSetFromMap(new ConcurrentHashMap<ExecutorService, Boolean>());
  }

  /**
   * @return the singleton instance of {@link ExecutorManager}
   */
  public static ExecutorManager getManager() {
    return INSTANCE;
  }

  /**
   * Adds an executor service to the list of managed executor services.
   *
   * @param executorService the {@link ExecutorService} to add
   */
  public void addManagedExecutor(ExecutorService executorService) {
    managedExecutors.add(executorService);
  }

  /**
   * Attempts to shut down all managed executor services, allowing submitted tasks to be completed. In case of a
   * timeout, terminates all tasks and forces the executors to shut down.
   */
  public void shutdownAll() {
    if (managedExecutors.isEmpty()) {
      return;
    }

    LOG.info("Shutting down {} thread pool executors", managedExecutors.size());

    final Iterator<ExecutorService> iterator = managedExecutors.iterator();

    ExecutorService shutdownExecutor = Executors.newFixedThreadPool(managedExecutors.size());
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

  private void shutdown(ExecutorService executorService) {
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(Property.EXECUTOR_SHUTDOWN_TIMEOUT_SECONDS.getInt(), TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      LOG.error("Failed to shutdown thread pool executor", e);
    }
  }

}

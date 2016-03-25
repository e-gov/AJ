package ee.degeetia.xrtracker.filter.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ExecutorManager {

  private static final Logger LOG = LogManager.getLogger(ExecutorManager.class);

  private static final ExecutorManager INSTANCE = new ExecutorManager();

  private static final int SHUTDOWN_TIMEOUT_SECONDS = 10;

  private final Set<ExecutorService> managedExecutors;

  private ExecutorManager() {
    managedExecutors = Collections.newSetFromMap(new ConcurrentHashMap<ExecutorService, Boolean>());
  }

  public static ExecutorManager getManager() {
    return INSTANCE;
  }

  public void addManagedExecutor(ExecutorService executorService) {
    managedExecutors.add(executorService);
  }

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
      if (!executorService.awaitTermination(SHUTDOWN_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      LOG.error("Failed to shutdown thread pool executor", e);
    }
  }

}

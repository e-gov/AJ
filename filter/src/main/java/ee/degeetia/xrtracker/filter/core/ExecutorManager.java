package ee.degeetia.xrtracker.filter.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ExecutorManager {

  private static final Logger LOG = LogManager.getLogger(ExecutorManager.class);

  private static final ExecutorManager INSTANCE = new ExecutorManager();

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
    final int shutdownTimeout = 10;

    LOG.info("Shutting down {} thread pool executors", managedExecutors.size());

    ExecutorService shutdownExecutor = Executors.newFixedThreadPool(managedExecutors.size());

    for (final ExecutorService executorService : managedExecutors) {
      shutdownExecutor.submit(new Runnable() {
        @Override
        public void run() {
          executorService.shutdown();
          try {
            if (!executorService.awaitTermination(shutdownTimeout, TimeUnit.SECONDS)) {
              List<Runnable> stoppedTasks = executorService.shutdownNow();
              LOG.warn("{} did not shutdown in {} seconds, stopping {} tasks",
                       executorService,
                       shutdownTimeout,
                       stoppedTasks.size());
            }
          } catch (InterruptedException e) {
            LOG.error("Failed to shutdown thread pool executor", e);
          }
        }
      });
    }

    shutdownExecutor.shutdown();
  }

}

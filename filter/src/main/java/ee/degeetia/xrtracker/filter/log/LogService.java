package ee.degeetia.xrtracker.filter.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: interface
public class LogService {

  private static final Logger LOG = LogManager.getLogger(LogService.class);

  public void createEntry(LogEntry logEntry) {
    LOG.info("Creating log entry: {}", logEntry);
  }

}


package ee.degeetia.xrtracker.filter.log;

public class LogEntry {

  private String testElement;

  public String getTestElement() {
    return testElement;
  }

  public void setTestElement(String testElement) {
    this.testElement = testElement;
  }

  @Override
  public String toString() {
    return "LogEntry{" +
        "testElement='" + testElement + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LogEntry logEntry = (LogEntry) o;

    return testElement != null ? testElement.equals(logEntry.testElement) : logEntry.testElement == null;

  }

  @Override
  public int hashCode() {
    return testElement != null ? testElement.hashCode() : 0;
  }

}

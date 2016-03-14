package ee.degeetia.xrtracker.filter.core.config;

public enum Property {

  TURVASERVER_URL("xrtracker.filter.turvaserver.url", true),
  ANDMEKOGU_URL("xrtracker.filter.andmekogu.url", true),
  TURVASERVER_INTERCEPTOR_PATH("xrtracker.filter.turvaserver.interceptor.path", true),
  ANDMEKOGU_INTERCEPTOR_PATH("xrtracker.filter.andmekogu.interceptor.path", true);

  private String key;
  private String value;
  private boolean required;

  Property(String key, boolean required) {
    this.key = key;
    this.required = required;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public boolean isRequired() {
    return required;
  }

  void setValue(String value) {
    this.value = value;
  }

}

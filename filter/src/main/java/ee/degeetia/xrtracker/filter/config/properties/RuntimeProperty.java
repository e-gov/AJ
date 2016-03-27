package ee.degeetia.xrtracker.filter.config.properties;

public enum RuntimeProperty {

  APPLICATION_URL;

  private String value;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean isSet() {
    return value != null;
  }

}

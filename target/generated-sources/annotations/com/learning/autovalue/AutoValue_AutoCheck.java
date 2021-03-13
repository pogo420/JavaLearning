package com.learning.autovalue;

import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_AutoCheck extends AutoCheck {

  private final String name;

  private final int id;

  private AutoValue_AutoCheck(
      String name,
      int id) {
    this.name = name;
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "AutoCheck{"
        + "name=" + name + ", "
        + "id=" + id
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof AutoCheck) {
      AutoCheck that = (AutoCheck) o;
      return this.name.equals(that.getName())
          && this.id == that.getId();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= id;
    return h$;
  }

  static final class Builder extends AutoCheck.Builder {
    private String name;
    private Integer id;
    Builder() {
    }
    @Override
    public AutoCheck.Builder setName(String name) {
      if (name == null) {
        throw new NullPointerException("Null name");
      }
      this.name = name;
      return this;
    }
    @Override
    public AutoCheck.Builder setId(int id) {
      this.id = id;
      return this;
    }
    @Override
    public AutoCheck build() {
      String missing = "";
      if (this.name == null) {
        missing += " name";
      }
      if (this.id == null) {
        missing += " id";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_AutoCheck(
          this.name,
          this.id);
    }
  }

}

package com.aurora.core.database;

public enum DataLocation {
  INTERNAL("Internal"),
  EXTERNAL("External");

  private String dataLocationValue;

  /**
   * DataLocation constructor, sets dataLocationValue.
   */
  DataLocation(String dataLocationValue) {
    this.dataLocationValue = dataLocationValue;
  }

  /**
   * returns Enum value from String.
   */
  public static DataLocation getEnum(String dataLocationValue) {
    for (DataLocation b : DataLocation.values()) {
      if (b.dataLocationValue.equalsIgnoreCase(dataLocationValue)) {
        return b;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.dataLocationValue;
  }
}

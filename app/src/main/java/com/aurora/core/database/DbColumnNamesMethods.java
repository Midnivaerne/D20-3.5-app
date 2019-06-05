package com.aurora.core.database;

import com.aurora.core.models.helpers.CoreHelper;

public interface DbColumnNamesMethods<E extends Enum<E>, C extends CoreHelper> {

  static <E extends Enum<E> & DbColumnNamesMethods<E, C>, C extends CoreHelper> E fromString(String name, Class<E> type) {
    if (name == null) {
      return null;
    }
    for (E item : type.getEnumConstants()) {
      if (item.getColumnName().equals(name)) {
        return item;
      }
    }
    throw new IllegalArgumentException("No column with name " + name + " found");
  }

  static <E extends Enum<E> & DbColumnNamesMethods<E, C>, C extends CoreHelper> boolean contains(String name, Class<E> type) {
    for (E item : type.getEnumConstants()) {
      if (item.toString().equals(name)) {
        return true;
      }
    }
    return false;
  }

  String getColumnName();

  void setParameter(C item, String data);

  Boolean getColumnIsUsed();

  void setColumnIsUsed(Boolean columnIsUsed);
}

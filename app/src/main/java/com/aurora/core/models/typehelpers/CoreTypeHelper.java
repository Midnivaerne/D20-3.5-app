package com.aurora.core.models.typehelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.helper.BaseDaO;
import com.aurora.core.models.helpers.CoreHelper;

public interface CoreTypeHelper<E extends Enum<E>, C extends CoreHelper> {

  static <E extends Enum<E> & CoreTypeHelper<E, C>, C extends CoreHelper> E fromString(String itemTypeString, Class<E> type) {
    for (E itemType : type.getEnumConstants()) {
      if (itemType.toString().equalsIgnoreCase(itemTypeString)) {
        return itemType;
      }
    }
    throw new IllegalArgumentException("No ItemType with value " + itemTypeString + " found");
  }

  static <E extends Enum<E> & CoreTypeHelper<E, C>, C extends CoreHelper> boolean contains(String name, Class<E> type) {
    for (E itemType : type.getEnumConstants()) {
      if (itemType.toString().equals(name)) {
        return true;
      }
    }
    return false;
  }

  static List<? extends CoreTypeHelper> values() {
    List<CoreTypeHelper> out = new ArrayList<>();
    out.addAll(Arrays.asList(RulesType.values()));
    out.addAll(Arrays.asList(ItemType.values()));
    return out;
  }

  BaseDaO<C> getDaO(DatabaseHolder databaseHolder);

  List<? extends C> getDatabaseList(DatabaseHolder databaseHolder);

  Map<Integer, ? extends C> getDatabaseMap(DatabaseHolder databaseHolder);

  default List<C> getAllFromDatabase(DatabaseHolder databaseHolder) {
    return getDaO(databaseHolder).getAllObjectsAsObject();
  }

  CoreHelper getNewObject();

  void fromHolderToDatabase(DatabaseHolder databaseHolder);

  void fromDatabaseToHolder(DatabaseHolder databaseHolder);

  default void deleteAll(DatabaseHolder databaseHolder) {
    deleteAllFromDatabase(databaseHolder);
    deleteAllFromHolder(databaseHolder);
  }

  default void deleteAllFromHolder(DatabaseHolder databaseHolder) {
    getDatabaseList(databaseHolder).clear();
    getDatabaseMap(databaseHolder).clear();
  }

  default void deleteAllFromDatabase(DatabaseHolder databaseHolder) {
    getDaO(databaseHolder).deleteAll();
  }
}

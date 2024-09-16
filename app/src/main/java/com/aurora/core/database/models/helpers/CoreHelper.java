package com.aurora.core.database.models.helpers;

import java.util.List;

public interface CoreHelper {

  String showAllContentAsString();

  String getName();

  default CoreHelper getObjectWithNameFromList(String findMe, List<CoreHelper> list) {
    for (CoreHelper object : list) {
      if (object.getName().equals(findMe)) {
        return object;
      }
    }
    return null;
  }
}

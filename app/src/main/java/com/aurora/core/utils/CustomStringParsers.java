package com.aurora.core.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.aurora.core.models.typehelpers.CoreTypeHelper;
import com.aurora.core.models.typehelpers.ItemType;

public class CustomStringParsers {

  public static String[] stringWithCommaToTable(String stringToSplit) {
    if (stringToSplit != null) {
      return stringToSplit.split(",");
    }
    Log.e("CustomStringParsers ", " stringWithCommaToTable > stringToSplit is empty");
    return null;
  }

  public static String stringWithCommaToSum(String stringToSum) {
    if (stringToSum != null) {
      return Integer.toString(Arrays.stream(stringToSum.split(",")).mapToInt(Integer::parseInt).sum());
    }
    Log.e("CustomStringParsers ", " stringWithCommaToSum > stringToSum is empty");
    return null;
  }

  public static Map<ItemType, Map<Integer, String>> stringWithCommaAndBracketsToMap(String stringToSplit) {
    if (stringToSplit != null) {
      Map<ItemType, Map<Integer, String>> out = new HashMap<>();
      if (stringToSplit.contains("{") && stringToSplit.contains("}") && stringToSplit.contains("=")) {
        String[] stringWithoutBracketsTable = stringWithCommaToTable(deBracket(stringToSplit));
        for (String tableObject : stringWithoutBracketsTable) {
          if (CoreTypeHelper.contains(tableObject.trim(), ItemType.class)) {
            out.put(CoreTypeHelper.fromString(tableObject.trim(), ItemType.class), bracketContents(tableObject.trim(), stringToSplit));
          } else {
            Log.e("CustomStringParsers ", " ItemType " + tableObject.trim() + " not identified");
          }
        }
      } else {
        Log.e("CustomStringParsers ", " Wrong format of: " + stringToSplit);
        throw new IllegalArgumentException("Wrong format");
      }
      return out;
    }
    Log.e("CustomStringParsers ", "stringWithCommaAndBracketsToMap > stringToSplit is empty");
    return null;
  }

  private static String deBracket(String stringToSplit) {
    if (stringToSplit != null) {
      String out = stringToSplit;
      for (int i = 0; i < stringToSplit.split("\\{").length - 1; i++) {
        int start = out.indexOf("{");
        int stop = out.indexOf("}") + 1;
        String begining = out.substring(0, start);
        String end = out.substring(stop);
        out = begining + end;
      }
      return out;
    }
    return null;
  }

  private static Map<Integer, String> bracketContents(String tableObject, String stringToSplit) {
    if (stringToSplit != null && tableObject != null) {
      @SuppressLint("UseSparseArrays") Map<Integer, String> out = new HashMap<>();
      String bracketContentMerged = stringToSplit.split(tableObject + "\\{")[1].split("\\}")[0];
      String[] values = stringWithCommaToTable(bracketContentMerged);
      for (String value : values) {
        int id = Integer.parseInt(value.split("=")[0]);
        String name = value.split("=")[1];
        out.put(id, name);
      }
      return out;
    }
    Log.e("CustomStringParsers ", "bracketContents > tableObject or stringToSplit is empty");
    return null;
  }
}

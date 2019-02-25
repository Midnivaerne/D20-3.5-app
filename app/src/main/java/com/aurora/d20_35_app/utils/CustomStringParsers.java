package com.aurora.d20_35_app.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.aurora.d20_35_app.models.typeHelpers.CoreTypeHelper;
import com.aurora.d20_35_app.models.typeHelpers.ItemType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomStringParsers {

    public static String[] StringWithCommaToTable(String stringToSplit) {
        if (stringToSplit != null) {
            return stringToSplit.split(",");
        }
        Log.e("CustomStringParsers ", " StringWithCommaToTable > stringToSplit is empty");
        return null;
    }

    public static String StringWithCommaToSum(String stringToSum) {
        if (stringToSum != null) {
            return Integer.toString(Arrays.stream(stringToSum.split(",")).mapToInt(Integer::parseInt).sum());
        }
        Log.e("CustomStringParsers ", " StringWithCommaToSum > stringToSum is empty");
        return null;
    }

    public static Map<ItemType, Map<Integer, String>> StringWithCommaAndBracketsToMap(String stringToSplit) {
        if (stringToSplit != null) {
            Map<ItemType, Map<Integer, String>> out = new HashMap<>();
            if (stringToSplit.contains("{") && stringToSplit.contains("}") && stringToSplit.contains("=")) {
                String[] stringWithoutBracketsTable = StringWithCommaToTable(deBracket(stringToSplit));
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
        Log.e("CustomStringParsers ", "StringWithCommaAndBracketsToMap > stringToSplit is empty");
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
            String[] values = StringWithCommaToTable(bracketContentMerged);
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

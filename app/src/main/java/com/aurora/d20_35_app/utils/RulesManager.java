package com.aurora.d20_35_app.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class RulesManager {
    public static List<rulesCategory> rulesCategoryList = new ArrayList<rulesCategory>();

    public static void loadRulesCategory() {
        Log.i("Rules category:", "loading categories...");

        //rulesCategoryList=; TODO load from db
        if (!rulesCategoryList.isEmpty()) {
            Log.i("Rules category:", "categories loaded.");
        } else {
            Log.e("Rules category:", "couldn't load categories.");
        }

    }

    @Data
    public class rulesCategory {
        private final String id;
        private final String content;

        public rulesCategory(String id, String content) {
            this.id = id;
            this.content = content;
        }
    }
}

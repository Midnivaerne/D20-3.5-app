package com.aurora.d20_35_app.utils;

import java.util.List;

public class RulesManager {
    public static List<rulesCategory> rulesCategoryList;

    public class rulesCategory {
        public final String id;
        public final String content;

        public rulesCategory(String id, String content) {
            this.id = id;
            this.content = content;
        }
    }
}

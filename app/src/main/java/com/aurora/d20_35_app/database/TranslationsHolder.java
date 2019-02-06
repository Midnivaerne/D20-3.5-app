package com.aurora.d20_35_app.database;

import com.aurora.d20_35_app.models.Translations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslationsHolder {
    private static final Map<String, String> TRANSLATIONS_NAMES_MAP = new HashMap<String, String>();

    static void loadAllTranslationsForLanguage(DatabaseHolder databaseHolder, String language) {
        TRANSLATIONS_NAMES_MAP.clear();
        String usedLanguage;
        if (language != null && !language.equals("")) {
            usedLanguage = language;
        } else {
            usedLanguage = "en";
        }

        List translations = databaseHolder.translationsDAO().getItemsForLanguage(usedLanguage);
        for (Object translation : translations) {
            TRANSLATIONS_NAMES_MAP.put(((Translations) translation).getName(), ((Translations) translation).getTrans());
        }
    }

    public static String translate(String name) {
        if (TRANSLATIONS_NAMES_MAP.containsKey(name)) {
            return TRANSLATIONS_NAMES_MAP.get(name);
        } else if (name != null) {
            return emergencyNameToTranslationConverter(name);
        }
        return null;
    }

    private static String emergencyNameToTranslationConverter(String name) {
        if (name.contains("_")) {
            return name.replaceAll("_", " ");
        }
        return name;
    }
}

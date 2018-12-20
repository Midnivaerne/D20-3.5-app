package com.aurora.d20_35_app.utilsDatabase;

import com.aurora.d20_35_app.models.Translations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TranslationsHolder {
    public static final Map<String, String> TRANSLATIONS_NAMES_MAP = new HashMap<String, String>();

    public static void loadAllTranslationsForLanguage(DatabaseHolder databaseHolder, String language) {
        TRANSLATIONS_NAMES_MAP.clear();
        String usedLanguage;
        if (language != null && !language.equals("")) {
            usedLanguage = language;
        } else {
            usedLanguage = "en";
        }

        List trans = databaseHolder.translationsDAO().getItemsForLanguage(usedLanguage);
        Iterator iterator = trans.iterator();
        while (iterator.hasNext()) {
            TRANSLATIONS_NAMES_MAP.put(((Translations) iterator.next()).getName(), ((Translations) iterator.next()).getTrans());
        }
    }

    public static String translate(String name) {
        if (TRANSLATIONS_NAMES_MAP.containsKey(name)) {
            return TRANSLATIONS_NAMES_MAP.get(name);
        } else {
            return emergencyNameToTranslationConverter(name);
        }
    }

    private static String emergencyNameToTranslationConverter(String name) {
        if (name.contains("_")) {
            return name.replaceAll("_", " ");
        }
        return name;
    }
}

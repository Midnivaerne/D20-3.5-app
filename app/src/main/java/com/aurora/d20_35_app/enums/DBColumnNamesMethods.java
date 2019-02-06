package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.models.helpers.CoreHelper;

public interface DBColumnNamesMethods<E extends Enum<E>, CH extends CoreHelper> {

    String getColumnName();

    static <E extends Enum<E> & DBColumnNamesMethods<E, CH>, CH extends CoreHelper> E fromString(String name, Class<E> type) {
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

    static <E extends Enum<E> & DBColumnNamesMethods<E, CH>, CH extends CoreHelper> boolean contains(String name, Class<E> type) {
        for (E item : type.getEnumConstants()) {
            if (item.toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    void setParameter(CH item, String data);

    Boolean getColumnIsUsed();
    void setColumnIsUsed(Boolean columnIsUsed);
}

package com.aurora.d20_35_app.enums;

public enum DataLocation {
    INTERNAL("Internal"),
    EXTERNAL("External");

    private String DataLocationValue;

    /**
     * @param dataLocationValue
     */
    DataLocation(String dataLocationValue) {
        this.DataLocationValue = dataLocationValue;
    }

    @Override
    public String toString() {
        return this.DataLocationValue;
    }

    /**
     * @param dataLocationValue
     * @return
     */
    public static DataLocation getEnum(String dataLocationValue) {
        for (DataLocation b : DataLocation.values()) {
            if (b.DataLocationValue.equalsIgnoreCase(dataLocationValue)) {
                return b;
            }
        }
        return null;
    }
}

package com.aurora.d20_35_app.utils;

public class Enums {


    public enum DataLocation {
        Internal("Internal"),
        External("External");

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

    public enum DatabaseHandlers {
        userDB("userDB_handler"),
        rulesDB("rulesDB_handler");

        private String DataHandlerName;

        /**
         * @param DataHandlerName
         */
        DatabaseHandlers(String DataHandlerName) {
            this.DataHandlerName = DataHandlerName;
        }

        @Override
        public String toString() {
            return this.DataHandlerName;
        }

        /**
         * @param DataHandlerName
         * @return
         */
        public static DataLocation getEnum(String DataHandlerName) {
            for (DataLocation b : DataLocation.values()) {
                if (b.DataLocationValue.equalsIgnoreCase(DataHandlerName)) {
                    return b;
                }
            }
            return null;
        }
    }
}

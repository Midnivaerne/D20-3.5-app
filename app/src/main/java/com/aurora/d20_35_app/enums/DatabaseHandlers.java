package com.aurora.d20_35_app.enums;

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
        public static DatabaseHandlers getEnum(String DataHandlerName) {
            for (DatabaseHandlers b : DatabaseHandlers.values()) {
                if (b.DataHandlerName.equalsIgnoreCase(DataHandlerName)) {
                    return b;
                }
            }
            return null;
        }
    }

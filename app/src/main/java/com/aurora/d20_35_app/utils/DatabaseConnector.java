package com.aurora.d20_35_app.utils;

import com.aurora.d20_35_app.activities.MainActivity;

import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

import static com.aurora.d20_35_app.utils.DatabaseManager.externalPathSeparator;
import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class DatabaseConnector {

    private static boolean external = true;
    /**
     * conn
     */
    private static Connection conn;

    /**
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * @param selectedDB
     */
    public static void chooseConnection(String selectedDB) {
        try {
            try {
                if (external) {
                    String externalPath = getPublicExternalStorageBaseDir();
                    conn = DriverManager.getConnection(externalPath + externalPathSeparator + selectedDB + ".db");
                } else {
                    conn = DriverManager.getConnection(selectedDB + ".db");//TODO probably wrong internal db path
                }

            } catch (SQLException e) {
                ErrorController.ErrorControllerMethod(e);
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            ErrorController.ErrorControllerMethod(ex);
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

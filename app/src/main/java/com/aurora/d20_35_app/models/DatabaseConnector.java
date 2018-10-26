package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.utils.ErrorController;
import com.aurora.d20_35_app.views.D2035appActivity;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

import lombok.Getter;

import static com.aurora.d20_35_app.utils.PermissionHandler.getPublicExternalStorageBaseDir;

public class DatabaseConnector {

    private static boolean external = true;
    /**
     * conn
     */
    @Getter
    private static Connection conn;

    /**
     * @param selectedDB
     */
    public static void chooseConnection(String selectedDB) {
        try {
            try {
                if (external) {
                    String externalPath = getPublicExternalStorageBaseDir();
                    conn = DriverManager.getConnection(externalPath + DatabaseManager.externalPathSeparator + selectedDB + ".db");
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
            Logger.getLogger(D2035appActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

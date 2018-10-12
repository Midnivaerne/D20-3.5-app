package com.aurora.d20_35_app.utils;

import android.renderscript.RenderScript;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorController {
    /**
     * @param ex
     */
    public static void ErrorControllerMethod(Exception ex) {
        /**
         * TODO Designed for something else, need to refactor
         *
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Exception Details");
        //alert.setContentText("Could not find file blabla.txt!");
        //Exception ex = new FileNotFoundException("Could not find file blabla.txt");

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label("The exception stacktrace was:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, RenderScript.Priority.ALWAYS);
        GridPane.setHgrow(textArea, RenderScript.Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
         **/
    }
}

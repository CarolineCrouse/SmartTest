/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/**
 *
 * @author spong
 */
public class DisplayStudents{
    public static Scene setScene(Test t){
        VBox box = new VBox();
        
        ScrollPane pane = new ScrollPane();
        for(int i = 0; i < t.students.size(); i++){
            Separator separator = new Separator();
            box.getChildren().add(separator);

            Separator separator1 = new Separator();
            box.getChildren().add(separator1);
                       
            Label studentsLabel = new Label(t.students.get(i).LastName);
            box.getChildren().add(studentsLabel);
            
        }
        Button displayStudent = new Button("View Detailed Results"); 
        
        displayStudent.setOnAction((ActionEvent event) ->{
            
        });

        
        pane.setContent(box);
        pane.setFitToWidth(true);

        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());

        return scene;
    }
}

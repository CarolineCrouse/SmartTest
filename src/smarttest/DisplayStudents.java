/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author spong
 */
public class DisplayStudents{
    public static Scene setScene(Test t){
        VBox box = new VBox();
        
        ScrollPane pane = new ScrollPane();
        //Goes through the list of students
        for(int i = 0; i < t.students.size(); i++){
            Separator separator = new Separator();
            box.getChildren().add(separator);

            Separator separator1 = new Separator();
            box.getChildren().add(separator1);
                      
            //Displays the names of the students
            Label studentsLabel = new Label(t.students.get(i).LastName);
            box.getChildren().add(studentsLabel);
            
        //Creates a button to view the results 
        Button displayStudent = new Button("View Detailed Results");
        box.getChildren().add(displayStudent);
        
        String uname = t.students.get(i).Username;
        String password = t.students.get(i).Password;
        
        //This is where the action for the button starts
        displayStudent.setOnAction((ActionEvent event) ->{
            String url = "http://10.22.13.87/SmartTestDB.php";
                String datastr = "op=getUser&uname=" + uname + "&password=" + password;

                String response = "";
                try {
                    response = Utils.httpsPost(url, datastr);
                } catch (Exception ex) {
                    Logger.getLogger(DisplayStudents.class.getName()).log(Level.SEVERE, null, ex);
                }

                response = response.substring(0, response.length() - 1);
                Student tempStudent = (Student) Utils.toObj(response);
                
                for (Test test : tempStudent.TakenTests) {
                    if (t.testID == test.testID) {
                        Scene seeTest = ViewTestStudent.setScene(test);

                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("View Test");
                        primaryStage.setScene(seeTest);
                        primaryStage.showAndWait();
                    }
                }
            
        });
        }
        //Sets up and displays the scene
        pane.setContent(box);
        pane.setFitToWidth(true);

        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());

        return scene;
    }
}

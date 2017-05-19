/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author spong
 */
public class LoginPage {
    public static Scene setScene()
    {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,25,10,25)); 
        
        
        Label unameLabel = new Label("Username");
        grid.add(unameLabel, 0, 0, 3, 1);
        
        TextField usernameText = new TextField();
        grid.add(usernameText,3,0,3,1);
        
        Label passLabel = new Label("Password");
        grid.add(passLabel,0,1,3,1);
        
        TextField passText = new TextField();
        grid.add(passText,3,1,3,1);
        
        //Buttons
        Button submitButton = new Button("Submit");
        grid.add(submitButton,0,2,3,1);

        Button cancelButton = new Button("Cancel");
        grid.add(submitButton,(1, 2, 3, 1);
        
        //Submits the login info for the user to enter
        submitButton.setOnAction((ActionEvent event) -> {
            String uname = usernameText.getText();
            String password = passText.getText();
            
            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=getUser&uname="+uname+"&password="+password;
            
            Scene tempScene = (Scene)submitButton.getScene();
            
            try {
                System.out.println("here");
                String response = Utils.httpsPost(url, datastr);
                response = response.substring(0, response.length()-1);
                if (response.equalsIgnoreCase("admin")){
                    tempScene = AdminHome.setScene();
                }
                else {
                    System.out.println(response);
                    User tempUser = (User)Utils.toObj(response);
                    
                    if (tempUser.AccountType.equalsIgnoreCase("student")){
                        tempScene = StudentHome.setScene((Student)tempUser);
                    }
                    else if(tempUser.AccountType.equalsIgnoreCase("teacher")){
                        tempScene = TeacherHome.setScene((Teacher)tempUser);
                    }
                }
                
            } catch (Exception ex) {
                System.out.println("Exception caught in login: " + ex);
            }
            
            //Exits out of the program if the user clicks cancel
            cancelButton.setOnAction((ActionEvent event) -> {
            String url = "http://10.22.13.87/SmartTestDB.php";
            //String datastr = "op=getUser&uname="+uname+"&password="+password;
            
            Scene tempScene = (Scene)cancelButton.getScene();
            
            System.exit(0);
            
            /*try {
                System.out.println("here");
                String response = Utils.httpsPost(url, datastr);
                response = response.substring(0, response.length()-1);
                if (response.equalsIgnoreCase("admin")){
                    tempScene = AdminHome.setScene();
                }
                else {
                    System.out.println(response);
                    User tempUser = (User)Utils.toObj(response);
                    
                    if (tempUser.AccountType.equalsIgnoreCase("student")){
                        tempScene = StudentHome.setScene((Student)tempUser);
                    }
                    else if(tempUser.AccountType.equalsIgnoreCase("teacher")){
                        tempScene = TeacherHome.setScene((Teacher)tempUser);
                    }
                }
                
            } catch (Exception ex) {
                System.out.println("Exception caught in login: " + ex);
            }*/
            
            
            Stage tempStage = new Stage();
            tempStage.setScene(tempScene);
            tempStage.show();
            
            Stage s = (Stage)submitButton.getScene().getWindow();
            s.close();
        });
        
        
        //Displays the scene for use
        Scene scene = new Scene(grid, 350, 225);
        return scene;
    }
    
}

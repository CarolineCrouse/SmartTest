/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Justin Sanchez
 */

public class AdminCreateAccount {
    public static Scene setScene()
    {
        GridPane AdminGrid = new GridPane();
        AdminGrid.setAlignment(Pos.CENTER);
        AdminGrid.setHgap(10);
        AdminGrid.setVgap(10);
        AdminGrid.setPadding(new Insets(10,10,10,10)); //padding is 25 px all around
        
        //TextFields
        TextField FNameTF = new TextField();
        TextField LNameTF = new TextField();
        TextField AccountTypeTF = new TextField();
        TextField UsernameTF = new TextField();
        TextField PasswordTF = new TextField();
        AdminGrid.add(FNameTF,1,2);
        AdminGrid.add(LNameTF,1,4);
        AdminGrid.add(UsernameTF,1,6);
        AdminGrid.add(PasswordTF,1,8);
        AdminGrid.add(AccountTypeTF,1,0);
        
        //Labels
        Label FNameLabel = new Label("First Name:");
        Label LNameLabel = new Label("Last Name:");
        Label UserLabel = new Label("Username:");
        Label PassLabel = new Label("Password:");
        Label AccountLabel = new Label("Account Type:");
        AdminGrid.add(FNameLabel,0,2);
        AdminGrid.add(LNameLabel,0,4);
        AdminGrid.add(UserLabel,0,6);
        AdminGrid.add(PassLabel,0,8);
        AdminGrid.add(AccountLabel,0,0);
        
        //button
        Button createButton = new Button("Create");
        HBox cbox = new HBox(10);
        cbox.setAlignment(Pos.CENTER);
        cbox.getChildren().add(createButton);
        AdminGrid.add(cbox,1,10);
        
        //button
        Button backButton = new Button("Go Back");
        HBox bbox = new HBox(10);
        bbox.setAlignment(Pos.CENTER);
        bbox.getChildren().add(backButton);
        AdminGrid.add(bbox,0,10);
        
        //back button action
        backButton.setOnAction((ActionEvent event) -> {
            Stage tempStage = new Stage();
            Scene scene = AdminHome.setScene();
            tempStage.setScene(scene);
            tempStage.show();            
            Stage st = (Stage)backButton.getScene().getWindow();
            st.close();
        });
        
        createButton.setOnAction((ActionEvent event) -> {
            User newUser;
            //creates new user depending on input Account Type
            Alert confirmation = new Alert(AlertType.INFORMATION);
            if("Student".equals(AccountTypeTF.getText()) || "student".equals(AccountTypeTF.getText()) )
            {
                newUser = new Student(FNameTF.getText(),LNameTF.getText(),UsernameTF.getText(),PasswordTF.getText());
                confirmation.setContentText("Student account has been created");
                uploadUser(newUser);
               
            }
            else if ("Teacher".equals(AccountTypeTF.getText()) || "teacher".equals(AccountTypeTF.getText()) )
            {
                newUser = new Teacher(FNameTF.getText(),LNameTF.getText(),UsernameTF.getText(),PasswordTF.getText());
                confirmation.setContentText("Teacher account has been created");
                uploadUser(newUser);
            }
            
            else
            {
                confirmation.setContentText("\""+AccountTypeTF.getText()+"\""+" is not a valid account type"+
                        "\nAccount types are: Student, Teacher, Admin"+
                        "\nPlease try again");
            }
            Stage tempStage = new Stage();
            Scene tempScene = AdminHome.setScene();
            confirmation.setTitle(null);
            confirmation.setHeaderText(null);
            tempStage.setScene(tempScene);
            tempStage.show();
            Stage s = (Stage)createButton.getScene().getWindow();
            s.close();
            confirmation.showAndWait();
        });
        
        //Scenes
        Scene scene = new Scene(AdminGrid, 400, 300);
        return scene;
    }//Set Scene
    
    /**
     *
     * @param use
     */
    public static void uploadUser(User use)
    {
        String str = Utils.toStr(use);
        String url = "http://10.22.13.87/SmartTestDB.php";
        String datastr = "op=addUser&str="+str+"&password="+use.Password+"&uname="+use.Username;
        try{
            String response = Utils.httpsPost(url, datastr);
            System.out.println(response);
            }
        catch(Exception ex){
            System.out.println("Exception caught: "+ex);
            }   
    }//uploadUser
}

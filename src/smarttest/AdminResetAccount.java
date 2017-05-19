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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Justin Sanchez
 */
public class AdminResetAccount {
    public static Scene setScene()
    {
        //Grid
        GridPane AdminGrid = new GridPane();
        AdminGrid.setAlignment(Pos.CENTER);
        AdminGrid.setHgap(10);
        AdminGrid.setVgap(10);
        AdminGrid.setPadding(new Insets(10,25,10,25)); 
        
        //Label
        Label UserLabel = new Label("Username:");
        AdminGrid.add(UserLabel,0,0);
        
        //TextFields
        TextField UsernameTF = new TextField();
        AdminGrid.add(UsernameTF,1,0);
        
        //Button
        Button resetButton = new Button("Reset");
        HBox cbox = new HBox(10);
        cbox.setAlignment(Pos.CENTER);
        cbox.getChildren().add(resetButton);
        AdminGrid.add(cbox,1,4);
        
        //button
        Button backButton = new Button("Go Back");
        HBox bbox = new HBox(10);
        bbox.setAlignment(Pos.CENTER);
        bbox.getChildren().add(backButton);
        AdminGrid.add(bbox,0,4);
        
        //back button action
        backButton.setOnAction((ActionEvent event) -> {
            Stage tempStage = new Stage();
            Scene scene = AdminHome.setScene();
            tempStage.setScene(scene);
            tempStage.show();            
            Stage st = (Stage)backButton.getScene().getWindow();
            st.close();
        });
        
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {             
                Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                String user = UsernameTF.getText();
                //reset user call is here   
                System.out.println(user);
                resetPasword(user);
                System.out.println(user);
                //
                confirmation.setContentText(user + "'s account has been reset" );
                Stage tempStage = new Stage();
                Scene tempScene = AdminHome.setScene();
                confirmation.setTitle(null);
                confirmation.setHeaderText(null); 
                tempStage.setScene(tempScene);
                tempStage.show();               
                Stage s = (Stage)resetButton.getScene().getWindow();
                s.close();
                confirmation.showAndWait();              
            }
        });
        
        //Scenes
        Scene scene = new Scene(AdminGrid, 350, 225);
        return scene;
    }//Set Scene
    
     public static void resetPasword(String user)
    {      
        String url = "http://10.22.13.87/SmartTestDB.php";
        String datastr = "op=resetPassword&uname="+user;
        try{
            String response = Utils.httpsPost(url, datastr);
            System.out.println(response);
            }
        catch(Exception ex){
            System.out.println("Exception caught: "+ex);
            }   
    }//uploadUser
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * This is main Client Class 
 */
public class SmartTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //primaryStage.setTitle("Smart Test Login");
        
        //Scene scene = CreateTest.setScene();
        //Scene scene = AddLearningOutcomes.setScene();
        //Scene scene = AdminHome.setScene();

        Scene scene = LoginPage.setScene();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package smarttest;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jesusgaitan
 */

//Scene for Undeployed Tests in entire project
//How another class will be able to call this one to display it


public class UndeployedTests {
    public static Scene setScene(Teacher t) {
        GridPane grdp = new GridPane();
        //grdp.setAlignment(Pos.CENTER);
        grdp.setHgap(10);
        grdp.setVgap(10);
        grdp.setPadding(new Insets(10,25,10,25));
        
        
        
        //Added a label named TeacherName
        //Gets added on to the GridPane @0, 0
        
        Label TeacherName = new Label("Teacher Name: ___________");
        grdp.add(TeacherName, 0, 0);        

        
        
        //Adds in a new label named UT
        //UT standsfor undeployed tests
        //Gets added on to the GridPane at 
        
        
        Label UT = new Label("Undeployed Tests:");
        grdp.add(UT, 0, 2);   
        
        
        
        //This puts tests in array
        //In array it displays all undeployed tests that are available
        //Tests here should ONLY be undeployed
        //That is what this calls
        //For loopslists ALL undeployed tests
        //Teacher can choose which test they'd like to edit. 
        
        
        TestsArray tests = t.UndeployedTests;
        for(int i =0; i < tests.testArray.size(); i++){
            tests.testArray.get(i);
        }
        
        
        //Buttons
        //Previous page button added
        //Edit test button added
        
        Button btn1 = new Button("Previous Page");
        Button btn2 = new Button("Edit Test");
        grdp.add(btn1, 0, 10);
        grdp.add(btn2, 0, 4);
        

        //Scene
        //Generates the new scene
        //This is how another class can call this scene to display it.
        
        Scene UndeployedTests = new Scene(grdp, 550, 350);
        return UndeployedTests;
    }


    
}
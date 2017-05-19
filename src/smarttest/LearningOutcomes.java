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

//Scene for Learning Outcomes in entire project
//How another class will be able to call this one to display it

public class LearningOutcomes {
    public static Scene setScene(){
        GridPane grdp = new GridPane();
        grdp.setHgap(10);
        grdp.setVgap(10);
        grdp.setPadding(new Insets(10,25,10,25));
        
        
        
        //New label was added here
        //Label TeacherName was added on
        //Label added to location 0,0 on Gridpane
        
        
        Label TeacherName = new Label("Teacher Name: ___________");
        grdp.add(TeacherName, 0, 0);        

        Label LOG = new Label("Learning Outcome Goals:");
        grdp.add(LOG, 0, 2);   
        
        
        
        //Buttons
        //3 new buttons were added
        //Button Previous Page was added
        //Button Edit Learning Outcome was added
        //Button Upload Learning Outcome was added
        
        
        Button btn1 = new Button("Previous Page");
        Button btn2 = new Button("Edit Learning Outcome");
        Button btn3 = new Button("Upload Learning Outcome");
        grdp.add(btn1, 0, 20);
        grdp.add(btn2, 0, 4);
        grdp.add(btn3, 0, 6);
        

        //Scene
        //Generates the new scene
        //This is how another class can call this scene to display it.
        
        
        Scene LearningOutcomes = new Scene(grdp, 550, 350);
        return LearningOutcomes;
        
    }
}
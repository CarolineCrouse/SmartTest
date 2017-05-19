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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author arslanwaheed
 */
public class AddLearningOutcomes {
    public static Scene setScene(Question question, LearningOutcomesArray learningOutcomes){
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();
        
        //LearningOutcomesArray learningOutcomes = new LearningOutcomesArray();
        //learningOutcomes.ReadLearningOutcomes();
        
        int size = learningOutcomes.loArray.size();
        String prevCategory = "";
        
        ArrayList<CheckBox> checkboxes = new ArrayList<>();
        
        for(int i=0; i<size; i++){
            String category = learningOutcomes.loArray.get(i).category;
            String name = learningOutcomes.loArray.get(i).name;
            
            
            
            if(!prevCategory.equals(category)){
                prevCategory = category;
                Label categoryLabel = new Label(category);
                box.getChildren().add(categoryLabel);
                
            }
            CheckBox cb = new CheckBox(name);
            box.getChildren().add(cb);
            checkboxes.add(cb);
        }
        
        //add two separators
        Separator separator1 = new Separator();
        box.getChildren().add(separator1);
        Separator separator2 = new Separator();
        box.getChildren().add(separator2);
        
        
        Button addButton = new Button("ADD Learning Outcomes");
        box.getChildren().add(addButton);
        
        addButton.setOnAction((ActionEvent event) -> {
            //now add all learning outcomes to question
            Boolean isSelected = false;
            for(CheckBox cb : checkboxes){
                if(cb.isSelected()){
                    LearningOutcome lo = new LearningOutcome();
                    lo = learningOutcomes.getLearningOutcome(cb.getText());
                    question.learningOutcomes.add(lo);
                    isSelected = true;
                }
            }
            if(!isSelected){
                return;
            }
            
            Stage s = (Stage)addButton.getScene().getWindow();
            s.close();
        });
        
        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
}

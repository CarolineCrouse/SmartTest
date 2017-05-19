/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author arslanwaheed
 */
public class CreateQuestion {
    public static Scene setScene(Question q, int num){
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();
        

        Label addnewQuestionLabel = new Label("Add a new question");
        box.getChildren().add(addnewQuestionLabel);
        
        TextField questionPrompt = new TextField("Type question here");
        box.getChildren().add(questionPrompt);
        
        //add two separators
        Separator separator1 = new Separator();
        box.getChildren().add(separator1);
        Separator separator2 = new Separator();
        box.getChildren().add(separator2);
        
        ArrayList<TextField> options = new ArrayList<>();
        
        Label addOptionsLabel = new Label("Add Options");
        box.getChildren().add(addOptionsLabel);
        int count = 0;
        while(count < num){
            TextField option = new TextField("Type answer here");
            options.add(option);
            box.getChildren().add(option);
            count++;
        }
        
        //add two separators
        Separator separator3 = new Separator();
        box.getChildren().add(separator3);
        Separator separator4 = new Separator();
        box.getChildren().add(separator4);
        
        Label provideInfoLabel = new Label("Provide Information about this question");
        box.getChildren().add(provideInfoLabel);
        
        Label correctOptionLabel = new Label("Enter number of correct option");
        box.getChildren().add(correctOptionLabel);
        
        TextField correctOption = new TextField("1");
        box.getChildren().add(correctOption);
        
        Label pointsLabel = new Label("Enter points for this question");
        box.getChildren().add(pointsLabel);
        
        TextField pointsForQuestion = new TextField("1");
        box.getChildren().add(pointsForQuestion);

        
        Button addButton = new Button("Save");
        box.getChildren().add(addButton);
        
        addButton.setOnAction((ActionEvent event) -> {
            //now add all collected data to question
            q.question = questionPrompt.getText();
            q.points = parseInt(pointsForQuestion.getText());
            q.correctOption = parseInt(correctOption.getText());
            for(int i=0; i<num; i++){
                q.options.add(options.get(i).getText());
            }
            
            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=getLearningOutcomes";
            String response = null;
            try {
                response = Utils.httpsPost(url, datastr);
            } catch (Exception ex) {
                Logger.getLogger(CreateQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            LearningOutcomesArray learningOutcomes = new LearningOutcomesArray();
            
            if(response != null){
                response = response.substring(0, response.length() - 1);
                learningOutcomes = (LearningOutcomesArray)Utils.toObj(response);
            }
            
            Stage tempStage = new Stage();
            Scene tempScene = AddLearningOutcomes.setScene(q,learningOutcomes);
            tempStage.setScene(tempScene);
            tempStage.showAndWait();
            
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

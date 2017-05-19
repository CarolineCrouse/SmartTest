/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Gib
 */
// DOES NOT CURRENTLY WORK NEEDS FURTHER IMPLEMENTATION
public class EditTestGib{
    public static Scene setScene(int testID, Teacher t){
        try {
            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=getFromUndeployedTests&pincode="+testID;
            
            // Returns a Test object
            String response = Utils.httpsPost(url, datastr);
            if(response != null){
                response = response.substring(0, response.length()-1);
                Test test = (Test)Utils.toObj(response);
 
                ArrayList<Question> questions = test.getQuestions();
                VBox box = new VBox();
                
                Label testLabel = new Label("Test Pincode #" + test.testID);
                box.getChildren().add(testLabel);
                
                Label testPointsLabel = new Label("Total Points " + test.totalPoints);
                box.getChildren().add(testPointsLabel);
                
                TextField numberOfOptions = new TextField("Enter number of options for new question");
                box.getChildren().add(numberOfOptions);
                
                Button add = new Button("Add Question");
                box.getChildren().add(add);
                add.setOnAction((ActionEvent event) -> {
                    int num = parseInt(numberOfOptions.getText());
                    Question question = new Question();

                    Stage tempStage = new Stage();
                    Scene tempScene = CreateQuestion.setScene(question, num);
                    tempStage.setScene(tempScene);
                    tempStage.showAndWait();
                    test.addQuestion(question);

                    String str = Utils.toStr(test);
                    String url2 = "http://10.22.13.87/SmartTestDB.php";
                    String datastr2 = "op=updateTest&uname=" + t.Username +"&str=" + str;
                    String response2 = "unsuccessfull operation";
                    try {
                        response2 = Utils.httpsPost(url2, datastr2);
                    } catch (Exception ex) {
                        System.out.println("Exception caught in EditTest: " + ex);
                    }
                    System.out.println("user update status: " + response2);

                    Stage temporaryStage = new Stage();
                    Scene temporaryScene = EditTest.setScene(test, t);
                    temporaryStage.setScene(temporaryScene);
                    temporaryStage.show();

                    Stage s = (Stage) add.getScene().getWindow();
                    s.close();
                
                });   
                
                ScrollPane pane = new ScrollPane();
                for(int i = 0; i<questions.size(); i++){
                    //add a separator
                    Question question = questions.get(i);
                    Separator separator = new Separator();
                    box.getChildren().add(separator);
                    
                    Separator separator1 = new Separator();
                    box.getChildren().add(separator1);
                    
                    Label questionLabel = new Label("Question #" + (i+1));
                    box.getChildren().add(questionLabel);

                    Label points = new Label("Points for this question: " + question.getPoints());
                    box.getChildren().add(points);
                    
                    Label correctOption = new Label("Correct Option: option# " + question.correctOption);
                    box.getChildren().add(correctOption);
                    
                    Label loLabel = new Label("Learning Outcomes for question:");
                    box.getChildren().add(loLabel);
                    
                    ArrayList<LearningOutcome> outcomeArr = question.getOutcomes();
                    for(int k = 0; k<outcomeArr.size(); k++){
                        Text LO = new Text("Category: " + outcomeArr.get(k).category + ", Subcategory: " + outcomeArr.get(k).name);
                        box.getChildren().add(LO);
                    }
                    //add a separator
                    Separator separator2 = new Separator();
                    box.getChildren().add(separator2);

                    Text Q = new Text("Question: "+question.getQuestion());
                    box.getChildren().add(Q);

                    ArrayList<String> op = question.getOptions();
                    for(int j = 0; j<op.size(); j++){
                        Text OP = new Text("Option# "+ (j+1) +" "+op.get(j));
                        box.getChildren().add(OP);
                    }
                    Button remove = new Button("Remove Question");
                    box.getChildren().add(remove);
                    
                    remove.setOnAction((ActionEvent event) -> {
                        test.removeQuestion(question);
                
                        String str = Utils.toStr(test);
                        String url2 = "http://10.22.13.87/SmartTestDB.php";
                        String datastr2 = "op=updateTest&uname="+t.Username+"&str="+str;
                        String response2 = "unsuccessfull operation";
                        try {
                            response2 = Utils.httpsPost(url2, datastr2);
                        } catch (Exception ex) {
                            System.out.println("Exception caught in EditTest: "+ex);
                        }
                        System.out.println("user update status: "+response2);
                
                        Stage tempStage = new Stage();
                        Scene tempScene = EditTestGib.setScene(testID,t);
                        tempStage.setScene(tempScene);
                        tempStage.show();
                
                        Stage s = (Stage)remove.getScene().getWindow();
                        s.close();
                    });
                }
                
                pane.setContent(box);                    
                pane.setFitToWidth(true);
                
                Scene scene = new Scene(pane, 800, 600);
                scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
                
                return scene;
            }

        } catch (Exception ex) {
            System.out.println("Exception in ViewTest: "+ex);
        }
        return null;
    }
    
    
}

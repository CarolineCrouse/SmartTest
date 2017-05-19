package smarttest;

/**
 *
 * @author arslanwaheed
 */

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TeacherHome {
    
    public static Scene setScene(Teacher t){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        VBox box = new VBox();
        ScrollPane pane = new ScrollPane();
        
        //adding name label
        Label nameLabel = new Label(t.FirstName+" " + t.LastName);
        nameLabel.setId("nameLabel");
        nameLabel.autosize();
        grid.add(nameLabel, 15, 0,30,1);
        
        //adding logout button
        Button logoutButton = new Button("Logout");
        grid.add(logoutButton, 50,0);
        
        //setting up action for logout button
        logoutButton.setOnAction((ActionEvent event) -> {
            Stage tempStage = new Stage();
            Scene scene = LoginPage.setScene();
            tempStage.setScene(scene);
            tempStage.show();
            
            Stage st = (Stage)logoutButton.getScene().getWindow();
            st.close();
        });
        
        //add a separator
        Separator separator1 = new Separator();  
        grid.add(separator1,0, 1, 67,1);
        //add another separator
        Separator separator2 = new Separator();  
        grid.add(separator2,0, 2, 67,1);
        
        
        //add create test button
        Button newTest = new Button("Create Test");
        grid.addRow(4, newTest);
        
        //add learning outcomes
        Button uploadLOButton = new Button("Upload Learning Outcomes");
        grid.addRow(3, uploadLOButton);
        
        uploadLOButton.setOnAction((ActionEvent event) -> {
            Scene uploadLOScene = UploadLearningOutcomes.setScene();
            
            Stage tempStage = new Stage();
            tempStage.setTitle("Uload Learning Outcomes");
            tempStage.setScene(uploadLOScene);
            tempStage.showAndWait();
        });
        
        //setting up action for create test button
        newTest.setOnAction((ActionEvent event) -> {
            Scene makeTest = CreateTest.setScene(t);
            
            Stage tempStage = new Stage();
            tempStage.setTitle("Create Test");
            tempStage.setScene(makeTest);
            tempStage.showAndWait();
        });
        
        //add a separator
        Separator separator3 = new Separator();  
        grid.add(separator3,0, 5, 67,1);
        
        //adding Test History label
        Label historyLabel = new Label("Test History");
        historyLabel.setId("historyLabel");
        historyLabel.autosize();
        grid.add(historyLabel, 15, 6,30,1);
        box.getChildren().add(grid);
        
        //get all tests and display them
        if(t.UndeployedTests != null){
            Label undeployedTestsLabel = new Label("Undeployed Tests");
            undeployedTestsLabel.setId("historyLabel");
            box.getChildren().add(undeployedTestsLabel);
            
            TestsArray uTests = t.UndeployedTests;
            for(Test test : uTests){
                Label testLabel = new Label("Test with pincode: " + test.testID);
                Button editTestButton = new Button("Edit Test");

                editTestButton.setOnAction((ActionEvent event) -> {
                    Scene editTestScene = EditTest.setScene(test,t);

                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Edit Test");
                    primaryStage.setScene(editTestScene);
                    primaryStage.showAndWait();
                    
                    Stage tempStage = new Stage();
                    Scene tempScene = TeacherHome.setScene(t);
                    tempStage.setScene(tempScene);
                    tempStage.show();
                    
                    Stage s = (Stage)editTestButton.getScene().getWindow();
                    s.close();
                });
                box.getChildren().add(testLabel);
                box.getChildren().add(editTestButton);
            }
            //add a separator
            Separator separator4 = new Separator();  
            box.getChildren().add(separator4);
            Separator separator5 = new Separator();  
            box.getChildren().add(separator5);
        }
        if(t.DeployedTests != null){
            Label deployedTestsLabel = new Label("Deployed Tests");
            deployedTestsLabel.setId("historyLabel");
            box.getChildren().add(deployedTestsLabel);
            
            TestsArray dTests = t.DeployedTests;
            for(Test test : dTests){
                Label testLabel = new Label("Test with pincode: " + test.testID);
                Button viewTestButton = new Button("View Test");  

                viewTestButton.setOnAction((ActionEvent event) -> {
                    Scene seeTest = ViewTest.setScene(test.testID);

                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("View Test");
                    primaryStage.setScene(seeTest);
                    primaryStage.showAndWait();
                    
                    Stage tempStage = new Stage();
                    Scene tempScene = TeacherHome.setScene(t);
                    tempStage.setScene(tempScene);
                    tempStage.show();
                    
                    Stage s = (Stage)viewTestButton.getScene().getWindow();
                    s.close();

                });
                box.getChildren().add(testLabel);
                box.getChildren().add(viewTestButton);
            }
        }
        pane.setContent(box);
        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());
        
        return scene;
    }
    

}


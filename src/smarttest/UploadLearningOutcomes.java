/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author arslanwaheed
 */
public class UploadLearningOutcomes {

    public static Scene setScene() {
        VBox box = new VBox();
        ScrollPane scroll = new ScrollPane();

        Label fileAddressLabel = new Label("Enter complete file path");
        box.getChildren().add(fileAddressLabel);

        TextField filePathText = new TextField("/src/resources/learningOutcomes.xml");
        box.getChildren().add(filePathText);

        Button uploadButton = new Button("Read & Upload");
        box.getChildren().add(uploadButton);

        uploadButton.setOnAction((ActionEvent event) -> {
            LearningOutcomesArray learningOutcomes = new LearningOutcomesArray();

            learningOutcomes.ReadLearningOutcomes(filePathText.getText());

            String str = Utils.toStr(learningOutcomes);

            String url = "http://10.22.13.87/SmartTestDB.php";
            String datastr = "op=uploadLearningOutcomes&str=" + str;

            String response = "nothing";
            try {
                response = Utils.httpsPost(url, datastr);
                Stage st = (Stage) uploadButton.getScene().getWindow();
                st.close();
            } catch (Exception ex) {
                Logger.getLogger(UploadLearningOutcomes.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Upload LO response: " + response);

        });

        scroll.setContent(box);
        scroll.setFitToWidth(true);
        Scene scene = new Scene(scroll, 800, 600);
        scene.getStylesheets().add(SmartTest.class.getResource("/resources/style.css").toExternalForm());

        return scene;
    }
}

package btp400.ass2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlgorithmRunnerApp extends Application {
	/**
	 * Loading the stage 
	 * @param stage window that the fxml file is being loaded into
	 */
	@Override
    public void start(Stage stage) {
         try {
        	 FXMLLoader loader = new FXMLLoader(getClass().getResource("Window.fxml"));
        	 
        	 Parent rootContainer = loader.load();
             Scene scene = new Scene(rootContainer);
             stage.setScene(scene);
             stage.setTitle("Algorithm Runner Application");
             stage.show();
        	 
         } catch (Exception e) {
        	 e.printStackTrace();
         }
    }

    /**
     * Renders the Window
     */
    public static void main(String[] args) {
        launch();
    }
}

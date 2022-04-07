package pl.edu.pw.mini.zpoif.project.youtube.app.graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class VideoController {

    @FXML
    void returnToChannel(MouseEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("SceneChannel.fxml"));
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("YouTube - " );
    		stage.show();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void returnToMain(MouseEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("SceneMain.fxml"));
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		Scene scene = new Scene(root);
    		stage.setScene(scene);
    		stage.setTitle("YouTube");
    		stage.show();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}

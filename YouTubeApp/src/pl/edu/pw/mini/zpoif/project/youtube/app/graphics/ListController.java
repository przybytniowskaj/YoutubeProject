package pl.edu.pw.mini.zpoif.project.youtube.app.graphics;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.*;

public class ListController implements Initializable{
	
	@FXML private ListView<YouTubeChannel> channelList = new ListView<>();
	@FXML private Label waitingLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		channelList.getItems().addAll(AppWindow.getList().getList());
		channelList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				waitingLabel.setText("Wait, loading info about chosen channel");
				try {
		        	YouTubeChannel channel = channelList.getSelectionModel().getSelectedItem();
		        	
		    		FXMLLoader loader = new FXMLLoader();
		    		loader.setLocation(getClass().getResource("SceneChannel.fxml"));
		    		Parent tableView = loader.load();
		    		Scene scene = new Scene(tableView);
		    		
		    		ChannelController controller = loader.getController();
		    		controller.initData(channel);
		    		
		    		Stage stage = AppWindow.getStage();
		    		stage.setScene(scene);
		    		stage.setTitle("YouTube - " + channel.getTitle());
		    		stage.show();
		    		
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
				
			}
			
		});
	}
	
    @FXML
    void goToMain(MouseEvent event) {
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

    @FXML
    void searchChannel(MouseEvent event) {
    	System.out.println(channelList.getSelectionModel());
    }
}

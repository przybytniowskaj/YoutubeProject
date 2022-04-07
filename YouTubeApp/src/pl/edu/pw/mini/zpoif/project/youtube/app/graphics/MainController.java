package pl.edu.pw.mini.zpoif.project.youtube.app.graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.ChannelCollector;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.ChannelGenerator;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.YouTubeChannel;

public class MainController {
	public YouTubeChannel channel;
	public ChannelCollector collector;
//	AppWindow window;
	

    @FXML public TextField searchTField;
    @FXML public Label waitingLabel;
    
//    void initCollector(ChannelCollector collector) {
//    	this.collector = collector;
//    }

    @FXML
    void clickedSearch(MouseEvent event) {
//    	channel = searchTField.getText();
    	waitingLabel.setText("Wait, loading info about chosen channel");
    	try {
    		ChannelGenerator.ApiAccess api = AppWindow.getApi();
        	channel = api.createChannel(searchTField.getText());
//        	waitingLabel.setText("Wait, loading page about " + channel.getTitle());
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("SceneChannel.fxml"));
    		Parent tableView = loader.load();
    		Scene scene = new Scene(tableView);
    		
//    		if (!AppWindow.getList().contains(channel)) {
//    			AppWindow.getList().add(channel);
//    		}
    		AppWindow.getList().addChannel(channel);
//    		System.out.println(AppWindow.getList());
    		
//    		collector.addChannel(channel);
    		
    		ChannelController controller = loader.getController();
    		controller.initData(channel);
    		
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(scene);
    		stage.setTitle("YouTube - " + channel.getTitle());
    		stage.show();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    @FXML
    void goToChannelList(MouseEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("SceneChannelList.fxml"));
    		Parent tableView = loader.load();
    		Scene scene = new Scene(tableView);
    		
    		
//    		ListController controller = new ListController();
//    		controller.initList();
    		
    		
        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(scene);
    		stage.setTitle("YouTube - channel examples");
    		stage.show();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

}

package pl.edu.pw.mini.zpoif.project.youtube.app.graphics;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.Film;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.YouTubeChannel;

public class ChannelController{
	YouTubeChannel channel;
	ArrayList<Film>  films;
	
	@FXML private Label channelName;
    @FXML private ImageView mainPhoto;
    @FXML private Button returnButton;
    @FXML private Label subLabel;
    @FXML private Label dateLabel;
    @FXML private ImageView video1;
    @FXML private ImageView video2;
    @FXML private ImageView video3;
    @FXML private ImageView video4;
    @FXML private ImageView video5;
    @FXML private Label videoT1;
    @FXML private Label videoT2;
    @FXML private Label videoT3;
    @FXML private Label videoT4;
    @FXML private Label videoT5;
    @FXML private Label videoD1;
    @FXML private Label videoD2;
    @FXML private Label videoD3;
    @FXML private Label videoD4;
    @FXML private Label videoD5;
    @FXML private Label videoV1;
    @FXML private Label videoV2;
    @FXML private Label videoV3;
    @FXML private Label videoV4;
    @FXML private Label videoV5;
    @FXML private Label videoC1;
    @FXML private Label videoC2;
    @FXML private Label videoC3;
    @FXML private Label videoC4;
    @FXML private Label videoC5;
    @FXML private Label videoL1;
    @FXML private Label videoL2;
    @FXML private Label videoL3;
    @FXML private Label videoL4;
    @FXML private Label videoL5;
    @FXML private Label videoLabel;
    @FXML private Label viewLabel;
    @FXML private Label description;

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
    
    public void initData(YouTubeChannel chan) {
    	channel = chan;
    	channelName.setText(channel.getTitle());
    	dateLabel.setText(channel.getDate().getDayOfMonth() + " " + channel.getDate().getMonth() + " " + channel.getDate().getYear());
    	subLabel.setText(channel.getSub());
    	videoLabel.setText(channel.getVideoCount());
    	viewLabel.setText(channel.getTotalViews());
    	films = channel.getFilms();
    	videoT1.setText(films.get(0).getTitle());
    	videoT2.setText(films.get(1).getTitle());
    	videoT3.setText(films.get(2).getTitle());
    	videoT4.setText(films.get(3).getTitle());
    	videoT5.setText(films.get(4).getTitle());
    	mainPhoto.setImage(new Image(channel.getPhoto()));
    	video1.setImage(new Image(films.get(0).getMinature()));
    	video2.setImage(new Image(films.get(1).getMinature()));
    	video3.setImage(new Image(films.get(2).getMinature()));
    	video4.setImage(new Image(films.get(3).getMinature()));
    	video5.setImage(new Image(films.get(4).getMinature()));
//    	, 170, 120, false, false
    	videoD1.setText("Date: " + films.get(0).getDate().getDayOfMonth() + " " + films.get(0).getDate().getMonth() + " " + films.get(0).getDate().getYear());
    	videoD2.setText("Date: " + films.get(1).getDate().getDayOfMonth() + " " + films.get(1).getDate().getMonth() + " " + films.get(1).getDate().getYear());
    	videoD3.setText("Date: " + films.get(2).getDate().getDayOfMonth() + " " + films.get(2).getDate().getMonth() + " " + films.get(2).getDate().getYear());
    	videoD4.setText("Date: " + films.get(3).getDate().getDayOfMonth() + " " + films.get(3).getDate().getMonth() + " " + films.get(3).getDate().getYear());
    	videoD5.setText("Date: " + films.get(4).getDate().getDayOfMonth() + " " + films.get(4).getDate().getMonth() + " " + films.get(4).getDate().getYear());
    	videoV1.setText("Views: " + films.get(0).getViews());
    	videoV2.setText("Views: " + films.get(1).getViews());
    	videoV3.setText("Views: " + films.get(2).getViews());
    	videoV4.setText("Views: " + films.get(3).getViews());
    	videoV5.setText("Views: " + films.get(4).getViews());
    	videoL1.setText("Likes: " + films.get(0).getLikes());
    	videoL2.setText("Likes: " + films.get(1).getLikes());
    	videoL3.setText("Likes: " + films.get(2).getLikes());
    	videoL4.setText("Likes: " + films.get(3).getLikes());
    	videoL5.setText("Likes: " + films.get(4).getLikes());
    	videoC1.setText("Comments: " + films.get(0).getCom());
    	videoC2.setText("Comments: " + films.get(1).getCom());
    	videoC3.setText("Comments: " + films.get(2).getCom());
    	videoC4.setText("Comments: " + films.get(3).getCom());
    	videoC5.setText("Comments: " + films.get(4).getCom());
    	description.setText(channel.getDescription());
    }
    
//    @FXML
//    void goToVideo(MouseEvent event) {
//    	try {
//    		Parent root = FXMLLoader.load(getClass().getResource("SceneVideo.fxml"));
//        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//    		Scene scene = new Scene(root);
//    		stage.setScene(scene);
//    		stage.setTitle("YouTube - video");
//    		stage.show();
//    		
//    	}catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    }

}

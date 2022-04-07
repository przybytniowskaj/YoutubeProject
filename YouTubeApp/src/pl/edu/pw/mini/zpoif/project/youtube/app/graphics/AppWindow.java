package pl.edu.pw.mini.zpoif.project.youtube.app.graphics;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderStroke;
//import javafx.scene.layout.BorderStrokeStyle;
//import javafx.scene.layout.BorderWidths;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.*;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.ChannelGenerator.ApiAccess;


public class AppWindow extends Application {
	private static ChannelCollector channels;
	private static Stage stage;
	
	public static ChannelGenerator.ApiAccess api = new ApiAccess("AIzaSyDya1-xeGvC4myMEyTCnoYhVpM9sVbnBiM");
//	public static ChannelGenerator.ApiAccess api = new ApiAccess("AIzaSyAwaxiFV5xg_05TivWGdbG6Ybz68tMXBHs");
	
	public static ChannelCollector getList(){
		return channels;
	}
	
	public static ApiAccess getApi() {
		return api;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void addExample() {
		try {
			channels.addChannel(api.createChannel("https://www.youtube.com/c/Blowek"));
			channels.addChannel(api.createChannel("https://www.youtube.com/c/BroCodez"));
			channels.addChannel(api.createChannel("https://www.youtube.com/channel/UCLxAS02eWvfZK4icRNzWD_g"));
			channels.addChannel(api.createChannel("https://www.youtube.com/c/TheSorryGirls"));
			channels.addChannel(api.createChannel("https://www.youtube.com/channel/UC0C-w0YjGpqDXGB8IHb662A"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
//		primaryStage.setMaximized(true);
		
		try {
			FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("SceneMain.fxml"));
    		Parent tableView = loader.load();
    		Scene scene = new Scene(tableView);
    		
//    		MainController controller = loader.getController();
//    		controller.initCollector(collector);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("YouTube");
			primaryStage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		this.primaryStage = primaryStage;
//		
//		showMainPage();

	}

	public static void main(String[] args) {
		channels = new ChannelCollector();
		addExample();
		Application.launch(args);
	}
	
//	public void showMainPage() {
//		primaryStage.setTitle("YouTube - main");
//		primaryStage.setScene(getMainPage());
//		
//		primaryStage.show();
//	}
//	
//	public void showChannelPage() {
////        primaryStage.hide();
//        primaryStage.setTitle("YouTube - " + searchedChannel);
//
//		primaryStage.getScene().setRoot(new Pane());;
//        primaryStage.setScene(getChannelPage());
////        primaryStage.show();
//	}
//	
//	public Scene getChannelPage() {
//		Group group = new Group();
//		Button returnButton = new Button();
//		returnButton.setGraphic(getLogo());
//		group.getChildren().add(returnButton);
//		returnButton.setOnMouseClicked((MouseEvent event) -> {
//			primaryStage.hide();
//			showMainPage();
//		});
//		return new Scene(group,0,0);
//	}
//
//	public Scene getMainPage() {
//		ImageView view = getLogo();
//		Text text = getText("CHANNEL", 40);
//		HBox box1 = new HBox(50);
//		box1.getChildren().addAll(view, text);
//		box1.setAlignment(Pos.BOTTOM_CENTER);
//		
//		HBox box2 = getSearchGroup();
//		VBox box = new VBox(50);
//		box.getChildren().addAll(box1, box2);
//		box.setAlignment(Pos.CENTER);
//		Group group = new Group(box);
//		group.setLayoutX(300);
//		group.setLayoutY(250);
//		return new Scene(group, 0, 0);
//	}
//
//	public ImageView getLogo() {
//		Image img = new Image("file:logo.png");
//		ImageView view = new ImageView(img);
//		view.setFitHeight(400);
//		view.setFitWidth(450);
//		view.setPreserveRatio(true);
//		return view;
//	}
//
//	public Text getText(String setText, int size) {
//		Text text = new Text();
//		text.setText(setText);
//		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
//		return text;
//	}
//
//	public HBox getSearchGroup() {
//		TextField textField = new TextField();
//		textField.setPromptText("Channel name");
//		textField.setFocusTraversable(false);
//		textField.setPrefSize(800, 30);
//		textField.setBorder(new Border(
//				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//		textField.setStyle("-fx-text-box-border: white;");
//		Button searchButton = new Button("Search");
//		searchButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
//		searchButton.setPrefSize(100, 30);
//		searchButton.setStyle("-fx-background-color: white; -fx-border-color : gray");
//		HBox box = new HBox(10);
//		box.getChildren().addAll(textField, searchButton);
//		searchButton.setOnMouseClicked((MouseEvent event) -> {
//            searchedChannel = textField.getText(); 
//            textField.setText("");
//            showChannelPage();
//            
//        });
//		return box;
//	}
//

}

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Game extends Application {
	private static Scene mainScene;
	private static Parent rootNode;
	public static Scene getMainScene() {
		return mainScene;
	}
	public static Parent getRoot() {
		return rootNode;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Wordle");
			primaryStage.setMinWidth(660);
			primaryStage.setMinHeight(520);
			Parent root = FXMLLoader.load(getClass().getResource("Wordle.fxml"));
			Scene scene = new Scene(root);
			rootNode = root;
			mainScene = scene;
			root.requestFocus();
			String css = getClass().getResource("Wordle.css").toExternalForm();
			scene.getStylesheets().add(css);
			primaryStage.setScene(scene);
			primaryStage.show();
			Game.handleInput();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void handleInput() {
		InputHandler handler = InputHandler.getInputHandler();
		handler.physicalKeyBordHandler();
	}

	public static void loadDictionary() {
		Dictionary.load();
	}

	public static void main(String[] args) {
		Game.loadDictionary();
		Matcher.setWord(Dictionary.getRandomWord(5));
		launch(args);
	}
}

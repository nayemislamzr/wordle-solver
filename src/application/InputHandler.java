package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {
	private static InputHandler inputHandler;
	private Controller controller;
	private Scene scene;

	public static InputHandler getInputHandler() {
		if (inputHandler == null)
			inputHandler = new InputHandler(Game.getMainScene());
		return inputHandler;
	}

	InputHandler(Scene scene) {
		this.scene = scene;
		controller = Controller.getController();
	}

	public void physicalKeyBordHandler() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				controller.handleEvents(key.toString());
			}
		});
	}
}

package ui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Start extends Application {

	//This is our PrimaryStage (It contains everything)
	private Stage primaryStage;

	// Creating a static root to pass to the controller
	private static BorderPane root = new BorderPane();

	/**
	 * Just a root getter for the controller to use
	 */
	public static BorderPane getRoot() {
		return root;
	}


	public void start(Stage primaryStage) throws IOException {
		URL menuBarUrl = getClass().getResource("MenuBar.fxml");
		MenuBar bar = FXMLLoader.load( menuBarUrl );

		URL indexUrl = getClass().getResource("NewDeck.fxml");
		AnchorPane indexPage = FXMLLoader.load( indexUrl );

		// constructing our scene using the static root

		root.setTop(bar);
		root.setCenter(indexPage);

		Scene scene = new Scene(root, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}

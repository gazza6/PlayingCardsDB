package ui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class IndexController{

	Stage prevStage;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	//Shows the employee operations view inside the root layout.
	public void showNewBidderView(ActionEvent actionEvent) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("Index.fxml"));
		
		Scene scene = new Scene(myPane);
		prevStage.setScene(scene);

		prevStage.show();
	}

}

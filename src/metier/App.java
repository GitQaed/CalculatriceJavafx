
package metier;

import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("exports")
	@Override
	public void start(Stage page) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));

		Scene scene = new Scene(root);
		Image image = new Image(getClass().getResource("/ressources/img/calculatrice.png").toString());
		scene.getStylesheets().add(getClass().getResource("/ressources/css/app.css").toString());
		page.setTitle("Calculatrice");
		page.setResizable(false);
		page.getIcons().add(image);
		page.setScene(scene);
		page.show();

	}

}

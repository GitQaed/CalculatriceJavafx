module CalculatriceV2 {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;

	opens metier to javafx.fxml;
	opens controllers to javafx.fxml;

	exports metier to javafx.graphics;
	exports controllers to javafx.fxml;

}
package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

public class CustomAlert {
	private Alert alert;
	private DialogPane dialogPane;
	
	public CustomAlert(AlertType alertType, String title, String headerText, String contentText) {
		setAlert(new Alert(alertType));
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		alert.initStyle(StageStyle.UTILITY);
		
		setDialogPane(alert.getDialogPane());
		dialogPane.getStylesheets().add("/ui/stylesheet.css");
		dialogPane.getStyleClass().add("custom-dialog");
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public DialogPane getDialogPane() {
		return dialogPane;
	}

	public void setDialogPane(DialogPane dialogPane) {
		this.dialogPane = dialogPane;
	}
}
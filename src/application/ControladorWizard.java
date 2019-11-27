package application;

import com.jfoenix.controls.JFXButton;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControladorWizard {

	@FXML
	private JFXButton btnNext, btnBefore;

	@FXML
	private Label lblIndex;

	@FXML
	private AnchorPane pane1, pane2, pane3;

	private int index;

	public void initialize() {
		index = 1;
		lblIndex.setText("1/3");
		cargarListeners();
	}

	private void cargarListeners() {
		btnNext.setOnMouseClicked(event -> {
			siguiente();
		});
		btnBefore.setOnMouseClicked(event -> {
			anterior();
		});
	}

	private void anterior() {
		switch (index) {
		case 2:
			translation(0.5, pane1, 600);
			index--;
			lblIndex.setText("1/3");
			break;
		case 3:
			translation(0.5, pane2, 600);
			index--;
			lblIndex.setText("2/3");
			break;
		}
	}

	private void siguiente() {
		switch (index) {
		case 1:
			translation(0.5, pane1, -600);
			index++;
			lblIndex.setText("2/3");
			break;
		case 2:
			translation(0.5, pane2, -600);
			index++;
			lblIndex.setText("3/3");
			break;
		case 3:
			Stage sala = (Stage) lblIndex.getScene().getWindow();
			crearLogin();
			sala.close();
			break;
		}
	}

	private void translation(double duration, Node node, double ejeY) {
		TranslateTransition tt = new TranslateTransition(Duration.seconds(duration), node);
		tt.setByY(ejeY);
		tt.play();
	}

	/**
	 * Crea la ventana de login
	 */
	private void crearLogin() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			Scene scene = new Scene(pane, 1150, 686);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

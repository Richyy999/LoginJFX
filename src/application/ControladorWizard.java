package application;


import com.jfoenix.controls.JFXButton;

import javafx.animation.ScaleTransition;
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
		btnBefore.setVisible(false);
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
			btnBefore.setVisible(false);
			break;
		case 3:
			translation(0.5, pane2, 600);
			index--;
			lblIndex.setText("2/3");
			reducirTama�o();
			break;
		}
	}

	private void reducirTama�o() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(0.3), btnNext);
		st.setFromX(0.6);
		st.setByX(0.35);
		st.play();
		st.setOnFinished(event -> {
			btnNext.setText(">");
		});
	}

	private void siguiente() {
		switch (index) {
		case 1:
			translation(0.5, pane1, -600);
			index++;
			lblIndex.setText("2/3");
			btnBefore.setVisible(true);
			break;
		case 2:
			translation(0.5, pane2, -600);
			index++;
			lblIndex.setText("3/3");
			aumentarTama�o();
			break;
		case 3:
			Stage sala = (Stage) lblIndex.getScene().getWindow();
			crearLogin();
			sala.close();
			break;
		}
	}

	private void aumentarTama�o() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(0.3), btnNext);
		st.setFromX(0.35);
		st.setByX(0.6);
		st.play();
		st.setOnFinished(event -> {
			btnNext.setText("Log In");
		});
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

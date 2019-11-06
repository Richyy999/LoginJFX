package application;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controlador {

	@FXML
	private AnchorPane ancla;

	@FXML
	private Pane panel1;
	@FXML
	private Pane panel2;
	@FXML
	private Pane panel3;
	@FXML
	private Pane panel4;

	@FXML
	private Label lblNombre;
	@FXML
	private Label lblSlogan;

	@FXML
	private ImageView img;

	@FXML
	public void cerrar() {
		System.exit(0);
	}

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

	public void initialize() {
		transiciones();
		backgroundAnimation();
	}

	private void transiciones() {
		TranslateTransition tran = new TranslateTransition(Duration.seconds(0.1), img);
		tran.setByY(700);
		tran.play();

		TranslateTransition tran2 = new TranslateTransition(Duration.seconds(0.1), lblNombre);
		tran2.setByY(700);
		tran2.play();

		TranslateTransition tran3 = new TranslateTransition(Duration.seconds(0.1), lblSlogan);
		tran3.setByY(700);
		tran3.play();

		tran.setOnFinished(event -> {
			TranslateTransition vuelta = new TranslateTransition(Duration.seconds(1), img);
			vuelta.setByY(-700);
			vuelta.play();
			vuelta.setOnFinished(event1 -> {
				TranslateTransition vuelta2 = new TranslateTransition(Duration.seconds(1), lblNombre);
				vuelta2.setByY(-700);
				vuelta2.play();
				vuelta2.setOnFinished(event2 -> {
					TranslateTransition vuelta3 = new TranslateTransition(Duration.seconds(1), lblSlogan);
					vuelta3.setByY(-700);
					vuelta3.play();
					vuelta3.setOnFinished(event3 -> {
						RotateTransition rt = new RotateTransition(Duration.seconds(1), ancla);
						rt.setByAngle(360);
						rt.setCycleCount(1);
						rt.play();
						rt.setOnFinished(event4 -> {
							Stage sala = (Stage) lblNombre.getScene().getWindow();
							crearLogin();
							sala.close();
						});
					});
				});
			});
		});
	}

	private void backgroundAnimation() {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), panel4);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();

		fadeTransition.setOnFinished(event -> {

			FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), panel3);
			fadeTransition1.setFromValue(1);
			fadeTransition1.setToValue(0);
			fadeTransition1.play();

			fadeTransition1.setOnFinished(event1 -> {
				FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), panel2);
				fadeTransition2.setFromValue(1);
				fadeTransition2.setToValue(0);
				fadeTransition2.play();

				fadeTransition2.setOnFinished(event2 -> {

					FadeTransition fadeTransition0 = new FadeTransition(Duration.seconds(3), panel2);
					fadeTransition0.setFromValue(0);
					fadeTransition0.setToValue(1);
					fadeTransition0.play();

					fadeTransition0.setOnFinished(event3 -> {

						FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(3), panel3);

						fadeTransition11.setFromValue(0);
						fadeTransition11.setToValue(1);
						fadeTransition11.play();

						fadeTransition11.setOnFinished(event4 -> {

							FadeTransition fadeTransition22 = new FadeTransition(Duration.seconds(3), panel4);

							fadeTransition22.setFromValue(0);
							fadeTransition22.setToValue(1);
							fadeTransition22.play();

							fadeTransition22.setOnFinished(event5 -> {

								backgroundAnimation();
							});
						});
					});
				});
			});
		});
	}
}
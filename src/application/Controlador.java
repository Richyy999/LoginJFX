package application;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controlador {

	@FXML
	private AnchorPane cuadroCentro;
	@FXML
	private AnchorPane root;

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

	public void initialize() {
		transiciones();
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
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						RotateTransition rt = new RotateTransition(Duration.seconds(1), cuadroCentro);
						rt.setByAngle(360);
						rt.setCycleCount(1);
						rt.play();
						rt.setOnFinished(event4 -> {
							FadeTransition ft = new FadeTransition(Duration.millis(500), root);
							ft.setFromValue(1);
							ft.setToValue(0);
							ft.play();
							ft.setOnFinished(event5 -> {
								Stage sala = (Stage) lblNombre.getScene().getWindow();
								sala.close();
							});
						});
					});
				});
			});
		});
	}
}

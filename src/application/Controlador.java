package application;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * 
 * @author Ricardo Bordería Pi
 *
 */
public class Controlador {
	/**
	 * Paneles de Splash
	 */
	@FXML
	private AnchorPane cuadroCentro, root;

	/**
	 * Paneles del Login
	 */
	@FXML
	private Pane panel1, panel2, panel3, panel4;
	/**
	 * label del login
	 */
	@FXML
	private Label lblNombre, lblSlogan;
	/**
	 * imagen del Splash
	 */
	@FXML
	private ImageView img;

	/**
	 * botón de login
	 */
	@FXML
	private Button btnLogin;

	/**
	 * campo que recoge el username del login
	 */
	@FXML
	private JFXTextField txtNombre;

	/**
	 * campo que recoge la contraseña del login
	 */
	@FXML
	private JFXPasswordField txtPwd;

	/**
	 * Cierra la Aplicación
	 */
	@FXML
	public void cerrar() {
		System.exit(0);
	}

	/**
	 * Inicia las transiciones
	 * 
	 * @see Controlador#transiciones()
	 * @see Controlador#backgroundAnimation()
	 * 
	 */
	public void initialize() {
		transiciones();
		backgroundAnimation();
	}

	/**
	 * Transiciones del splash
	 */
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
								crearWizard();
								sala.close();
							});
						});
					});
				});
			});
		});
	}

	/**
	 * Crea la ventana del wizard
	 */
	private void crearWizard() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Wizard.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			Scene scene = new Scene(pane, 600, 600);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Animacion del background del login
	 */
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

	/**
	 * Comprueba el login
	 */
	public void logIn() {
		if (txtNombre.getText().equals("Ricardo") && txtPwd.getText().equals("1234")) {
			Stage escena = (Stage) txtNombre.getScene().getWindow();
			escena.close();
			crearHomepage();
		} else {
			txtNombre.setText("");
			txtPwd.setText("");
		}
	}

	/**
	 * Crea la HomePage
	 */
	private void crearHomepage() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
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

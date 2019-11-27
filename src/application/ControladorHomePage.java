package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ControladorHomePage {

	@FXML
	private AnchorPane aPan1;
	@FXML
	private AnchorPane aPan2;
	@FXML
	private AnchorPane aPan3;
	@FXML
	private AnchorPane aPan4;
	@FXML
	private AnchorPane opacityPane;

	@FXML
	private VBox menLateral;

	@FXML
	private ImageView img;

	private boolean mostrado;

	public void initialize() {
		mostrado = true;
		animacionHome();
		esconderMenu();

		opacityPane.setOnMouseClicked(event -> {
			esconderMenu();
		});
		cargarListeners();
	}

	/**
	 * Carga los listeners
	 */
	private void cargarListeners() {
		img.setOnMouseClicked(event -> {
			if (mostrado)
				esconderMenu();
			else
				mostrarMenu();
		});
	}

	/**
	 * Esconde el menú
	 * 
	 * @see ControladorHomePage#initialize()
	 * @see ControladorHomePage#cargarListeners()
	 */
	private void esconderMenu() {
		if (mostrado) {
			mostrado = false;
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), opacityPane);
			ft.setFromValue(0.3);
			ft.setByValue(0);
			ft.play();
			opacityPane.setVisible(false);
			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menLateral);
			tt.setByX(-200);
			tt.play();
		}
	}

	/**
	 * Muestra el menu
	 * 
	 * @see ControladorHomePage#cargarListeners()
	 */
	private void mostrarMenu() {
		if (!mostrado) {
			mostrado = true;
			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menLateral);
			tt.setByX(200);
			tt.play();

			opacityPane.setVisible(true);
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), opacityPane);
			ft.setFromValue(0);
			ft.setByValue(0.3);
			ft.play();
		}
	}

	/**
	 * Crea la animación del wallpaper de la ventana
	 * 
	 * @see ControladorHomePage#initialize()
	 */
	private void animacionHome() {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), aPan4);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();

		fadeTransition.setOnFinished(event -> {
			FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), aPan3);
			fadeTransition1.setFromValue(1);
			fadeTransition1.setToValue(0);
			fadeTransition1.play();

			fadeTransition1.setOnFinished(event1 -> {
				FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), aPan2);
				fadeTransition2.setFromValue(1);
				fadeTransition2.setToValue(0);
				fadeTransition2.play();

				fadeTransition2.setOnFinished(event2 -> {
					FadeTransition fadeTransition0 = new FadeTransition(Duration.seconds(3), aPan1);
					fadeTransition0.setFromValue(0);
					fadeTransition0.setToValue(1);
					fadeTransition0.play();

					fadeTransition0.setOnFinished(event3 -> {
						FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(3), aPan3);
						fadeTransition11.setFromValue(0);
						fadeTransition11.setToValue(1);
						fadeTransition11.play();

						fadeTransition11.setOnFinished(event4 -> {
							FadeTransition fadeTransition22 = new FadeTransition(Duration.seconds(3), aPan4);
							fadeTransition22.setFromValue(0);
							fadeTransition22.setToValue(1);
							fadeTransition22.play();

							fadeTransition22.setOnFinished(event5 -> {
								animacionHome();
							});
						});
					});
				});
			});
		});
	}
}

package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * 
 * @author Ricardo Bordería Pi
 *
 */
public class ControladorHomePage {

	/**
	 * paneles
	 */
	@FXML
	private AnchorPane aPan1, aPan2, aPan3, aPan4, opacityPane;
	/**
	 * Menú lateral
	 */
	@FXML
	private VBox menLateral;

	/**
	 * Imagen menú
	 */
	@FXML
	private ImageView img;

	/**
	 * Si el menú lateral está ostrado
	 */
	private boolean mostrado;

	/**
	 * Indica que el menú esta mostrado para después esconderlo, carga las
	 * animaciones del fondo y carga los listeners
	 * 
	 * @see ControladorHomePage#cargarListeners()
	 */
	public void initialize() {
		mostrado = true;
		animacionHome();
		esconderMenu();
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

		opacityPane.setOnMouseClicked(event -> {
			esconderMenu();
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
	 * Muestra el menú
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

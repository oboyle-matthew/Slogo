package GUI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SplashScreen extends ScreenDisplay {

	private static final int PREFSIZE = 80;
	private static final String TITLEFONT = "Verdana";
	private static final String TURTLRIMAGE = "turtle.gif";
	private static final String TITLE = "Welcome to SLOGO";
	private HBox titleBox = new HBox();
	private VBox screenBox;
	private Text SlogoTitle;
	private LanguageLoader LanLoader;
	private ImageView turtleGif;
	private Stage stage;

	public SplashScreen(int width, int height, Paint background, Stage currentStage) {
		super(width, height, background);
		stage = currentStage;
		// TODO Auto-generated constructor stub
		basicSetup();

		/*
		 * rootAdd(LanLoader); rootAdd(titleBox); rootAdd(turtleGif);
		 */
		rootAdd(screenBox);
	}

	private void basicSetup() {
		SlogoTitle = new Text(10, 20, TITLE);
		SlogoTitle.setFont(Font.font(TITLEFONT, FontPosture.ITALIC, 30));
		SlogoTitle.setFill(Color.DARKBLUE);
		titleBox = new HBox();
		titleBox.setAlignment(Pos.CENTER);
		titleBox.getChildren().add(SlogoTitle);
		titleBox.setPrefSize(PREFSIZE, PREFSIZE);

		LanLoader = new LanguageLoader(100, 100, stage);
		LanLoader.setAlignment(Pos.CENTER);

		Image image = new Image(getClass().getClassLoader().getResourceAsStream(TURTLRIMAGE));
		turtleGif = new ImageView(image);

		screenBox = new VBox();
		screenBox.getChildren().add(titleBox);
		screenBox.getChildren().add(turtleGif);
		screenBox.getChildren().add(LanLoader);
		screenBox.setSpacing(30);
		screenBox.setAlignment(Pos.CENTER);
		screenBox.setLayoutX(50);
	}



}

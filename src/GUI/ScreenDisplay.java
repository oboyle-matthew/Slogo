package GUI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.util.Duration;


public abstract class ScreenDisplay{
	
	public double FRAMES_PER_SECOND = 1;
	public double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public double SECOND_DELAY = 100.0/ FRAMES_PER_SECOND;
	private KeyFrame frame;
	private Timeline animation = new Timeline();
	private Scene myScene;
	private Group root = new Group();




	/**
	 * Constructor: Screen Display class
	 */

	public ScreenDisplay (int width, int height, Paint background) {
		init();
		myScene = new Scene(root, width, height, background);


	}



	public Scene getScene() {
		return myScene;
	}

	public abstract void step (double elapsedTime);
	
	public void init() {
		frame  = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> this.step(SECOND_DELAY));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
	
	protected void rootAdd(Node object) {
		root.getChildren().add(object);
	}
	
	protected void centerScene() {
	
	}
}


package GUI;

	import javafx.application.Application;
	import javafx.scene.Scene;
	import javafx.scene.layout.BorderPane;
	import javafx.stage.Stage;

	public class ToggleSwitchDemo extends Application{

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        BorderPane root = new BorderPane();
	        ToggleSwitch button = new ToggleSwitch();
	        root.setCenter(button);
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();

	    }
}
	


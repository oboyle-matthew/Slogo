package GUI;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;

public class TurtleFileExplorer extends TitledPane{
	
	//constructor
	public TurtleFileExplorer() {
		
		this.setStyle( "-fx-background-color: white, white, white;\n" + 
				"  -fx-background-insets: 0, 1, 2;\n" + 
				"  -fx-background-radius: 60 60 0 0, 60 60 0 0, 0 0 0 0;\n"
				);
		
		Image imageTur = new Image(getClass().getResourceAsStream("packageExpo.png"));
        this.setGraphic(
        	      HBoxBuilder.create().spacing(5).alignment(Pos.CENTER_LEFT).children(
        	           
        	        new ImageView(imageTur),
        	       

        	       new Label("Package Explorer")
        	      ).build()
        	    );
		
		
		this.setContent(createListView());
	
		this.setPrefSize(190, 546);
	}

	private Node createListView() {
		ListView<HBox> paneListView = new ListView<>();
       for (int i = 1; i < 11; i++) {
        HBox subPane = new HBox();
        Image tur = new Image(getClass().getResourceAsStream("turtleButton.png"));
        subPane.getChildren().add(new ImageView(tur));
        Label  name = new Label(" Turtle "+ i);
        name.setFont(new Font(11));
        subPane.getChildren().add(name);
        
        Label gitControl = new Label(" [GUI master]");
        gitControl.setTextFill(Color.GOLDENROD);
        gitControl.setFont(new Font(11));
        subPane.getChildren().add(gitControl);
        gitControl.setAlignment(Pos.BASELINE_CENTER);
        
        
       paneListView.getItems().add(subPane);
       
       subPane.setAlignment(Pos.BOTTOM_LEFT);
       }
        return paneListView;
    }
	
}

package GUI;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StackedPane extends Application {

    @Override
    public void start(Stage stage) {
        VBox stackedTitledPanes = createStackedTitledPanes();
        stackedTitledPanes.setPrefSize(410, 600);
        //ScrollPane scroll = makeScrollable(stackedTitledPanes);
        //scroll.setPrefSize(410, 600);

        stage.setTitle("Test Case");
        Scene scene = new Scene(stackedTitledPanes);
        stage.setScene(scene);
        
        stage.show();
        
        
    }
        private VBox createStackedTitledPanes() {
            final VBox stackedTitledPanes = new VBox();
            final ListView<TitledPane> listView = new ListView<>();
            for (int i = 0; i < 10; i++) {
                final TitledPane newPane = new TitledPane();
                newPane.setContent(createSubPane());
                newPane.setText("Pane " + i);
                Image imageTur = new Image(getClass().getResourceAsStream("turtleButton.png"));
                newPane.setGraphic(
                	      HBoxBuilder.create().spacing(2).alignment(Pos.CENTER).children(
                	           
                	        new ImageView(imageTur),
                	       

                	       new Label("ERROR")
                	      ).build()
                	    );
                
                
                listView.getItems().add(newPane);
                newPane.setExpanded(false);
                //newPane.lookup(".arrow").setVisible(false);
            }
            stackedTitledPanes.getChildren().add(listView);
            VBox.setVgrow(listView, Priority.ALWAYS);
            return stackedTitledPanes;
        }

        private Node createSubPane() {
            final ListView<TitledPane> paneListView = new ListView<>();
            for (int i = 0; i < 20; i++) {
                final TitledPane subPane = new TitledPane();
                subPane.setText("SubPane " + i+1);

                final ListView<String> listView = new ListView<>();
                for (int j = 0; j < 20; j++) {
                    listView.getItems().add("Item " + j);
                }
                subPane.setContent(listView);
                subPane.setExpanded(false);
                paneListView.getItems().add(subPane);
            }
            return paneListView;
        }

       
        public static void main(String[] args) {
            Application.launch(args);
        }
    }
    

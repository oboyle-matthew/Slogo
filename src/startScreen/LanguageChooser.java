package startScreen;

import GUI.GUIDelegate;
import javafx.scene.control.ComboBox;

public class LanguageChooser extends ComboBox<String> {
	
//	private GUIDelegate app;
	
	public LanguageChooser(GUIDelegate app) {
//		this.app = app;
		this.setWidth(150);
		this.setHeight(30);
		this.setLayoutY(400);
	}

	public String readText() {
		return this.getValue();
	}
	
}

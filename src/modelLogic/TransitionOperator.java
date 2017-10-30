package modelLogic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class TransitionOperator {
	
	private static final double ROTATION_SPEED = 0.2 * 1000; 
	private static final double MOVEMENT_SPEED = 0.1 * 1000;
	private static final double FADE_SPEED = 0.3 * 1000;

	private List<Transition> waitingTransitions;
	private boolean transitionRunning;
	
	TransitionOperator() {
		waitingTransitions = new ArrayList<>();
		transitionRunning = false; 
	}
	
	private void addAndPlay(Transition a) {
		if(!transitionRunning && waitingTransitions.size() == 0) {
			transitionRunning = true;	
			a.play();
		} else {
			waitingTransitions.add(a); 
		}
	}
	
	private void transitionFinished() {
		transitionRunning = false;
		if(waitingTransitions.size() != 0) {
			Transition next = waitingTransitions.remove(0); 
			transitionRunning = true; 
			next.play();
		}
	}
	
	public void createFadeOut(Node obj) {
		FadeTransition ft = new FadeTransition(Duration.millis(FADE_SPEED));
		ft.setCycleCount(1);
		ft.setNode(obj);
		ft.setDuration(Duration.millis(FADE_SPEED));
		ft.setToValue(0);
		ft.setOnFinished(e -> transitionFinished());
		addAndPlay(ft);
	}
	
	public void createFadeIn(Node obj) {
		FadeTransition ft = new FadeTransition(Duration.millis(FADE_SPEED));
		ft.setCycleCount(1);
		ft.setNode(obj);
		ft.setDuration(Duration.millis(FADE_SPEED));
		ft.setToValue(1);
		ft.setOnFinished(e -> transitionFinished());
		addAndPlay(ft);
	}
	
	
	public void createRotation(Node obj, double angle) {
		RotateTransition rt = new RotateTransition();
		rt.setByAngle(angle);
		rt.setCycleCount(1);
		rt.setNode(obj);
		rt.setDuration(Duration.millis(ROTATION_SPEED));
		rt.setOnFinished(e-> transitionFinished());
		addAndPlay(rt);
	}
	
	public void createMovement(Node obj, Path movementPath, double x, double y) {
		PathTransition pt = new PathTransition();
		pt.setPath(movementPath);
		pt.setCycleCount(1);
		pt.setNode(obj);
		pt.setDuration(Duration.millis(MOVEMENT_SPEED));
		pt.setOnFinished(e-> {
			try {
				((ImageView) obj).setX(x);
				((ImageView) obj).setY(y);
				obj.setTranslateX(-20);
				obj.setTranslateY(-20);
			} catch (Exception ex) {
				System.out.println("Object is not an ImageView.");
			}
			transitionFinished(); 
		});
		addAndPlay(pt);
	}
}

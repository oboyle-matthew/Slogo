package modelLogic;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class TransitionOperator {
	
	private static final double ROTATION_SPEED = 0.8 * 1000; 
	private static final double MOVEMENT_SPEED = 0.8 * 1000;
	
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
	
	private void transitionFinished(ImageView obj) {
		transitionRunning = false;
		if(waitingTransitions.size() != 0) {
			Transition next = waitingTransitions.remove(0); 
			transitionRunning = true; 
			next.play();
		}
	}
	
	public void createRotation(ImageView obj, double angle) {
		RotateTransition rt = new RotateTransition(Duration.millis(ROTATION_SPEED));
		rt.setByAngle(angle);
		rt.setCycleCount(1);
		rt.setNode(obj);
		rt.setDuration(Duration.millis(ROTATION_SPEED));
		rt.setOnFinished(e-> {			
			transitionFinished(obj); 
		});
		addAndPlay(rt);
	}
	
	public void createMovement(ImageView obj, Path movementPath, double x, double y) {
		PathTransition pt = new PathTransition();
		pt.setPath(movementPath);
		pt.setCycleCount(1);
		pt.setNode(obj);
		pt.setDuration(Duration.millis(MOVEMENT_SPEED));
		pt.setOnFinished(e-> {
			obj.setX(x);
			obj.setY(y);
			obj.setTranslateX(-20);
			obj.setTranslateY(-20);
			transitionFinished(obj); 
		});
		addAndPlay(pt);
	}
}

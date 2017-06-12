package com.rawad.tetris.client.gui;

import com.rawad.jfxengine.gui.GuiLoader;
import com.rawad.jfxengine.gui.Hideable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * @author Rawad
 *
 */
public class CountDownScreen extends VBox implements Hideable {
	
	@FXML private Label timeLeftLabel;
	
	private Timeline timeline = new Timeline();
	
	/** Default delay of 3 seconds. */
	private int delay = 3;
	
	private int timeLeft;
	
	public CountDownScreen() {
		super();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		
		try {
			fxmlLoader.load(GuiLoader.streamLayoutFile(CountDownScreen.class));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * @see com.rawad.jfxengine.gui.Hideable#show()
	 */
	@Override
	public void show() {
		Hideable.super.show();
		
		timeLeft = delay;
		
		Platform.runLater(() -> setTimeLeft());
		
		timeline.setCycleCount(delay);
		timeline.getKeyFrames().clear();
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (e) -> {// Each KeyFrame occurs for 1 second.
			timeLeft -= 1;// Waits 1 second then runs code.
			setTimeLeft();
		}));
		timeline.setOnFinished((e) -> CountDownScreen.this.hide());
		
		timeline.playFromStart();
		
	}
	
	/**
	 * @see com.rawad.jfxengine.gui.Hideable#hide()
	 */
	@Override
	public void hide() {
		
		timeline.stop();
		
		Hideable.super.hide();
		
	}
	
	/**
	 * @param delay the delay to set
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	private void setTimeLeft() {
		timeLeftLabel.setText(Integer.toString(timeLeft));
	}
	
}

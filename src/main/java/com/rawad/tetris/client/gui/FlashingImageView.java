package com.rawad.tetris.client.gui;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * @author Rawad
 *
 */
public class FlashingImageView extends ImageView {
	
	private static final Duration FLASH_DURATION = Duration.seconds(0.5d);
	
	private static final double FROM_SCALE = 1.0d;
	private static final double TO_SCALE = 1.2d;
	
	private ParallelTransition flashTransition = new ParallelTransition(this);
	
	private FadeTransition fadeTransition = new FadeTransition(FLASH_DURATION);
	private ScaleTransition scaleTransition = new ScaleTransition(FLASH_DURATION);
	
	private Image initialImage;
	private Image finalImage;
	
	/**
	 * 
	 */
	public FlashingImageView() {
		super();
		
		flashTransition.setCycleCount(4);
		flashTransition.setAutoReverse(true);
		flashTransition.setOnFinished((e) -> FlashingImageView.this.setImage(FlashingImageView.this.initialImage));
		
		fadeTransition.setFromValue(0.4);
		fadeTransition.setToValue(1);
		
		scaleTransition.setFromX(FROM_SCALE);
		scaleTransition.setFromY(FROM_SCALE);
		scaleTransition.setToX(TO_SCALE);
		scaleTransition.setToY(TO_SCALE);
		
		flashTransition.getChildren().addAll(fadeTransition, scaleTransition);
		
	}
	
	public void setImages(Image initialImage, Image finalImage) {
		
		this.initialImage = initialImage;
		this.finalImage = finalImage;
		
		this.setImage(initialImage);
		
	}
	
	public void flashImage() {
		
		Platform.runLater(() -> {
			
			FlashingImageView.this.setImage(finalImage);
			
			flashTransition.playFromStart();
			
		});
		
	}
	
}

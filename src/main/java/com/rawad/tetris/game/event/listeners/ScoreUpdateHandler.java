package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.FallingEvent;
import com.rawad.tetris.game.event.HardDropEvent;
import com.rawad.tetris.game.event.LevelIncreaseEvent;
import com.rawad.tetris.game.event.LineClearEvent;
import com.rawad.tetris.game.event.TSpinEvent;
import com.rawad.tetris.util.ScoreCalculator;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * @author Rawad
 *
 */
public class ScoreUpdateHandler implements Listener {
	
	/** Line clears required before the level increases. */
	private static final int LINE_CLEARS_TO_INCREASE_LEVEL = 10;
	
	private final EventManager eventManager;
	private final GameModel gameModel;
	
	private final SimpleIntegerProperty score = new SimpleIntegerProperty(0);
	private final SimpleIntegerProperty level = new SimpleIntegerProperty(1);
	private final SimpleIntegerProperty lines = new SimpleIntegerProperty(0);
	
	/**
	 * @Param eventManager
	 * @param gameModel
	 */
	public ScoreUpdateHandler(EventManager eventManager, GameModel gameModel) {
		super();
		
		this.eventManager = eventManager;
		this.gameModel = gameModel;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		int pointsToAdd = 0;
		
		if(ev.getEventType() == EventType.LINE_CLEAR) {
			
			LineClearEvent lineClearEvent = (LineClearEvent) ev;
			
			pointsToAdd = ScoreCalculator.getLineClearPoints(lineClearEvent.getFullRows().length, 
					gameModel.getLineClearStreak(), level.get());
			
			// Second part of && won't get evaluated if first part is false (i.e. ensures no mod by zero excpetion).
			final boolean levelUp = lines.get() != 0 && lines.get() % LINE_CLEARS_TO_INCREASE_LEVEL == 0;
			
			if(levelUp) eventManager.submitEvent(new LevelIncreaseEvent());
			
			Platform.runLater(() -> {
				
				lines.set(lines.get() + lineClearEvent.getFullRows().length);
				
				if(levelUp) level.set(level.get() + 1);
				
			});
			
		} else if(ev.getEventType() == EventType.TETROMINO_FALL) {
			
			FallingEvent fallingEvent = (FallingEvent) ev;
			
			if(gameModel.isSoftDrop()) {
				pointsToAdd = ScoreCalculator.getSoftDropPoints(fallingEvent.getDistance());
			}
			
		} else if(ev.getEventType() == EventType.TETROMINO_HARD_DROP) {
			
			HardDropEvent hardDropEvent = (HardDropEvent) ev;
			
			pointsToAdd = ScoreCalculator.getHardDropPoints(hardDropEvent.getDistance());
			
		} else if(ev.getEventType() == EventType.T_SPIN) {
			
			TSpinEvent tSpinEvent = (TSpinEvent) ev;
			
			pointsToAdd = ScoreCalculator.getTSpinPoints(tSpinEvent.getLinesCleared(), gameModel.getLineClearStreak(), 
					level.get());
			
		}
		
		final int newPoints = score.get() + pointsToAdd;// Don't use score.add() method, makes a new object.
		
		Platform.runLater(() -> {
			score.set(newPoints);
		});
		
	}
	
	/**
	 * @return the score
	 */
	public SimpleIntegerProperty getScore() {
		return score;
	}
	
	/**
	 * @return the level
	 */
	public SimpleIntegerProperty getLevel() {
		return level;
	}
	
	/**
	 * @return the lines
	 */
	public SimpleIntegerProperty getLines() {
		return lines;
	}
	
}

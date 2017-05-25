package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.game.GameEngine;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.entity.MovementComponent;
import com.rawad.tetris.entity.PlacementComponent;
import com.rawad.tetris.entity.RotationComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.entity.movement.Movement;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.GameOverEvent;
import com.rawad.tetris.game.event.GenerateTetrominoesEvent;
import com.rawad.tetris.game.event.HardDropEvent;
import com.rawad.tetris.game.event.PlaceEvent;
import com.rawad.tetris.game.event.PlaceRequestEvent;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.geometry.Rectangle;
import com.rawad.tetris.util.BoundingBoxCalculator;
import com.rawad.tetris.util.RotationCalculator;
import com.rawad.tetris.util.TetrominoGenerator;
import com.rawad.tetris.util.TetrominoPlacementPredictor;


/**
 * @author Rawad
 *
 */
public class TetrominoHandler implements Listener {
	
	private final GameEngine gameEngine;
	private final GameModel gameModel;
	
	private final EventManager eventManager;
	
	private TetrominoGenerator tetrominoGenerator = new TetrominoGenerator();
	
	public TetrominoHandler(GameEngine gameEngine, GameModel gameModel) {
		super();
		
		this.gameEngine = gameEngine;
		this.gameModel = gameModel;
		
		this.eventManager = gameEngine.getEventManager();
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev.getEventType() == EventType.GENERATE_TETROMINOES) {
			
			GenerateTetrominoesEvent generateTetrominoesEvent = (GenerateTetrominoesEvent) ev;
			
			for(int i = 0; i < generateTetrominoesEvent.getAmountToGenerate(); i++) {
				generateTetromino();
			}
			
			setActive();
			
		} else if(ev.getEventType() == EventType.TETROMINO_PLACE_REQUEST) {
			
			// Get it here before it's replaced but after we know it was placed. Could get after if but need for next line.
			Entity activeTetromino = gameModel.getActiveTetromino();
			
			// This is for pur aesthetics, so tetromino doesn't continue to flash after game is lost.
			activeTetromino.getComponent(PlacementComponent.class).setOnGround(false);
			
			if(!placeTetrominoOnBoard()) return;
			
			generateTetromino();
			
			setActive();
			
			// Player can perform hold again once the active tetromino is placed.
			gameModel.setHold(true);
			
			eventManager.submitEvent(new PlaceEvent(activeTetromino));
			
		} else if(ev.getEventType() == EventType.TETROMINO_HOLD) {
			
			if(!gameModel.canHold()) return;
			
			Entity activeTetromino = gameModel.getActiveTetromino();
			
			RotationComponent rotationComp = activeTetromino.getComponent(RotationComponent.class);
			
			TranslateComponent translateComponent = activeTetromino.getComponent(TranslateComponent.class);
			translateComponent.setX(0);
			translateComponent.setY(0);
			
			// Undo any rotations applied to this tetromino.
			RotationCalculator.rotateTiles(rotationComp, activeTetromino.getComponent(TilesComponent.class), 
					-rotationComp.getTotalRotated());
			
			Entity holdTetromino = gameModel.getHoldTetromino();
			
			// This will occur the first time a hold operation is requested.
			if(holdTetromino == null) {
				generateTetromino();// In this case, we need to generate an additional tetromino.
				setActive();// If hold tetromino is null, just get next-in-line tetromino.
			} else {
				setActive(holdTetromino);// Otherwise, set the active tetromino to the hold tetromino.
			}
			
			gameModel.setHoldTetromino(activeTetromino);
			
			gameModel.setHold(false);
			
		} else if(ev.getEventType() == EventType.TETROMINO_HARD_DROP_REQUEST) {
			
			int newY = TetrominoPlacementPredictor.getPredictedPlacementPosition(gameModel);
			
			Entity activeTetromino = gameModel.getActiveTetromino();
			
			MovementComponent movementComp = activeTetromino.getComponent(MovementComponent.class);
			TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
			
			int distance = newY - translateComp.getY();
			
			translateComp.setY(newY);
			
			movementComp.setPrevMovement(Movement.HARD_DROP);
			
			eventManager.submitEvent(new HardDropEvent(distance));
			eventManager.submitEvent(new PlaceRequestEvent());
			
		}
		
	}
	
	/**
	 * Generates a new tetromino to be added to the stack of tetrominos in line to be next.
	 * 
	 */
	private void generateTetromino() {
		
		Entity newTetromino = Entity.createEntity(tetrominoGenerator.generateTetromino());
		newTetromino.addComponent(new PlacementComponent());
		newTetromino.addComponent(new MovementComponent());
		
		gameModel.getNextTetrominoes().addLast(newTetromino);
		
	}
	
	private void setActive() {
		this.setActive(gameModel.getNextTetrominoes().pollFirst());
	}
	
	private void setActive(Entity newTetromino) {
		
		gameEngine.getEntities().remove(gameModel.getActiveTetromino());
		
		Rectangle boundingBox = BoundingBoxCalculator.getBoundingBox(newTetromino.getComponent(TilesComponent.class));
		
		TranslateComponent translateComp = newTetromino.getComponent(TranslateComponent.class);
		
		// height = -2 is a bit of a hack; centers vertically in the area that is 2 rows above game board.
		BoundingBoxCalculator.centerInArea(translateComp, boundingBox, gameModel.getWidth(), -2);
		
		gameModel.setActiveTetromino(newTetromino);
		
		gameEngine.getEntities().add(newTetromino);
		
	}
	
	/**
	 * 
	 * @return {@code true} if the active tetromino from the {@code gameModel} was placed; {@code false} otherwise.
	 */
	private boolean placeTetrominoOnBoard() {
		
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
		
		for(Tile tile: activeTetromino.getComponent(TilesComponent.class).getTiles()) {
			
			int x = tile.getX() + translateComp.getX();
			int y = tile.getY() + translateComp.getY();
			
			// A Tile of a tetromino is outside (top) of the game board. Don't set the tile or submit a place event.
			if(y < 0) {
				eventManager.submitEvent(new GameOverEvent());
				return false;
			}
			
			// Need to copy info from tile in tetromino to board. Make method in Tile class if gets more complex.
			Tile tileOnBoard = new Tile(x, y, tile.getColor());
			
			gameModel.setTile(tileOnBoard, x, y);
			
		}
		
		return true;
		
	}
	
}

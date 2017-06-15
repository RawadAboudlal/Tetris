package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.client.sound.SoundEffectsManager;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.client.sound.SoundEffect;
import com.rawad.tetris.entity.movement.Movement;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.FallingEvent;

/**
 * @author Rawad
 *
 */
public class SoundHandler implements Listener {
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev.getEventType() == EventType.LEVEL_INCREASE) {
			SoundEffectsManager.playSoundEffect(SoundEffect.LEVEL_UP);
		} else if(ev.getEventType() == EventType.TETROMINO_FALL) {
			
			FallingEvent fallingEvent = (FallingEvent) ev;
			
			if(fallingEvent.getPrevMovement() == Movement.FALL) {
//				SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_FALL);
			} else if(fallingEvent.getPrevMovement() == Movement.SOFT_DROP) {
				SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_SOFT_DROP);
			}
			
		} else if(ev.getEventType() == EventType.TETROMINO_HARD_DROP) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_HARD_DROP);
		} else if(ev.getEventType() == EventType.TETROMINO_HOLD) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_HOLD);
		} else if(ev.getEventType() == EventType.TETROMINO_PLACE) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_LOCKDOWN);
		} else if(ev.getEventType() == EventType.TETROMINO_MOVE) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_MOVE_LR);
		} else if(ev.getEventType() == EventType.FAILED_ROTATE) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_ROTATE_FAIL);
		} else if(ev.getEventType() == EventType.TETROMINO_ROTATE) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_ROTATE_LR);
		} else if(ev.getEventType() == EventType.GROUND_HIT) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_TOUCH_DOWN);
		} else if(ev.getEventType() == EventType.FAILED_MOVE) {
			SoundEffectsManager.playSoundEffect(SoundEffect.PIECE_TOUCH_LR);
		}
		
	}
	
}

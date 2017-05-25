package com.rawad.tetris.entity;

import com.rawad.gamehelpers.game.entity.Component;
import com.rawad.tetris.entity.movement.Movement;


/**
 * @author Rawad
 *
 */
public class MovementComponent extends Component {
	
	private Movement prevMovement;
	
	/**
	 * @return the prevMovement
	 */
	public Movement getPrevMovement() {
		return prevMovement;
	}
	
	/**
	 * @param prevMovement the prevMovement to set
	 */
	public void setPrevMovement(Movement prevMovement) {
		this.prevMovement = prevMovement;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		return comp;
	}
	
}

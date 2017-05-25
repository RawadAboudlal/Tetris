package com.rawad.tetris.entity;

import com.rawad.gamehelpers.game.entity.Component;


/**
 * @author Rawad
 *
 */
public class PlacementComponent extends Component {
	
	private int lockDelay = 0;
	
	private boolean onGround = false;
	
	/**
	 * @return the lockDelay
	 */
	public int getLockDelay() {
		return lockDelay;
	}
	
	/**
	 * @param lockDelay the lockDelay to set
	 */
	public void setLockDelay(int lockDelay) {
		this.lockDelay = lockDelay;
	}
	
	/**
	 * @return the onGround
	 */
	public boolean isOnGround() {
		return onGround;
	}
	
	/**
	 * @param onGround the onGround to set
	 */
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		return comp;
	}
	
}

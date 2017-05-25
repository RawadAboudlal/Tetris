package com.rawad.tetris.entity;

import com.rawad.gamehelpers.game.entity.Component;


/**
 * @author Rawad
 *
 */
public class IdComponent extends Component {
	
	private String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		
		if(comp instanceof IdComponent) {
			
			IdComponent idComp = (IdComponent) comp;
			
			idComp.setId(getId());
			
		}
		
		return comp;
		
	}
	
}

package com.rawad.tetris.loader;

import java.io.BufferedReader;

import com.rawad.gamehelpers.fileparser.xml.EntityFileParser;
import com.rawad.gamehelpers.game.entity.Blueprint;
import com.rawad.gamehelpers.game.entity.BlueprintManager;
import com.rawad.gamehelpers.resources.AbstractLoader;
import com.rawad.tetris.entity.ETetrominoes;
import com.rawad.tetris.game.tiles.Tile;

import javafx.scene.image.Image;

/**
 * @author Rawad
 *
 */
public class Loader extends AbstractLoader {
	
	private static final String FOLDER_RES = "res";
	private static final String FOLDER_ENTITY = "tetrominoes";
	private static final String FOLDER_TEXTURES = "textures";
	
	private static final String EXTENSION_ENTITY_BLUEPRINT_FILE = "xml";
	private static final String EXTENSION_TEXTURE = "png";
	
	private static final String PROTOCOL_FILE = "file:";
	
	/**
	 * 
	 */
	public Loader() {
		super(FOLDER_RES);
	}
	
	public Image loadTexture(String textureName) {
		return new Image(PROTOCOL_FILE + getFilePathFromParts(EXTENSION_TEXTURE, FOLDER_TEXTURES, textureName));
	}
	
	public String getEntityBlueprintPath(String entityName) {
		return getFilePathFromParts(EXTENSION_ENTITY_BLUEPRINT_FILE, FOLDER_ENTITY, entityName);
	}
	
	private Blueprint loadEntityBlueprint(EntityFileParser parser, String entityName, String... contextPaths) {
		
		BufferedReader reader = readFile(getEntityBlueprintPath(entityName));
		
		parser.setContextPaths(contextPaths);
		parser.parseFile(reader);
		
		Blueprint blueprint = new Blueprint(parser.getEntity());
		
		return blueprint;
		
	}
	
	public void loadEntityBlueprints(EntityFileParser parser) {
		

		final String[] contextPaths = {
				ETetrominoes.class.getPackage().getName(),
				Tile.class.getPackage().getName(),
		};
		
		for(ETetrominoes entityKey: ETetrominoes.values()) {
			
			Blueprint blueprint = this.loadEntityBlueprint(parser, entityKey.getName(), contextPaths);
			
			BlueprintManager.addBlueprint(entityKey, blueprint);
			
		}
		
	}
	
	
}

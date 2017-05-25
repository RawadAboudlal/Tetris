package com.rawad.tetris.fileparser;

import com.rawad.gamehelpers.fileparser.xml.EntityFileParser;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.entity.ETetrominoes;
import com.rawad.tetris.entity.IdComponent;
import com.rawad.tetris.entity.RenderingComponent;
import com.rawad.tetris.entity.RotationComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.loader.Loader;
import com.rawad.tetris.util.ColorUtils;

import javafx.scene.paint.Color;

/**
 * @author Rawad
 *
 */
public class TetrominoesBlueprintFileMaker {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Entity I = Entity.createEntity();
		Entity J = Entity.createEntity();
		Entity L = Entity.createEntity();
		Entity O = Entity.createEntity();
		Entity S = Entity.createEntity();
		Entity T = Entity.createEntity();
		Entity Z = Entity.createEntity();
		
		IdComponent iIdComp = new IdComponent();
		IdComponent jIdComp = new IdComponent();
		IdComponent lIdComp = new IdComponent();
		IdComponent oIdComp = new IdComponent();
		IdComponent sIdComp = new IdComponent();
		IdComponent tIdComp = new IdComponent();
		IdComponent zIdComp = new IdComponent();
		
		iIdComp.setId(ETetrominoes.I.getName());
		jIdComp.setId(ETetrominoes.J.getName());
		lIdComp.setId(ETetrominoes.L.getName());
		oIdComp.setId(ETetrominoes.O.getName());
		sIdComp.setId(ETetrominoes.S.getName());
		tIdComp.setId(ETetrominoes.T.getName());
		zIdComp.setId(ETetrominoes.Z.getName());
		
		TilesComponent iTilesComp = new TilesComponent();
		TilesComponent jTilesComp = new TilesComponent();
		TilesComponent lTilesComp = new TilesComponent();
		TilesComponent oTilesComp = new TilesComponent();
		TilesComponent sTilesComp = new TilesComponent();
		TilesComponent tTilesComp = new TilesComponent();
		TilesComponent zTilesComp = new TilesComponent();
		
		iTilesComp.setTiles(new Tile[] {new Tile(0, 0), new Tile(1, 0), new Tile(2, 0), new Tile(3, 0)});
		jTilesComp.setTiles(new Tile[] {new Tile(0, 0), new Tile(0, 1), new Tile(1, 1), new Tile(2, 1)});
		lTilesComp.setTiles(new Tile[] {new Tile(0, 1), new Tile(1, 1), new Tile(2, 1), new Tile(2, 0)});
		oTilesComp.setTiles(new Tile[] {new Tile(0, 0), new Tile(0, 1), new Tile(1, 0), new Tile(1, 1)});
		sTilesComp.setTiles(new Tile[] {new Tile(0, 1), new Tile(1, 1), new Tile(1, 0), new Tile(2, 0)});
		tTilesComp.setTiles(new Tile[] {new Tile(1, 0), new Tile(0, 1), new Tile(1, 1), new Tile(2, 1)});
		zTilesComp.setTiles(new Tile[] {new Tile(0, 0), new Tile(1, 0), new Tile(1, 1), new Tile(2, 1)});
		
		RenderingComponent iRenderingComp = new RenderingComponent();
		RenderingComponent oRenderingComp = new RenderingComponent();
		RenderingComponent jRenderingComp = new RenderingComponent();
		RenderingComponent lRenderingComp = new RenderingComponent();
		RenderingComponent sRenderingComp = new RenderingComponent();
		RenderingComponent tRenderingComp = new RenderingComponent();
		RenderingComponent zRenderingComp = new RenderingComponent();
		
		iRenderingComp.setColor(ColorUtils.toInteger(Color.CYAN));
		jRenderingComp.setColor(ColorUtils.toInteger(Color.BLUE));
		lRenderingComp.setColor(ColorUtils.toInteger(Color.ORANGE));
		oRenderingComp.setColor(ColorUtils.toInteger(Color.YELLOW));
		sRenderingComp.setColor(ColorUtils.toInteger(Color.GREEN));
		tRenderingComp.setColor(ColorUtils.toInteger(Color.PURPLE));
		zRenderingComp.setColor(ColorUtils.toInteger(Color.RED));
		
		RotationComponent iRotationComp = new RotationComponent();
		RotationComponent jRotationComp = new RotationComponent();
		RotationComponent lRotationComp = new RotationComponent();
		RotationComponent oRotationComp = new RotationComponent();
		RotationComponent sRotationComp = new RotationComponent();
		RotationComponent tRotationComp = new RotationComponent();
		RotationComponent zRotationComp = new RotationComponent();
		
		iRotationComp.setCenterX(1.5d);
		iRotationComp.setCenterY(0.5d);
		
		jRotationComp.setCenterX(1d);
		jRotationComp.setCenterY(1d);
		
		lRotationComp.setCenterX(1d);
		lRotationComp.setCenterY(1d);
		
		oRotationComp.setCenterX(0.5d);
		oRotationComp.setCenterY(0.5d);
		
		sRotationComp.setCenterX(1d);
		sRotationComp.setCenterY(1d);
		
		tRotationComp.setCenterX(1d);
		tRotationComp.setCenterY(1d);
		
		zRotationComp.setCenterX(1d);
		zRotationComp.setCenterY(1d);
		
		I.addComponent(iIdComp).addComponent(iTilesComp).addComponent(iRenderingComp).addComponent(iRotationComp).addComponent(new TranslateComponent());
		J.addComponent(jIdComp).addComponent(jTilesComp).addComponent(jRenderingComp).addComponent(jRotationComp).addComponent(new TranslateComponent());
		L.addComponent(lIdComp).addComponent(lTilesComp).addComponent(lRenderingComp).addComponent(lRotationComp).addComponent(new TranslateComponent());
		O.addComponent(oIdComp).addComponent(oTilesComp).addComponent(oRenderingComp).addComponent(oRotationComp).addComponent(new TranslateComponent());
		S.addComponent(sIdComp).addComponent(sTilesComp).addComponent(sRenderingComp).addComponent(sRotationComp).addComponent(new TranslateComponent());
		T.addComponent(tIdComp).addComponent(tTilesComp).addComponent(tRenderingComp).addComponent(tRotationComp).addComponent(new TranslateComponent());
		Z.addComponent(zIdComp).addComponent(zTilesComp).addComponent(zRenderingComp).addComponent(zRotationComp).addComponent(new TranslateComponent());
		
		Loader loader = new Loader();
		EntityFileParser entityFileParser = new EntityFileParser();
		
		final String[] contextPaths = {
				ETetrominoes.class.getPackage().getName(),
				Tile.class.getPackage().getName(),
		};
		
		entityFileParser.setContextPaths(contextPaths);
		
		entityFileParser.saveEntityBlueprint(I, loader.getEntityBlueprintPath(ETetrominoes.I.getName()));
		entityFileParser.saveEntityBlueprint(J, loader.getEntityBlueprintPath(ETetrominoes.J.getName()));
		entityFileParser.saveEntityBlueprint(L, loader.getEntityBlueprintPath(ETetrominoes.L.getName()));
		entityFileParser.saveEntityBlueprint(O, loader.getEntityBlueprintPath(ETetrominoes.O.getName()));
		entityFileParser.saveEntityBlueprint(S, loader.getEntityBlueprintPath(ETetrominoes.S.getName()));
		entityFileParser.saveEntityBlueprint(T, loader.getEntityBlueprintPath(ETetrominoes.T.getName()));
		entityFileParser.saveEntityBlueprint(Z, loader.getEntityBlueprintPath(ETetrominoes.Z.getName()));
		
	}
	
}

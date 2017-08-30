package com.ozma.sameW.woo1.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ozma.sameW.woo1.Core;
import com.ozma.sameW.woo1.character.Player;

public class MapManager {
	
	private final static String ENEMY_ID_KEY = "id";
	private final static String ENEMY_AMT_KEY = "amt";
	private final static String ENEMY_LV_KEY = "level";
	
	private Array<Body> curBodies;
	private MapData curMapData;
	
	public MapManager() {
		curBodies = new Array<Body>();
	}
	
	/**
	 * Saves and cleans stage if not first and then loads or reloads the map
	 * @param mapName
	 */
	public void loadMap(String mapName) {
//		System.out.println("Attempting to load: " + mapName);
		
		// new map to load from storage
		TiledMap newMap = new TmxMapLoader().load(mapName);
		
		// create mapData object
		curMapData = new MapData(newMap, mapName);
		
		// wipe stage
		if(Core.stage != null)
		Core.stage.dispose();
		Core.stage = new Stage(new ScreenViewport(Core.camera), Core.batch);
		Core.stage.addActor(new Player(new Vector2(100f, 100f)));

		// build walls and lights
		buildMapSolids(newMap);
		
		// set camera on map
		Core.mapRenderer = new OrthogonalTiledMapRenderer(newMap, Core.batch);

	}
	
	/**
	 * Builds the solid objects of a map such as walls and exits
	 * @param map
	 */
	private void buildMapSolids(TiledMap map) {
		curBodies.addAll(MapBodyBuilder.buildMapWalls(map, Core.world));
	}
	
}

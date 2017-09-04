package com.ozma.sameW.woo1.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ozma.sameW.woo1.Core;
import com.ozma.sameW.woo1.character.Player;
import com.ozma.sameW.woo1.map.event.MapEvent_Warp;
import com.ozma.sameW.woo1.util.BodyBuilder;

public class MapManager {
	
	private final static String ENEMY_ID_KEY = "id";
	private final static String ENEMY_AMT_KEY = "amt";
	private final static String ENEMY_LV_KEY = "level";
	
	private Array<Body> curBodies;
	public MapData curMapData;
	
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

		// build walls and lights
		buildMapSolids(newMap);
		
		// build entry and warp
		buildMapEvents(newMap);
		
		// spawn player on start location
		Vector2 spawn = curMapData.getEntrances().get(-1);
		Core.stage.addActor(new Player(new Vector2(spawn.x, spawn.y)));
		
		// set camera on map
		Core.mapRenderer = new OrthogonalTiledMapRenderer(newMap, Core.batch);

	}
	
	private void buildMapEvents(TiledMap map) {
        // get all of the map objects from boundary layer
        MapObjects objects = map.getLayers().get("event").getObjects();
        
        for(MapObject object : objects) {
            // make body and stuff
            int type = object.getProperties().get("type", Integer.class);
            switch(type) {
                case 1:
                    Rectangle r = ((RectangleMapObject) object).getRectangle();
                    Body body = BodyBuilder.makeRectBody(new Vector2(r.x + r.width/2f, r.y + r.height/2f), r.width/2f, r.height/2f, BodyType.KinematicBody);
                    int destID = object.getProperties().get("value", Integer.class);
                    Core.stage.addActor(new MapEvent_Warp(body, destID));
                    break;
                default:
                    System.out.println("Unknow event somewhere");
                    break;
            }
            
            
        }
	}
	
	/**
	 * Builds the solid objects of a map such as walls and exits
	 * @param map
	 */
	private void buildMapSolids(TiledMap map) {
		curBodies.addAll(MapBodyBuilder.buildMapWalls(map, Core.world));
	}
	
}

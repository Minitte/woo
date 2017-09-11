package com.ozma.samew.woo1.map;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MapData {

	private static final String ENTRANCE_LAYER_NAME = "entry";
	private static final String ENTRANCE_ID_PROPERTY = "id";

	private final String mapName;
	private TiledMap map;

	// collections
	private HashMap<Integer, Vector2> entrances;
	private Array<Character> characters;

	/**
	 * 
	 * @param mapName
	 *            the map name
	 */
	public MapData(TiledMap map, String mapName) {
		this.mapName = mapName;
		this.map = map;
		entrances = new HashMap<Integer, Vector2>();
		loadEntrance();
	}

	/**
	 * Loads the entrance data from map
	 */
	private void loadEntrance() {
		MapObjects objs = map.getLayers().get(ENTRANCE_LAYER_NAME).getObjects();
		for (MapObject obj : objs) {
			Rectangle rect = ((RectangleMapObject) obj).getRectangle();
			entrances.put(obj.getProperties().get(ENTRANCE_ID_PROPERTY, Integer.class), new Vector2(rect.x, rect.y));
		}
	}

	/**
	 * @return the characters
	 */
	public Array<Character> getCharacters() {
		return characters;
	}

	/**
	 * @param characters
	 *            the characters to set
	 */
	public void setCharacters(Array<Character> characters) {
		this.characters = characters;
	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @return the entrances
	 */
	public HashMap<Integer, Vector2> getEntrances() {
		return entrances;
	}

	/**
	 * @return the map
	 */
	public TiledMap getMap() {
		return map;
	}

}

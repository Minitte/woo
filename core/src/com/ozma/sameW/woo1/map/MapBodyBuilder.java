package com.ozma.samew.woo1.map;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ozma.samew.woo1.Core;
import com.ozma.samew.woo1.map.object.SolidWall;
import com.ozma.samew.woo1.map.object.ThinWall;
import com.ozma.samew.woo1.util.BodyBuilder;
import com.ozma.samew.woo1.util.Constants;

/**
 * http://gamedev.stackexchange.com/questions/66924/how-can-i-convert-a-tilemap-to-a-box2d-world
 *
 */
public class MapBodyBuilder {

	private MapBodyBuilder(){}
    
    // layer names
    static private String boundary = "block";
    static private String light = "light";
    static private String exits = "exits";
    static private String mapNameKey = "mapName";
    static private String entryIdKey = "entryId";

    /**
     * Builds all of the bodies of the map's wall
     * @param map
     * @param world
     * @return
     */
    public static Array<Body> buildMapWalls(Map map, World world) {
    	Array<Body> bodies = new Array<Body>();
    	// get all of the map objects from boundary layer
    	MapObjects objects = map.getLayers().get(boundary).getObjects();
    	
    	for(MapObject object : objects) {

    		// make body
            if (object instanceof RectangleMapObject) {
            	boolean thin = object.getProperties().get("thin", Boolean.class) != null ? true : false;
            	Rectangle r = ((RectangleMapObject) object).getRectangle();
            	Body wall = BodyBuilder.makeRectBody(new Vector2(r.x + r.width/2f, r.y + r.height/2f), r.width/2f, r.height/2f, BodyType.StaticBody);
            	bodies.add(wall);
            	if (thin) {
            	    Core.stage.addActor(new ThinWall(wall, r.height/2f/Constants.PPM));
            	} else {
            	    Core.stage.addActor(new SolidWall(wall, r.height/2f/Constants.PPM));
            	}
            } else if (object instanceof EllipseMapObject) {
            	
            	// TODO
            	
            } else {
            	continue;
            }
    	}
    	
    	return bodies;
    }
}
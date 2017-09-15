package com.ozma.samew.woo1.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.ozma.samew.woo1.Core;

public class BodyBuilder {

	private static float density = 0.1f;
	
	/**
	 * Makes a basic rect body and returns it
	 * 
	 * @param pos
	 * @param width
	 * @param height
	 * @param bodyType
	 * @return
	 */
	public static Body makeRectBody(Vector2 pos, float width, float height, BodyType bodyType) {
		// the actual body
		Body body = makeBody(pos, bodyType);

		// body shape
		PolygonShape phyShape = new PolygonShape();
		phyShape.setAsBox(width / Constants.PPM, height / Constants.PPM);

		// fixture def
		FixtureDef fixtureDef = makeFixtureDef(phyShape);

		// give body the fixture
		body.createFixture(fixtureDef);

		// don't need the shape anymore
		phyShape.dispose();

		return body;
	}
	


	/**
	 * Creates the basic body
	 * 
	 * @param pos
	 * @param bodyType
	 * @return
	 */
	private static Body makeBody(Vector2 pos, BodyType bodyType) {
		// body def
		BodyDef bDef = new BodyDef();
		bDef.type = bodyType;
		bDef.position.set(pos.x / Constants.PPM, pos.y / Constants.PPM);
		bDef.fixedRotation = false;

		// actual body
		Body body = Core.world.createBody(bDef);

		return body;
	}

	/**
	 * Creates the fixture from a shape
	 * 
	 * @param phyShape
	 * @return
	 */
	private static FixtureDef makeFixtureDef(Shape phyShape) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = phyShape;
		fixtureDef.density = density;
//		fixtureDef.friction = 1f;
		// fixtureDef.restitution = 0.1f;

		return fixtureDef;
	}
}

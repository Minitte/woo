package com.ozma.sameW.woo1.componets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Box2dComponent implements Component, Poolable {

	public Body body;
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}

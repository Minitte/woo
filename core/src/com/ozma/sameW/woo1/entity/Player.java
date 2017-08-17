package com.ozma.sameW.woo1.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ozma.sameW.woo1.componets.Box2dComponent;
import com.ozma.sameW.woo1.componets.InputComponent;
import com.ozma.sameW.woo1.componets.SpriteComponent;
import com.ozma.sameW.woo1.util.BodyBuilder;

public class Player extends Entity {
	
	public Player(Vector2 pos) {
		// sprite
		SpriteComponent sc = new SpriteComponent();
		// sc.sprite = ??? TODO
		add(sc);
		
		// box2d
		Box2dComponent bc = new Box2dComponent();
		BodyBuilder.makeRectBody(pos, sc.sprite.getWidth(), sc.sprite.getHeight(), BodyType.DynamicBody);
		add(bc);
		
		//input
		InputComponent ic = new InputComponent();
		add(ic);
	}
}

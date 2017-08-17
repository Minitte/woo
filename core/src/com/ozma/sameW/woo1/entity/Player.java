package com.ozma.sameW.woo1.entity;

import com.badlogic.ashley.core.Entity;
import com.ozma.sameW.woo1.componets.Box2dComponent;

public class Player extends Entity {
	
	public Player() {
		Box2dComponent box2d = new Box2dComponent();
		add();
	}
}

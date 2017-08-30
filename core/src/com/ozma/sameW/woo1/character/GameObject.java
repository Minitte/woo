package com.ozma.sameW.woo1.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GameObject extends Actor{
	protected Body body;
	public Sprite sprite;
	
	public abstract void processMessage(com.ozma.sameW.woo1.character.GameObject sender, int msg);
}

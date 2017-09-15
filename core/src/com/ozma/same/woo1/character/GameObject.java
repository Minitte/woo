package com.ozma.same.woo1.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GameObject extends Actor{
	public Body body;
	public Sprite sprite;
	
	public abstract void processMessage(com.ozma.samew.woo1.character.GameObject sender, int msg);

	/**
	 * teleport the game object by rebuilding body it's  at the given location
	 * Warning: Bodies can not be modified during contactListner cycle or world act cycles/
	 * @param x
	 * @param y
	 */
	public abstract void teleport(float x, float y);
	
    public Body getBody() {
        return body;
    }
}

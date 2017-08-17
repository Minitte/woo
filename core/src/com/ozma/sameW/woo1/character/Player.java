package com.ozma.sameW.woo1.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ozma.sameW.woo1.util.BodyBuilder;

public class Player extends Character {

	private float walkForce = 100f;

	// input
	private boolean keyUp, keyDown, keyLeft, keyRight;

	public Player(Vector2 pos) {
		Texture texture = new Texture(Gdx.files.local("assets/character/player.png"));
		sprite = new Sprite(texture);

		body = BodyBuilder.makeRectBody(pos, sprite.getWidth(), sprite.getWidth(), BodyType.DynamicBody);

		setTouchable(Touchable.enabled);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sprite, body.getPosition().x, body.getPosition().y);
	}

	@Override
	public void act(float delta) {
		processInput();
	}

	private void processInput() {
		if(keyUp) {
			body.applyForceToCenter(0, walkForce, true);
		}
		
		if(keyLeft) {
			body.applyForceToCenter(-walkForce, 0, true);
		}
		
		if(keyRight) {
			body.applyForceToCenter(walkForce, 0, true);
		}
	}
}

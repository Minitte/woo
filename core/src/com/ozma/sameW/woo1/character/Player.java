package com.ozma.sameW.woo1.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ozma.sameW.woo1.util.BodyBuilder;

public class Player extends Character {

	private float walkForce = 400f;
	private float spriteXOffset, spriteYOffset;
	
	// input
	private boolean keyUp, keyDown, keyLeft, keyRight;

	public Player(Vector2 pos) {
		Texture texture = new Texture(Gdx.files.internal("assets/character/player.png"));
		sprite = new Sprite(texture);
		spriteXOffset = sprite.getWidth() * 0.5f;
		spriteYOffset = sprite.getHeight() * 0.5f;
		

		body = BodyBuilder.makeRectBody(pos, sprite.getWidth(), sprite.getWidth(), BodyType.DynamicBody);
		body.setLinearDamping(1f);

		setTouchable(Touchable.enabled);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sprite, body.getPosition().x - spriteXOffset, body.getPosition().y - spriteYOffset);
	}

	@Override
	public void act(float delta) {
		checkInput();
		processInput(); 
	}
	
	private void checkInput() {
		keyUp = Gdx.input.isKeyPressed(Keys.UP);
		keyDown = Gdx.input.isKeyPressed(Keys.DOWN);
		keyLeft = Gdx.input.isKeyPressed(Keys.LEFT);
		keyRight = Gdx.input.isKeyPressed(Keys.RIGHT);
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

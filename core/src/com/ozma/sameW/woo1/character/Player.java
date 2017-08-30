package com.ozma.sameW.woo1.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ozma.sameW.woo1.Core;
import com.ozma.sameW.woo1.character.util.Message;
import com.ozma.sameW.woo1.util.BodyBuilder;
import com.ozma.sameW.woo1.util.Constants;

public class Player extends GameObject {

	private static float walkForce = 800f;
	private static float iniJumpForce = 5000f;
	private static float holdJumpForce = 4000f;
	private static int jumpTickMax = 10;
	
	private int jumpTick;
	private float spriteXOffset, spriteYOffset;
	private boolean grounded;

	// input
	private boolean keyUp, keyDown, keyLeft, keyRight;

	public Player(Vector2 pos) {
		Texture texture = new Texture(Gdx.files.internal("assets/character/player2.png"));
		sprite = new Sprite(texture);
		sprite.setOrigin(sprite.getWidth()/2f, sprite.getHeight()/2f);
		spriteXOffset = sprite.getWidth() * 0.5f;
		spriteYOffset = sprite.getHeight() * 0.5f;

		body = BodyBuilder.makeRectBody(pos, sprite.getWidth()/2f, sprite.getHeight()/2f,
				BodyType.DynamicBody);
		body.setLinearDamping(1f);
		body.setFixedRotation(true);
		body.getFixtureList().get(0).setUserData(this);

		setTouchable(Touchable.enabled);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		 batch.draw(sprite, body.getPosition().x * Constants.PPM - spriteXOffset,
		 body.getPosition().y * Constants.PPM - spriteYOffset);
	}

	@Override
	public void act(float delta) {
		checkInput();
		processInput();
		updateCamera();
	}

	private void checkInput() {
		keyUp = Gdx.input.isKeyPressed(Keys.UP);
		keyDown = Gdx.input.isKeyPressed(Keys.DOWN);
		keyLeft = Gdx.input.isKeyPressed(Keys.LEFT);
		keyRight = Gdx.input.isKeyPressed(Keys.RIGHT);
	}
	
	/**
	 * Smoothly moves the camera with this player.
	 */
	private void updateCamera() {
		float moveX = body.getPosition().x * Constants.PPM - Core.camera.position.x;
		float moveY = body.getPosition().y * Constants.PPM - Core.camera.position.y;

		if ((int) moveX != 0)
			Core.camera.position.x += moveX * 0.08;
		if ((int) moveY != 0)
			Core.camera.position.y += moveY * 0.08;
	}

	private void processInput() {
		if (keyUp && (grounded || jumpTick < jumpTickMax)) {
			grounded = false;
			jumpTick++;
			if (grounded) 
				body.applyForceToCenter(0, iniJumpForce, true);
			else
				body.applyForceToCenter(0, holdJumpForce, true);
		}

		if (keyLeft) {
			body.applyForceToCenter(-walkForce, 0, true);
		}

		if (keyRight) {
			body.applyForceToCenter(walkForce, 0, true);
		}
		
		if(keyRight || keyLeft || keyUp) {
			body.setLinearDamping(1f);
		} else {
			body.setLinearDamping(5f);
		}
		
		if (!keyUp) {
			jumpTick = 1000;
		}
	}

	@Override
	public void processMessage(GameObject sender, int msg) {
		if (msg == Message.LAND.id) {
			grounded = true;
			jumpTick = 0;
		} else if (msg == Message.TOUCH.id) {
			// TODO
		}
		
	}
}

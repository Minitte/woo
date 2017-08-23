package com.ozma.sameW.woo1.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ozma.sameW.woo1.character.util.Telegram;

public abstract class Character extends Actor implements Telegram{
	protected Body body;
	protected Sprite sprite;
}

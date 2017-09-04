package com.ozma.sameW.woo1.character;

import com.badlogic.gdx.physics.box2d.Body;
import com.ozma.sameW.woo1.character.util.Message;
import com.ozma.sameW.woo1.util.Constants;

public class SolidWall extends GameObject {
	
	private float height;
	
	public SolidWall(Body body, float height){
		this.height = height;
		this.body = body;
		body.getFixtureList().get(0).setUserData(this);
	}

	@Override
	public void processMessage(GameObject sender, int msg) {
		float bodyUpperY = body.getPosition().y + height;
		if (sender.body.getPosition().y - sender.sprite.getHeight()/2f/Constants.PPM >= bodyUpperY)
			sender.processMessage(this, Message.LAND.id);
	}

    @Override
    public void teleport(float x, float y) {
        // TODO Auto-generated method stub
        
    }

}

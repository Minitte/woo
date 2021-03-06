package com.ozma.samew.woo1.map.object;

import com.badlogic.gdx.physics.box2d.Body;
import com.ozma.samew.woo1.character.GameObject;
import com.ozma.samew.woo1.character.util.Message;
import com.ozma.samew.woo1.util.Constants;

public class ThinWall extends GameObject {

    private float height;

    public ThinWall(Body body, float height) {
        this.height = height;
        this.body = body;
        body.getFixtureList().get(0).setUserData(this);
    }
    
    public boolean onTop(GameObject obj) {
        float bodyUpperY = body.getPosition().y + height;
        if (obj.body.getPosition().y
                - obj.sprite.getHeight() / 2f / Constants.PPM >= bodyUpperY)
            return true;
        return false;
    }

    @Override
    public void processMessage(GameObject sender, int msg) {
        float bodyUpperY = body.getPosition().y + height;
        if (sender.body.getPosition().y
                - sender.sprite.getHeight() / 2f / Constants.PPM >= bodyUpperY)
            sender.processMessage(this, Message.LAND.id);
    }

    @Override
    public void teleport(float x, float y) {
        // TODO Auto-generated method stub

    }

}

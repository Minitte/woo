package com.ozma.sameW.woo1.map.event;

import com.badlogic.gdx.physics.box2d.Body;
import com.ozma.sameW.woo1.character.GameObject;

public class MapEvent_Death extends GameObject {
    
    public MapEvent_Death(Body body) {
        this.body = body;
        body.getFixtureList().get(0).setUserData(this);
    }

    @Override
    public void processMessage(GameObject sender, int msg) {
        // kill sender

    }

    @Override
    public void teleport(float x, float y) {
        // TODO Auto-generated method stub

    }

}

package com.ozma.samew.woo1.map.event;

import com.badlogic.gdx.physics.box2d.Body;
import com.ozma.samew.woo1.character.GameObject;

public class MapEvent_Goal extends GameObject {

    public MapEvent_Goal(Body body) {
        this.body = body;
    }
    
    @Override
    public void processMessage(GameObject sender, int msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void teleport(float x, float y) {
        // TODO Auto-generated method stub

    }

}

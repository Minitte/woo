package com.ozma.sameW.woo1.map.event;

import com.badlogic.gdx.physics.box2d.Body;
import com.ozma.sameW.woo1.Core;
import com.ozma.sameW.woo1.character.EffectActor;
import com.ozma.sameW.woo1.character.GameObject;
import com.ozma.sameW.woo1.character.util.Message;

public class MapEvent_Effect extends GameObject{

    private String effectName;
    
    public MapEvent_Effect(Body body, String effectName) {
        this.effectName = effectName;
        this.body = body;
        body.getFixtureList().get(0).setUserData(this);
    }
    
    @Override
    public void processMessage(GameObject sender, int msg) {
        if (msg == Message.TOUCH.id) {
            Core.stage.addActor(new EffectActor(sender.getBody().getPosition().x, sender.getBody().getPosition().y, effectName));
        }
        
    }

    @Override
    public void teleport(float x, float y) {
        // TODO Auto-generated method stub
        
    }

}

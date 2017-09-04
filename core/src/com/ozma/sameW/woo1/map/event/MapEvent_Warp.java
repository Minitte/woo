package com.ozma.sameW.woo1.map.event;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ozma.sameW.woo1.Core;
import com.ozma.sameW.woo1.character.GameObject;
import com.ozma.sameW.woo1.character.Player;

public class MapEvent_Warp extends GameObject{
    
    private int destID;
    
    /**
     * 
     * @param body
     * @param destID
     */
    public MapEvent_Warp(Body body, int destID) {
        this.body = body;
        this.destID = destID;
        body.getFixtureList().get(0).setUserData(this);
    }

    @Override
    public void processMessage(GameObject sender, int msg) {
        if (sender instanceof Player) {
            
            // move body
            Vector2 dest = Core.mapManager.curMapData.getEntrances().get(destID);
            sender.teleport(dest.x, dest.y);
            
        }
        
    }

    @Override
    public void teleport(float x, float y) {
        // TODO Auto-generated method stub
        
    }
    
}

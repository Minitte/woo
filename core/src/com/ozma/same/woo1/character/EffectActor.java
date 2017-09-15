package com.ozma.same.woo1.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EffectActor extends Actor {
    
    private static String effectDir = "assets/effect";
    
    private ParticleEffect effect;
    
    public EffectActor(float x, float y, String effectName) {
        effect = new ParticleEffect();
        effect.load(Gdx.files.local(effectName), Gdx.files.local(effectDir));
        setX(x);
        setY(y);
    }
    
    public void start() {
        effect.start();
    }
    
    public void end() {
        remove();
        effect.dispose();
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO Auto-generated method stub
        super.draw(batch, parentAlpha);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        effect.setPosition(getX(), getY());
        effect.update(delta);
        if (effect.isComplete()) {
            end();
        }
    }
}

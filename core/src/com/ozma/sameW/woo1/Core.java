package com.ozma.sameW.woo1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ozma.sameW.woo1.character.GameObject;
import com.ozma.sameW.woo1.character.util.Message;
import com.ozma.sameW.woo1.map.MapManager;

public class Core extends Game {
	
	// render
	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	
	public static TiledMap map;
	public static TiledMapRenderer mapRenderer;
	
	public static Stage stage;
	
	// box2d
	public static final float TIME_STEP = 0.01f;
	
	public static World world;
	private float accumulator = 0;
	private Box2DDebugRenderer debugRenderer;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.zoom = 0.5f;
		
		
		// box2d
		world = new World(new Vector2(0f, -50f), true);
		debugRenderer = new Box2DDebugRenderer();
		setUpContactListener();
		
		// map
		new MapManager().loadMap("assets/map/test.tmx");
		mapRenderer.setView(camera);
	}

	@Override
	public void render () {
		// act
		doPhysicsStep(Gdx.graphics.getDeltaTime());
		
		// draw
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		debugRenderer.render(world, camera.combined);
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
	
	/**
	 * physics stepping
	 * See: https://github.com/libgdx/libgdx/wiki/box2d#stepping-the-simulation
	 * See: http://gafferongames.com/game-physics/fix-your-timestep/
	 * @param deltaTime
	 */
	public void doPhysicsStep(float deltaTime) {
	    // fixed time step
	    // max frame time to avoid spiral of death (on slow devices)
	    float frameTime = Math.min(deltaTime, 0.25f);
	    accumulator += frameTime;
	    while (accumulator >= TIME_STEP) {
	        world.step(TIME_STEP, 6, 2);
	        accumulator -= TIME_STEP;
	    }
	}
	
	public void setUpContactListener(){
	    world.setContactListener(new ContactListener() {
            
            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            	GameObject a = (GameObject) contact.getFixtureA().getUserData();
                GameObject b = (GameObject) contact.getFixtureB().getUserData();
                if(a != null && b != null) {
	                a.processMessage(b, Message.TOUCH.id);
	                b.processMessage(a, Message.TOUCH.id);
                }
                
            }
            
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void endContact(Contact contact) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void beginContact(Contact contact) {
            	// TODO Auto-generated method stub
            }
        });
	}
}

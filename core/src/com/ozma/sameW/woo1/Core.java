package com.ozma.sameW.woo1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ozma.sameW.woo1.character.Player;

public class Core extends Game {
	
	// render
	private static float ppm = 2f;
	private static float mpp = 1f/ppm;
	
	private SpriteBatch batch;
	public OrthographicCamera camera;
	
	private TiledMap map;
	private TiledMapRenderer mapRenderer;
	
	private Stage stage;
	
	// box2d
	public static final float TIME_STEP = 0.01f;
	
	public static World world;
	private float accumulator = 0;
	private Box2DDebugRenderer debugRenderer;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * mpp, Gdx.graphics.getHeight() * mpp);
		
		// map
		map = new TmxMapLoader().load("assets/map/test.tmx");
		mapRenderer = new OrthoCachedTiledMapRenderer(map);
		mapRenderer.setView(camera);
		
		// box2d
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();
		setUpContactListener();
		
		// scene2d
		stage = new Stage(new ScreenViewport(camera), batch);
		stage.addActor(new Player(new Vector2(100f, 100f)));
	}

	@Override
	public void render () {
		// act
		doPhysicsStep(Gdx.graphics.getDeltaTime());
		
		// draw
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		camera.update();
		mapRenderer.render();
		debugRenderer.render(world, camera.combined);
		
		
		batch.end();
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
                // TODO Auto-generated method stub
                
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

package screens;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import planets.Planet;
import planets.Planets;
import utils.MyGestures;

public class WorldScreen implements Screen {
    OrthographicCamera cam;
    public Vector3 vec;
    Initial game;
    public enum State{
        PAUSE,
        RUN,
        RESUME,

    }
    private State state = State.RUN;

    Sprite bg;

    public Planets planets;

    //TEST

    Viewport viewport;
    int virtualWidth = 1280;
    int virtualHeight = 720;
    public WorldScreen(Initial game){
        Initial.setInputProcessor();
        this.game = game;
        cam = new OrthographicCamera();
        cam = new OrthographicCamera(cam.viewportWidth/2, cam.viewportHeight/2);

        viewport = new FillViewport(virtualHeight,virtualWidth,cam);
        bg = new Sprite(new Texture(Gdx.files.internal("space.png")));
        bg.setPosition(0,0);
        bg.setSize(virtualHeight,virtualWidth);

        vec = new Vector3();

        planets = new Planets();
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/planet1.png")),100,100,"1"));
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/planet2.png")),300,300,"2"));
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/planet3.png")),200,700,"3"));

    }
    public void input(){
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            cam.unproject(vec);
            planets.input(vec);
        }
    }

    public void update(){
        planets.update();
        if(planets.planetTouched()){
            game.prefs.putString("load_level",planets.getTouched().level);
            game.prefs.flush();
            game.setScreen(new MainGame(game));
            dispose();
        }
    }

    public void draw(SpriteBatch batch){

        bg.draw(batch);
        planets.draw(batch);
    }

    public void drawHUD(SpriteBatch batch){

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("FPS",""+Gdx.graphics.getFramesPerSecond());

        // 1)Clear the screen
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        switch(state){
            case RUN:
                // 2)Input handling
                input();
                // 3)Update system
                // 3.1)---> Update Cam
                cam.update();
                game.batch.setProjectionMatrix(cam.combined);
                // 3.2)---> Update Game
                update();
                break;
            case PAUSE:

                break;
            case RESUME:
                break;

        }
        // 4)Draw
        game.batch.begin();
        draw(game.batch);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

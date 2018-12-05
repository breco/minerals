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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import huds.menu.BasicMenu;
import huds.menu.LevelMenu;
import huds.menu.MineralMenu;
import huds.menu.SelectionMenu;
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

    public enum InputState{
        WORLD,
        MENU,
        MINERALS,
        SELECTION,
        GO,
    }
    private State state = State.RUN;
    public InputState inputState = InputState.WORLD;

    Sprite bg;

    public Planets planets;


    public static Viewport viewport;
    int virtualWidth = 1280;
    int virtualHeight = 720;


    //TEST

    BasicMenu menu;
    public LevelMenu levelmenu;
    public MineralMenu mineralmenu;
    public SelectionMenu selectionmenu;

    public WorldScreen(Initial game){
        Initial.setInputProcessor();
        this.game = game;
        cam = new OrthographicCamera();
        cam = new OrthographicCamera(cam.viewportWidth/2, cam.viewportHeight/2);

        viewport = new FitViewport(virtualHeight,virtualWidth,cam);
        bg = new Sprite(new Texture(Gdx.files.internal("space.png")));
        bg.setPosition(0,0);
        bg.setSize(virtualHeight,virtualWidth);

        vec = new Vector3();

        planets = new Planets();
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/planet1.png")),100,100, "grass planet","1"));
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/planet2.png")),300,500,"ice planet", "2"));
        planets.add(new Planet(new Texture(Gdx.files.internal("planets/planet3.png")),200,700,"rock planet","3"));

        menu = new BasicMenu(this);
        levelmenu = new LevelMenu(this);
        mineralmenu = new MineralMenu(this);
        selectionmenu = new SelectionMenu(this);

    }
    public void input(){
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            viewport.unproject(vec);
            switch(inputState){
                case WORLD:
                    planets.input(vec);
                    break;
                case MENU:
                    levelmenu.input(vec);
                    break;
                case MINERALS:
                    mineralmenu.input(vec);

                    break;
                case SELECTION:
                    selectionmenu.input(vec);

                    break;

            }


        }
        if(MyGestures.isTouchUp()){
            vec.set(MyGestures.touchUpvec);
            viewport.unproject(vec);
            switch(inputState){
                case WORLD:
                    break;
                case MENU:
                    levelmenu.touchUp(vec);
                    break;
                case MINERALS:
                    mineralmenu.touchUp(vec);
                    break;
                case SELECTION:
                    selectionmenu.touchUp(vec);
                    break;

            }
        }

        if(!MyGestures.isTouchDragged()){

            MyGestures.resetDiff(0);
        }
    }

    public void update(){
        planets.update();

        switch(inputState){
            case WORLD:
                if(planets.planetTouched()){


                    game.prefs.putString("load_planet",planets.getTouched().name);
                    game.prefs.flush();
                    levelmenu.show(planets.getTouched());

                    levelmenu.tryLoadLevel();
                    planets.setUntouched();
                    inputState = InputState.MENU;

                }
                break;
            case MENU:

                break;
            case MINERALS:

                break;
            case SELECTION:
                selectionmenu.update();
                break;
            case GO:
                game.prefs.putString("load_level",levelmenu.getPlanet().level+"-"+levelmenu.getLevel());
                game.prefs.flush();
                game.setScreen (new MainGame(game));
                dispose();
                break;
        }




    }

    public void draw(SpriteBatch batch){

        bg.draw(batch);
        planets.draw(batch);
        switch(inputState){
            case WORLD:

                break;
            case MENU:
                levelmenu.draw(batch);
                break;
            case MINERALS:
                mineralmenu.draw(batch);
                break;
            case SELECTION:
                selectionmenu.draw(batch);
                break;
        }

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
        Gdx.gl.glClearColor(0,0,0, 0);
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

package screens;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import backgrounds.Background;
import bullets.Bullets;
import enemies.Enemies;
import enemies.Enemy;
import huds.maingame.MainGameHUD;
import items.ItemLoader;
import items.Items;
import minerals.MineralLoader;
import minerals.Minerals;
import skills.Skills;
import utils.MyGestures;
import utils.TimeManager;

//import Minerals.Minerals;
//import Minerals.Aqualis;


public class MainGame implements Screen {

    //GAME STATES
    public enum State{
        PAUSE,
        RUN,
        RESUME,
        WIN,
        LOSE
    }
    private State state = State.RUN;


    public static OrthographicCamera cam;
    public Initial game;
    public static Viewport viewport;
    public Vector3 vec;


    public static Minerals minerals;
    public static Enemies enemies;
    public static Bullets bullets;
    public static Items items;
    public static Skills skills;

    //HUD

    public MainGameHUD hud;
    public float itemSection = 6.5f;
    //graphics



    //HELPERS/UTILS

    public static TimeManager time;

    //GAME STATS

    public static int CURRENT_PP = 0;
    public static int MAX_PP = 30;
    public static float PERCENT_PP;

    //TEST
    public Background bg;






    public MainGame(Initial game){

        Initial.setInputProcessor();
        this.game = game;
        cam = new OrthographicCamera();

        cam.position.set(Initial.HEIGHT/2,Initial.WIDTH/2, 0);
        cam.update();
        viewport = new FitViewport(Initial.HEIGHT, Initial.WIDTH,cam);

        viewport.apply();

        vec = new Vector3();


        // GAME OBJECTS
        minerals = new Minerals();

        //minerals.add( new Redmi(Initial.HEIGHT/4-32,400,30,2,30, 1));
        //minerals.add( new Terro(Initial.HEIGHT/2-32,400,30,2,30, 1));
        //minerals.add( new Agni(Initial.HEIGHT*3/4-32,400,30,2,30, 1));

        enemies = new Enemies();


        bullets = new Bullets();

        items = new Items();
        String[] selecteditems = game.prefs.getString("selected_items").split(",");
        for(String item : selecteditems){
            items.add(ItemLoader.getItem(item));

        }

        String[] selectedMinerals = game.prefs.getString("selected_minerals").split(",");
        for(String mineral : selectedMinerals){
            minerals.add(MineralLoader.getMineral(mineral));
        }

        minerals.getMinerals().get(0).setPosition(Initial.HEIGHT/4-32,400);
        minerals.getMinerals().get(1).setPosition(Initial.HEIGHT/2-32,400);
        minerals.getMinerals().get(2).setPosition(Initial.HEIGHT*3/4-32,400);
        //items.add(new Fruit(new Texture(Gdx.files.internal("items/grapes.png"))));
        //items.add(new Mirror(new Texture(Gdx.files.internal("items/mirror.png"))));
        //items.add(new Gemstone(new Texture(Gdx.files.internal("items/gemstone.png"))));
        items.setPosition();

        skills = new Skills(this);
        skills.setSkills();
        skills.setPosition();

        setInitialPP();


        //LOAD LEVEL
        try {
            loadLevel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        //LOAD HUD

        hud = new MainGameHUD(this);

        time = new TimeManager();
        time.start();
    }

    public static class JsonEnemy {
        public String clase;
        public int posx,posy,time;
    }

    public void loadLevel() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("levels/"+game.prefs.getString("load_level")+".json"));

        JsonValue enemigos = base.get("enemies");
        JsonEnemy t;
        int i = 0;
        while(enemigos.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemigos.get(i).toString());
            Class<?> clazz = Class.forName(t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,int.class);
            enemies.add((Enemy) ctor.newInstance(t.posx,Initial.WIDTH,t.time));
            i++;
        }



        bg = new Background(new Texture(Gdx.files.internal("backgrounds/"+base.getString("background"))));
    }

    public void setInitialPP(){
        CURRENT_PP = minerals.getInitialPP();
        updatePPbar();

    }

    public static void chargePP(int amount){

        CURRENT_PP += amount;
        if(CURRENT_PP > MAX_PP) CURRENT_PP = MAX_PP;
        updatePPbar();
    }

    public static void updatePPbar(){
        PERCENT_PP = CURRENT_PP*100f/MAX_PP;
        //Gdx.app.log("PERCENTPP",PERCENT_PP+"");

    }



    public void input(){
        if(MyGestures.isLongPress()){
            vec.set(MyGestures.firstTouch);
            viewport.unproject(vec);
            //minerals.long_input(vec);
        }
        else{
            if(MyGestures.isTouchDown()){
                vec.set(MyGestures.firstTouch);
                viewport.unproject(vec);
                if(vec.y < Initial.WIDTH/itemSection){
                    skills.input(vec,0);
                    return;
                }
                items.input(vec,0);
                minerals.input(vec, 0);
                hud.input(state, vec);

            }
            if(MyGestures.isTouchDown2()){
                vec.set(MyGestures.firstTouch2);
                viewport.unproject(vec);
                if(vec.y < Initial.WIDTH/itemSection){
                    skills.input(vec,1);
                    return;
                }
                items.input(vec,1);
                minerals.input(vec,1);
                //HUD INPUT
                hud.input(state, vec);
            }
        }

        if(!MyGestures.isTouchDragged()){

            MyGestures.resetDiff(0);
        }

        if(!MyGestures.isTouchDragged2()){
            MyGestures.resetDiff(1);
        }

        if(MyGestures.isTouchUp()){
            vec.set(MyGestures.touchUpvec);
            viewport.unproject(vec);
            hud.touchUp(state,vec);
            minerals.setUntouched(0);
            items.touchUp(0);
            items.setUntouched(0);

        }
        if(MyGestures.isTouchUp2()){
            minerals.setUntouched(1);
            items.touchUp(1);
            items.setUntouched(1);
        }
    }

    public void update(){
        gameConditions();
        bg.update();
        hud.update();
        minerals.update();
        enemies.update();
        bullets.update();
        items.update();
        skills.update();
    }

    public void draw(){
        bg.draw(game.batch);
        enemies.draw(game.batch);
        bullets.draw(game.batch);
        minerals.draw(game.batch);
        hud.draw(game.batch);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("FPS",""+Gdx.graphics.getFramesPerSecond());

        // 1)Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // 3)Update system
        // 3.1)---> Update Cam
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        switch(state){
            case RUN:
                // 2)Input handling
                input();

                // 3.2)---> Update Game
                update();
                break;
            case PAUSE:
                hudInput();
                hud.update();
                minerals.pause();
                skills.pause();
                break;
            case RESUME:
                minerals.unpause();
                skills.unpause();
                break;
            case WIN:
                hudInput();
                break;
            case LOSE:
                hudInput();
                break;

        }

        // 4)Draw
        game.batch.begin();
        draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);//,true);

    }

    @Override
    public void pause() {
        state = State.PAUSE;
        time.pause();
        minerals.pause();
        skills.pause();
    }

    @Override
    public void resume() {
        state = State.RUN;
        time.unpause();
        minerals.unpause();
        skills.unpause();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        viewport = null;

    }

    public void hudInput(){
        if(MyGestures.isTouchDown()) {
            vec.set(MyGestures.firstTouch);
            viewport.unproject(vec);
            hud.input(state, vec);
        }
        if(MyGestures.isTouchUp()){
            vec.set(MyGestures.touchUpvec);
            viewport.unproject(vec);
            hud.touchUp(state,vec);

        }
    }



    public void gameConditions(){
        if(enemies.enemies.size == enemies.deadEnemies.size + enemies.escapedEnemies.size){
            state = State.WIN;
        }
        if(minerals.getMinerals().size == 0){
            state = State.LOSE;
        }
    }
    public State getState(){
        return state;
    }
}


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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import Minerals.Minerals;
import Minerals.TestMineral;
import backgrounds.Background;
import bullets.Bullets;
import enemies.Enemies;
import enemies.Enemy;
import huds.Initial.MainGameHUD;
import items.Fruit;
import items.Items;
import items.SpeedStar;
import skills.Skills;
import utils.MyGestures;
import utils.TimeManager;

//import Minerals.Minerals;
//import Minerals.TestMineral;


public class MainGame implements Screen {



    public static OrthographicCamera cam;
    Initial game;
    Viewport viewport;
    public Vector3 vec;


    public static Minerals minerals;
    public static Enemies enemies;
    public static Bullets bullets;
    public static Items items;
    public static Skills skills;

    //HUD

    public huds.Initial.MainGameHUD hud;
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
        cam.position.set(cam.viewportHeight/2,cam.viewportWidth/2, 0);
        viewport = new FillViewport(Initial.HEIGHT, Initial.WIDTH,cam);
        viewport.apply();
        vec = new Vector3();

        //GRAPHICS
        bg = new Background(new Texture(Gdx.files.internal("backgrounds/bg3.png")));



        // GAME OBJECTS
        minerals = new Minerals();
        minerals.add( new TestMineral(Initial.HEIGHT/4,400,30,2,30, 1));
        minerals.add( new TestMineral(Initial.HEIGHT/2,400,30,2,30, 1));
        minerals.add( new TestMineral(Initial.HEIGHT*3/4,400,30,2,30, 1));

        enemies = new Enemies();


        bullets = new Bullets();

        items = new Items();
        items.add(new Fruit(new Texture(Gdx.files.internal("items/orangefruit.png"))));
        items.add(new SpeedStar(new Texture(Gdx.files.internal("items/star.png"))));
        items.add(new Fruit(new Texture(Gdx.files.internal("items/tuna.png"))));
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
        JsonValue base = reader.parse(Gdx.files.internal("levels/1.json"));

        JsonValue enemigos = base.get("enemies");
        JsonEnemy t;
        int i = 0;
        while(enemigos.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemigos.get(i).toString());
            Class<?> clazz = Class.forName(t.clase);
            Gdx.app.log("t.clase",t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,int.class);
            enemies.add((Enemy) ctor.newInstance(t.posx,Initial.WIDTH,t.time));
            i++;
        }



        //bg = new Background(new Texture(Gdx.files.internal("backgrounds/"+base.getString("background"))));
    }

    public void setInitialPP(){
        CURRENT_PP = minerals.getInitialPP();
        Gdx.app.log("INITIAL PP",CURRENT_PP+"");
        updatePPbar();
    }

    public static void chargePP(int amount){

        CURRENT_PP += amount;
        if(CURRENT_PP > MAX_PP) CURRENT_PP = MAX_PP;
        updatePPbar();
    }

    public static void updatePPbar(){
        PERCENT_PP = CURRENT_PP*100f/MAX_PP;
        Gdx.app.log("PERCENTPP",PERCENT_PP+"");

    }



    public void input(){
        if(MyGestures.isLongPress()){
            vec.set(MyGestures.firstTouch);
            cam.unproject(vec);
            //minerals.long_input(vec);
        }
        else{
            if(MyGestures.isTouchDown()){
                vec.set(MyGestures.firstTouch);
                cam.unproject(vec);

                if(vec.y < Initial.WIDTH/itemSection){
                    Gdx.app.log("ITEM","SECTION");
                    skills.input(vec,0);
                    return;
                }
                items.input(vec,0);
                minerals.input(vec, 0);
                //HUD INPUT
                vec.set(MyGestures.firstTouch);

                //hud.input(vec);

            }
            if(MyGestures.isTouchDown2()){
                vec.set(MyGestures.firstTouch2);
                cam.unproject(vec);
                if(vec.y < Initial.WIDTH/itemSection){
                    skills.input(vec,1);
                    return;
                }
                items.input(vec,1);
                minerals.input(vec,1);
                //HUD INPUT
                vec.set(MyGestures.firstTouch);
            }
        }

        if(!MyGestures.isTouchDragged()){

            MyGestures.resetDiff(0);
        }

        if(!MyGestures.isTouchDragged2()){
            MyGestures.resetDiff(1);
        }

        if(MyGestures.isTouchUp()){

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
        bg.update();
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

        // 2)Input handling
        input();

        // 3)Update system
        // 3.1)---> Update Cam

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        // 3.2)---> Update Game
        update();


        // 4)Draw
        game.batch.begin();
        draw();

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


package huds.maingame.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

import planets.Planet;
import screens.WorldScreen;


public class BasicMenu {
    public Sprite menuC, menuCD, menuCU, menuLC, menuLD, menuLU, menuRC, menuRD, menuRU;
    public Sprite menutitleC, menutitleL, menutitleR, menutitleX;

    public BasicButton backbutton;
    public BasicButton closebutton;

    public BitmapFont font;
    public boolean show = false;


    Planet planet;
    String title ="";

    private WorldScreen screen;

    public BasicMenu(WorldScreen screen){
        this.screen = screen;
        menuC = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu C.png")));
        menuCD = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu CD.png")));
        menuCU= new Sprite(new Texture(Gdx.files.internal("huds/menu/menu CU.png")));
        menuLC = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu LC.png")));
        menuLD = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu LD.png")));
        menuLU = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu LU.png")));
        menuRC = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu RC.png")));
        menuRD = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu RD.png")));
        menuRU = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu RU.png")));
        menutitleC = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu title C.png")));
        menutitleL = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu title L.png")));
        menutitleR = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu title R.png")));
        menutitleX = new Sprite(new Texture(Gdx.files.internal("huds/menu/menu title X.png")));

        menuC.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menuCD.setSize(menuC.getWidth()*5.4f,menuC.getHeight()*1.8f);
        menuCU.setSize(menuC.getWidth()*5.4f,menuC.getHeight()*1.8f);
        menuLC.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*10.8f);
        menuLD.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menuLU.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menuRC.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*10.8f);
        menuRD.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menuRU.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menutitleC.setSize(menuC.getWidth()*3.6f,menuC.getHeight()*1.8f);
        menutitleL.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menutitleR.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menutitleX.setSize(menuC.getWidth()*1.8f,menuC.getHeight()*1.8f);
        menuC.setSize(menuC.getWidth()*7.2f,menuC.getHeight()*10.8f);

        //menuLU.setPosition(Initial.HEIGHT/6,Initial.WIDTH*0.8125f);
        float baseX = Initial.HEIGHT/6;
        float baseY = Initial.WIDTH*0.8125f;
        menutitleL.setPosition(baseX, baseY);
        menutitleC.setPosition(menutitleL.getX()+menutitleL.getHeight(),menutitleL.getY());
        menutitleR.setPosition(menutitleL.getX()+menutitleL.getHeight()*3,menutitleL.getY());
        menutitleX.setPosition(menutitleL.getX()+menutitleL.getHeight()*4,menutitleL.getY());


        menuLU.setPosition(menutitleL.getX(), menutitleL.getY()-menuLU.getHeight());
        menuCU.setPosition(menutitleL.getX() + menutitleL.getWidth(),menutitleL.getY()-menuLU.getHeight());
        menuRU.setPosition(menutitleL.getX() + menutitleL.getWidth()*4, menutitleL.getY()-menuLU.getHeight());

        menuLC.setPosition(menuLU.getX(), menuLU.getY()-menuLU.getHeight()*6);
        menuC.setPosition(menuLU.getX()+menuLU.getWidth(),menuLU.getY() -menuLU.getHeight()*6);
        menuRC.setPosition(menuLU.getX() + menuC.getWidth(),menuLU.getY() -menuLU.getHeight()*6);

        menuLD.setPosition(menuLU.getX(), menuC.getY()-menuLU.getHeight());
        menuCD.setPosition(menuLU.getX()+menuLU.getWidth(), menuC.getY()-menuLU.getHeight());
        menuRD.setPosition(menuCD.getX()+menuCD.getWidth(),menuC.getY()-menuLU.getHeight());

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;

        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);

        //buttons

        Texture normal = new Texture(Gdx.files.internal("huds/menu/square button.png"));
        Texture pressed = new Texture(Gdx.files.internal("huds/menu/square button pressed.png"));
        backbutton = new BasicButton(normal,pressed, Initial.HEIGHT/2 - menuLU.getWidth()/3, menuCD.getY()+ menuCD.getHeight()/3);
        backbutton.setIcon(new Texture(Gdx.files.internal("huds/menu/back arrow.png")));
        backbutton.setSize(menuLU.getWidth(), menuLU.getHeight()*0.8f);
        normal = new Texture(Gdx.files.internal("huds/menu/menu title X.png"));
        pressed = new Texture(Gdx.files.internal("huds/menu/menu title X pressed.png"));
        closebutton = new BasicButton(normal,pressed,menutitleL.getX()+menutitleL.getHeight()*4,menutitleL.getY());
        closebutton.setSize(menuLU.getWidth(),menuLU.getHeight());

    }
    public void draw(SpriteBatch batch){
        if(!show) return;
        menutitleL.draw(batch);
        menutitleC.draw(batch);
        menutitleR.draw(batch);
        closebutton.draw(batch);

        batch.draw(planet.getTexture(), menutitleL.getX()+menutitleL.getWidth()/3, menutitleL.getY()+ menutitleL.getHeight()*0.2f, menutitleL.getWidth()*0.6f,menutitleL.getHeight()*0.6f);
        font.draw(batch, title,menutitleL.getX()+menutitleL.getWidth(), menutitleL.getY()+ menutitleL.getHeight()*0.6f);

        menuLU.draw(batch);
        menuCU.draw(batch);
        menuRU.draw(batch);

        menuLC.draw(batch);
        menuC.draw(batch);
        menuRC.draw(batch);

        menuLD.draw(batch);
        menuCD.draw(batch);
        menuRD.draw(batch);

        backbutton.draw(batch);
    }

    public void update(){
        if(!show) return;
    }
    public void input(Vector3 vec){
        if(!show) return;

        closebutton.input(vec);
        backbutton.input(vec);
    }

    public void touchUp(Vector3 vec){
        if(backbutton.contains(vec) || closebutton.contains(vec)){
            show = false;
            screen.inputState = WorldScreen.InputState.WORLD;
        }

        backbutton.touchUp();
        closebutton.touchUp();
    }

    public void show(Planet planet){

        show = true;
        this.planet = planet;
        title = planet.name;

    }
}

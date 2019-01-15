package huds.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import utils.Animator;

public class MineralButton {

    public Sprite slot;
    public Animator mineralAnimator;
    public Sprite mineral;
    public Rectangle rect;
    public boolean touched = false;


    public int HP, AP, PP, LVL;
    public String name, ABILITY;
    private BitmapFont font;

    String value = "";


    public MineralButton(String value, String mineralname, int y, int HP, int AP, int PP, int LVL, String ABILITY){
        this.value = value;
        name = mineralname;
        this.HP = HP;
        this.AP = AP;
        this.PP = PP;
        this.LVL = LVL;
        this.ABILITY = ABILITY;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = Initial.parameter;
        parameter.size = 13;

        font = Initial.generator.generateFont(parameter);
        font.setColor(Color.WHITE);


        slot = new Sprite(new Texture(Gdx.files.internal("huds/menu/level slot.png")));
        int[] size2 = {32,32};
        mineralAnimator = new Animator(new Texture(Gdx.files.internal("minerals/"+mineralname+" front.png")),1,2,2,0.7f,size2);
        mineral = new Sprite();

        slot.setSize(slot.getWidth()*2,slot.getHeight()*2);
        //slot.setPosition(Initial.HEIGHT/5, Initial.WIDTH*0.6f - (slot.getHeight()+ 50)*y);
        slot.setPosition(Initial.HEIGHT/4.75f + (slot.getHeight()+ 35) * y, Initial.WIDTH*0.59f);
        mineral.setPosition(slot.getX()+slot.getWidth()*0.25f,slot.getY()+slot.getHeight()*0.25f);
        mineral.setSize(64,64);
        rect = new Rectangle(slot.getX(),slot.getY(), Initial.WIDTH/2.7f,slot.getHeight());

    }

    public void update(){

    }
    public void draw(SpriteBatch batch){

        slot.draw(batch);
        mineralAnimator.draw(mineral,batch);
        font.draw(batch, name,slot.getX() + slot.getHeight()*0.25f , slot.getY() - slot.getHeight() * 0.2f);
        //font.draw(batch,"LVL   "+ LVL,slot.getX() + slot.getWidth()*2.4f,slot.getY()+ slot.getHeight()*0.8f);
        //font.draw(batch, "HP   "+ HP,slot.getX() + slot.getWidth()*1.2f, slot.getY() + slot.getHeight()*0.5f);
        //font.draw(batch,"AP   "+ AP,slot.getX() + slot.getWidth()*2.4f,slot.getY()+ slot.getHeight()*0.5f);
        //font.draw(batch,"PP   "+ PP,slot.getX() + slot.getWidth()*1.2f,slot.getY()+ slot.getHeight()*0.2f);
        //font.draw(batch,ABILITY,slot.getX() + slot.getWidth()*2.4f,slot.getY()+ slot.getHeight()*0.2f);

    }
    public void input(Vector3 vec){
        if(rect.contains(vec.x,vec.y)){
            touched = true;
        }
    }


    public void touchUp(){
        touched = false;
    }
    public Rectangle getBoundingRectangle(){
        //
        return rect;
    }
    public String getValue(){
        return value;
    }

    public void updateMineral(String value, String name, String effect) {
        int[] size2 = {32,32};
        mineralAnimator = new Animator(new Texture(Gdx.files.internal("minerals/"+name+" front.png")),1,2,2,0.7f,size2);


        this.value = value;
        this.ABILITY = effect;
        this.name = name;

    }
}

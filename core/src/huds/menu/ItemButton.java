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

import java.util.List;

import utils.Words;

public class ItemButton {

    public Sprite slot;
    //public Animator mineralAnimator;
    public Sprite item;
    public Rectangle rect;
    public boolean touched = false;



    public String value, name, effect;
    private BitmapFont font;

    public ItemButton(String value, int y, String itemname,  String effect){
        name = itemname;
        this.value = value;
        this.effect = effect;
        fixEffect();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = Initial.parameter;
        parameter.size = 13;

        font = Initial.generator.generateFont(parameter);
        font.setColor(Color.WHITE);


        slot = new Sprite(new Texture(Gdx.files.internal("huds/menu/level slot.png")));

        //mineralAnimator = new Animator(new Texture(Gdx.files.internal("items/"+itemname+".png")),1,1,1,0.7f,size2);
        item = new Sprite(new Texture(Gdx.files.internal("items/"+itemname+".png")));

        slot.setSize(slot.getWidth() * 2,slot.getHeight() * 2);
        slot.setPosition(Initial.HEIGHT/5, Initial.WIDTH * 0.6f - (slot.getHeight()+ 50) * y);
        item.setPosition(slot.getX()+slot.getWidth() * 0.25f,slot.getY()+slot.getHeight() * 0.25f);
        item.setSize(64,64);
        rect = new Rectangle(slot.getX(),slot.getY(), Initial.WIDTH/2.7f,slot.getHeight());

    }

    public void update(){

    }
    public void draw(SpriteBatch batch){

        slot.draw(batch);
        //mineralAnimator.draw(item,batch);
        item.draw(batch);
        font.draw(batch, name,slot.getX() + slot.getWidth()*1.2f, slot.getY()+ slot.getHeight()*0.8f);
        font.draw(batch, effect,slot.getX() + slot.getWidth()*1.2f, slot.getY() + slot.getHeight()*0.5f);


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

    public void fixEffect(){
        if(effect.length() <=25) return;

        String[] words = effect.split(" ");
        List<String> fixed = Words.fullJustify(words,25);
        String temp = "";
        for(String linea : fixed){
            temp += linea;
            temp += "\n";
        }
        effect = temp;

    }

    public void updateItem(String value, String name, String effect){
        item.setTexture(new Texture(Gdx.files.internal("items/"+name+".png")));
        this.value = value;
        this.effect = effect;
        this.name = name;
        fixEffect();
    }
    public String getValue(){
        return value;
    }
}

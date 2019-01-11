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

    Sprite box;
    BasicMenu menu;
    public boolean showDescription = false;


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
        slot.setPosition(Initial.HEIGHT/4.75f + (slot.getHeight()+ 35) * y, Initial.WIDTH * 0.6f - (slot.getHeight()+ 60) * 2 );
        item.setPosition(slot.getX()+slot.getWidth() * 0.25f,slot.getY()+slot.getHeight() * 0.25f);
        item.setSize(64,64);
        rect = new Rectangle(slot.getX(),slot.getY(), Initial.WIDTH/2.7f,slot.getHeight());

        box = new Sprite(new Texture(Gdx.files.internal("huds/menu/description box.png")));

    }

    public void setParent(BasicMenu menu){
        this.menu = menu;
        box.setPosition(menu.menutitleL.getX(),menu.menutitleL.getY());
        box.setSize(menu.menuLU.getWidth()+menu.menuCU.getWidth()+menu.menuRU.getWidth(), menu.menuLU.getHeight()*2.3f);
    }

    public void update(){

    }
    public void draw(SpriteBatch batch){

        slot.draw(batch);
        //mineralAnimator.draw(item,batch);
        item.draw(batch);

        if(showDescription){
            box.draw(batch);
            font.draw(batch,name,box.getX()+75,box.getY()+box.getHeight()*3/4);
            font.draw(batch,effect,box.getX()+50,box.getY()+box.getHeight()/2);
        }

        //font.draw(batch, name,slot.getX() + slot.getWidth()*1.2f, slot.getY()+ slot.getHeight()*0.8f);
        //font.draw(batch, effect,slot.getX() + slot.getWidth()*1.2f, slot.getY() + slot.getHeight()*0.5f);


    }
    public void input(Vector3 vec){
        if(rect.contains(vec.x,vec.y)){
            showDescription = true;

            touched = true;
        }
    }


    public void touchUp(){
        touched = false;
        showDescription = false;

    }
    public Rectangle getBoundingRectangle(){
        //
        return rect;
    }

    public void fixEffect(){
        if(effect.length() <=35) return;

        String[] words = effect.split(" ");
        List<String> fixed = Words.fullJustify(words,35);
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

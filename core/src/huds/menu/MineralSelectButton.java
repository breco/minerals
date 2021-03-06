package huds.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

import utils.Animator;
import utils.Words;

public class MineralSelectButton {


    String name, description;
    String value;
    Sprite item;
    public Animator mineralAnimator;
    Sprite box;
    BasicMenu menu;
    public boolean showDescription = false;
    BitmapFont font, selectedfont;
    boolean touched = false;
    String originalDescription;
    boolean selected = false;
    public MineralSelectButton(Texture texture, String value, String name, String text, float x, float y){
        this.value = value;
        this.name = name;
        int[] size2 = {32,32};
        mineralAnimator = new Animator(new Texture(Gdx.files.internal("minerals/"+name+" front.png")),1,2,2,0.7f,size2);

        originalDescription = text;
        List<String> fixed = Words.fullJustify(text.split(" "),35);
        String temp = "";
        for(String linea : fixed){
            temp += linea;
            temp += "\n";
        }

        description = temp;

        item = new Sprite(texture);
        item.setPosition(Initial.HEIGHT/4.3f + Initial.HEIGHT*x/4.3f,Initial.WIDTH * 0.7f - Initial.WIDTH*y/8);
        item.setSize(96,96);
        box = new Sprite(new Texture(Gdx.files.internal("huds/menu/description box.png")));


        FreeTypeFontGenerator.FreeTypeFontParameter parameter = Initial.parameter;
        parameter.size = 13;
        font = Initial.generator.generateFont(parameter);
        font.setColor(Color.WHITE);
        selectedfont = Initial.generator.generateFont(parameter);
        selectedfont.setColor(Color.BLUE);
    }

    public void setParent(BasicMenu menu){
        this.menu = menu;
        box.setPosition(menu.menutitleL.getX(),menu.menutitleL.getY());
        box.setSize(menu.menuLU.getWidth()+menu.menuCU.getWidth()+menu.menuRU.getWidth(), menu.menuLU.getHeight()*2.3f);

    }

    public void update(){


    }

    public void draw(SpriteBatch batch){
        item.draw(batch);
        mineralAnimator.draw(item,batch);

        if(showDescription){
            box.draw(batch);
            font.draw(batch,name,box.getX()+75,box.getY()+box.getHeight()*3/4);
            font.draw(batch,description,box.getX()+50,box.getY()+box.getHeight()/2);

        }


    }

    public void input(Vector3 vec){
        if(item.getBoundingRectangle().contains(vec.x,vec.y)){
            showDescription = true;
            touched = true;
        }
    }
    public void touchUp(Vector3 vec){
        showDescription = false;
        touched = false;
    }
    public boolean isTouched(){
        return touched;
    }

    public String getValue(){
        return value;
    }

    public void setSelected(){
        selected = true;

    }
    public void setUnselected()
    {
        selected = false;

    }
}


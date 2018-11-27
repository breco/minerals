package huds.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class BasicButton extends Sprite {

    private Texture normal, pressed;
    private String text;
    private Texture icon;
    private BitmapFont font;
    public boolean touched = false;
    public boolean show = true;
    public BasicButton(Texture normal, Texture pressed, float x, float y){
        super(normal);
        setPosition(x,y);
        this.normal = normal;
        this.pressed = pressed;
        text = "";

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;

        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
    }

    public void update(){

    }
    public void input(Vector3 vec) {

        if (getBoundingRectangle().contains(vec.x, vec.y)) {

            touched = true;
            setTexture(pressed);
        }
    }

    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);
        font.draw(batch,text,getX()+getWidth()*0.2f,getY()+getHeight()*0.7f);
        if(icon != null) batch.draw(icon, getX()+getWidth()/5,getY()+getHeight()/5, icon.getWidth()*2,icon.getHeight()*2);
    }
    public void setText(String text){
        this.text = text;
    }
    public void setIcon(Texture texture){
        this.icon = texture;
    }
    public void setShow(boolean show){
        this.show = show;
    }
    public void touchUp(){
        if(!touched) return;
        setTexture(normal);
        touched = false;

    }
    public boolean contains(Vector3 vec){
        return getBoundingRectangle().contains(vec.x,vec.y);
    }
}

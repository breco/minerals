package huds.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class LevelButton {

    public Sprite slot;
    public Sprite star1,star2,star3;
    public int stars;
    public Sprite number;
    public Rectangle rect;
    public String name;
    public boolean touched = false;

    //TEST
    //public Texture green = new Texture(Gdx.files.internal("colors/green.png"));
    public LevelButton(int y, String number, int stars){
        this.stars = stars;
        this.name = number;
        this.number = new Sprite(new Texture(Gdx.files.internal("huds/numbers/"+number+".png")));

        slot = new Sprite(new Texture(Gdx.files.internal("huds/menu/level slot.png")));
        star1 = new Sprite(new Texture(Gdx.files.internal("huds/menu/star.png")));
        star2 = new Sprite(star1.getTexture());
        star3 = new Sprite(star1.getTexture());
        slot.setSize(slot.getWidth()*2,slot.getHeight()*2);
        slot.setPosition(Initial.HEIGHT/5, Initial.WIDTH*0.69f - (slot.getHeight()+ 10)*y);
        this.number.setPosition(slot.getX()+slot.getWidth()*0.35f,slot.getY()+slot.getHeight()*0.3f);
        this.number.setSize(this.number.getWidth()*3,this.number.getHeight()*3);
        if(stars == 2){
            star3.setColor(Color.BLACK);
        }
        if(stars == 1){
            star2.setColor(Color.BLACK);
            star3.setColor(Color.BLACK);
        }
        if(stars == 0){
            star1.setColor(Color.BLACK);
            star2.setColor(Color.BLACK);
            star3.setColor(Color.BLACK);
        }
        star1.setPosition(slot.getX() + 5 + slot.getWidth(),slot.getY()+slot.getHeight()/6);
        star2.setPosition(slot.getX() + slot.getWidth()*2 -5,slot.getY()+slot.getHeight()/6);
        star3.setPosition(slot.getX() + slot.getWidth()*3 -10,slot.getY()+slot.getHeight()/6);
        star1.setSize(slot.getWidth()*0.7f,slot.getHeight()*0.7f);
        star2.setSize(slot.getWidth()*0.7f,slot.getHeight()*0.7f);
        star3.setSize(slot.getWidth()*0.7f,slot.getHeight()*0.7f);

        rect = new Rectangle(slot.getX(),slot.getY(), Initial.WIDTH/2.7f,slot.getHeight());
    }
    public void update(){

    }
    public void draw(SpriteBatch batch){
        //batch.draw(green,rect.x,rect.y,rect.getWidth(),rect.getHeight());
        slot.draw(batch);
        number.draw(batch);
        star1.draw(batch);
        star2.draw(batch);
        star3.draw(batch);
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
        return rect;
    }
}

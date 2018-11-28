package huds.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import utils.Animator;

public class MineralButton {

    public Sprite slot;
    public Animator mineralAnimator;
    public Sprite mineral;
    public Rectangle rect;
    public boolean touched = false;


    public int HP, AP, PP;
    public String name;


    public MineralButton(int y){
        slot = new Sprite(new Texture(Gdx.files.internal("huds/menu/level slot.png")));
        int[] size2 = {32,32};
        mineralAnimator = new Animator(new Texture(Gdx.files.internal("minerals/terro front.png")),1,2,2,0.2f,size2);
        mineral = new Sprite();

        slot.setSize(slot.getWidth()*2,slot.getHeight()*2);
        slot.setPosition(Initial.HEIGHT/5, Initial.WIDTH*0.69f - (slot.getHeight()+ 10)*y);
        mineral.setPosition(slot.getX()+slot.getWidth()*0.35f,slot.getY()+slot.getHeight()*0.3f);
        mineral.setSize(64,64);
        rect = new Rectangle(slot.getX(),slot.getY(), Initial.WIDTH/2.7f,slot.getHeight());

    }

    public void update(){

    }
    public void draw(SpriteBatch batch){

        slot.draw(batch);
        mineralAnimator.draw(mineral,batch);

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
}

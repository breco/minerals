package huds.maingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minerals.Mineral;
import utils.Animator;

public class MineralPin extends Sprite {
    Mineral owner;
    Animator animator;
    public MineralPin(Mineral owner, String color){

        setSize(45,57);
        this.owner = owner;

        int[] size = {40,51};
        Gdx.app.log("SIZE!!",size[0]+","+size[1]);
        animator = new Animator(new Texture(Gdx.files.internal("pins/"+color+"_pin.png")),1,4,4,0.2f,size);
    }
    public void draw(SpriteBatch batch){
        setX(owner.getX()+owner.getWidth()/6f);
        setY(owner.getY() - owner.getHeight()*2);
        animator.draw(this,batch);
        //batch.draw(pin,owner.getX()+owner.getWidth()/2,owner.getY() - owner.getHeight() * 3f);
    }
}

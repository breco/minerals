package items;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import utils.MyGestures;

public abstract class Item extends Sprite {
    public String type;
    public boolean touched = false;
    public boolean touched2 = false;
    public boolean used = false;

    public Rectangle rect;
    public int fixMovY;
    public float virtualX,virtualY;
    public float baseX,baseY;
    public int i;
    public Texture cross;
    public float initialX = Initial.HEIGHT - getWidth()*2 - 10;
    //public Texture green = new Texture(Gdx.files.internal(("colors/green.png")));

    public Item(Texture texture, String type){
        super(texture);
        setSize(50,50);
        this.type = type;
        rect = new Rectangle();
        fixMovY = 50;

        cross = new Texture(Gdx.files.internal("huds/mainGame/cross.png"));


    }
    public void setPosition(int i){

        setPosition(initialX,Initial.WIDTH/2 - ((getWidth()+30)*i));
        Gdx.app.log("POSITION",getX()+","+getY());
        baseX = getX();
        baseY = getY();
        virtualX = baseX;
        virtualY = baseY;
        this.i = i;

    }
    public void update(){
        move();
    }

    public abstract void effect();

    public Rectangle getBoundingRectangle() {
        Rectangle rect = super.getBoundingRectangle();
        rect.setSize(100,50);
        return rect;
    }

    public void input(Vector3 vec, int pointer){
        if(used) return;

        if(getBoundingRectangle().contains(vec.x,vec.y)){

            if(type.equals("auto")){ // Applicable to all pixies
                effect();
            }
            if(type.equals("select")){ //Applicable to selected pixie
                if(pointer == 0){
                    touched = true;
                }
                else if(pointer == 1){
                    touched2 = true;
                }


            }
        }
    }

    public void move(){
        if(touched){
            virtualX -= MyGestures.diff.x;
            virtualY -= MyGestures.diff.y;

        }
        if(touched2){
            virtualX -= MyGestures.diff2.x;
            virtualY -= MyGestures.diff2.y;

        }



    }
    public void draw(SpriteBatch batch){

        super.draw(batch);
        if(used){
            batch.draw(cross,getX(),getY(),50,50);
            return;
        }
        if(touched || touched2){



            batch.draw(getTexture(),virtualX-fixMovY*2,virtualY+fixMovY,getWidth(),getHeight());

        }

    }
    public void resetPosition(){
        virtualX = baseX;
        virtualY = baseY;

    }
    public Rectangle getMovingRectangle(){
        rect.setPosition(virtualX-fixMovY*2,virtualY+fixMovY);
        rect.setSize(getWidth(),getHeight());
        return rect;
    }
}


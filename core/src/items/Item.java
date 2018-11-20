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
    public int i;
    public Texture itemSlot,cross;

    public Item(Texture texture, String type){
        super(texture);
        setSize(90,90);
        this.type = type;
        rect = new Rectangle();
        fixMovY = 90;
        itemSlot = new Texture(Gdx.files.internal("huds/mainGame/itemSlot.png"));
        cross = new Texture(Gdx.files.internal("huds/mainGame/cross.png"));

    }
    public void setPosition(int i){
        Gdx.app.log("i position",i+"");
        Gdx.app.log(Initial.HEIGHT/7.8f+i* Initial.HEIGHT/3.25f+"",Initial.WIDTH/24+"");
        setPosition(Initial.HEIGHT/7.8f+i* Initial.HEIGHT/3.25f,Initial.WIDTH/24);
        virtualX = getX();
        virtualY = getY();
        this.i = i;
    }
    public void update(){
        move();
    }

    public abstract void effect();

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
        batch.draw(itemSlot,Initial.HEIGHT/15+i*Initial.HEIGHT/3.25f,Initial.WIDTH/256,180,180);

        super.draw(batch);
        if(used){

            batch.draw(cross,Initial.HEIGHT/7.8f+i*Initial.HEIGHT/3.25f,Initial.WIDTH/24,90,90);
            return;
        }
        if(touched || touched2){
            batch.draw(getTexture(),virtualX,virtualY+fixMovY,getWidth(),getHeight());
        }

    }
    public void resetPosition(){
        Gdx.app.log("RESET POSITION","MYSTERY");
        virtualX = getX();
        virtualY = getY();

    }
}


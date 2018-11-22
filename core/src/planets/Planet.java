package planets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Planet extends Sprite {
    public boolean touched;
    public String level;
    public Planet(Texture texture, int x, int y, String level){
        super(texture);
        super.setPosition(x,y);
        touched = false;
        this.level = level;

    }
    public void update(){

    }

    public void input(Vector3 vec){
        if(getBoundingRectangle().contains(vec.x,vec.y)){

            touched = true;
        }
    }
}

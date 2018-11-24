package minerals;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Minerals{
    private Array<Mineral> minerals;
    private Mineral temp;
    private float x, y, dist;
    public Minerals() {
        minerals = new Array<Mineral>();
        x = 0;
        y = 0;
        dist = 100000;

    }

    public void draw(SpriteBatch batch){
        //make the selected mineral be drawed above other pixies
        temp = null;
        for(Mineral mineral : minerals){
            if(mineral.touched || mineral.touched2)
                temp = mineral;
        }
        if(temp != null){
            minerals.removeValue(temp,false);
            minerals.add(temp);
        }
        //draw pixies
        for (Mineral mineral : minerals) {
            mineral.draw(batch);
        }

    }

    public void update(){
        for (Mineral mineral : minerals) {
            mineral.update();
        }
    }

    public void add(Mineral mineral) {
        //
        minerals.add(mineral);
    }

    public Array<Mineral> getMinerals() {
        Array<Mineral> temp = new Array<Mineral>();
        for(Mineral mineral : minerals){
            if(!mineral.getStatus().equals("dead")){
                temp.add(mineral);
            }
        }
        return temp;
    }

    public Mineral getMostRight(){
        float x = -1;
        Mineral temp = null;
        for(Mineral mineral : minerals){
            if(mineral.getX() > x){
                x = mineral.getX();
                temp = mineral;
            }
        }
        return temp;
    }

    public int getInitialPP(){
        int PP = 0;
        for(Mineral mineral : minerals){
            PP += mineral.getPP();
        }
        return PP;
    }



    public void input(Vector3 vec, int pointer) {
        //get touched pixies
        for (Mineral mineral : minerals) {
            mineral.input(vec, pointer);
        }

        if (pointer == 0) {
            //get nearest pixie from touch position
            for (Mineral mineral : minerals) {
                if (!mineral.touched) continue;
                x = mineral.touchRect.getX() + mineral.touchRect.getWidth() / 2;
                y = mineral.touchRect.getY() + mineral.touchRect.getHeight() / 2;

                if (vec.dst(x, y, 0) < dist) {
                    dist = vec.dst(x, y, 0);
                    temp = mineral;
                }
            }
            //mark only the nearest pixie
            for (Mineral mineral : minerals) {
                if (mineral.equals(temp)) {
                    mineral.touched = true;
                } else {
                    mineral.touched = false;
                }
            }
            //make other pixies untouchable
            for (Mineral mineral : minerals) {
                if (!mineral.touched) mineral.canBeTouched = false;
            }
        } else if (pointer == 1) {
            //get nearest pixie from touch position
            for (Mineral mineral : minerals) {
                if (!mineral.touched2) continue;
                x = mineral.touchRect.getX() + mineral.touchRect.getWidth() / 2;
                y = mineral.touchRect.getY() + mineral.touchRect.getHeight() / 2;
                //Gdx.app.log("dist",""+vec.dst(x,y,0));
                if (vec.dst(x, y, 0) < dist) {
                    dist = vec.dst(x, y, 0);
                    temp = mineral;
                }
            }
            //mark only the nearest pixie
            for (Mineral mineral : minerals) {
                if (mineral.equals(temp)) {
                    mineral.touched2 = true;
                } else {
                    mineral.touched2 = false;
                }
            }
            //make other pixies untouchable
            for (Mineral mineral : minerals) {
                if (!mineral.touched2) mineral.canBeTouched2 = false;
            }
        }

        temp = null;
        x = 0;
        y = 0;
        dist = 10000;

    }

    public void setUntouched(int pointer) {
        if (pointer == 0) {

            for (Mineral mineral : minerals) {

                mineral.touched = false;
                mineral.canBeTouched = true;
                mineral.longTouched = false;
                mineral.canBeLongTouched = true;
            }
        } else if (pointer == 1) {

            for (Mineral mineral : minerals) {
                mineral.touched2 = false;
                mineral.canBeTouched2 = true;
                mineral.longTouched2 = false;
                mineral.canBeLongTouched2 = true;
            }
        }

    }
}

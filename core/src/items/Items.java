package items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


public class Items {
    public Array<Item> items;

    public Items() {
        items = new Array<Item>();



    }
    public void draw(SpriteBatch batch){
        for(Item item  : items){
            item.draw(batch);
        }
    }
    public void update(){
        for(Item item : items){
            item.update();
        }
    }
    public void remove(Item item){
        items.removeValue(item, false);
    }
    public Array<Item> getItems(){
        return items;
    }
    public void add(Item item){
        items.add(item);
    }
    public void setPosition(){
        int  i = 0;
        for(Item item : items){
            item.setPosition(i);
            i++;
        }
    }
    public void input(Vector3 vec, int pointer){
        for(Item item : items){
            item.input(vec, pointer);
        }


    }
    public void touchUp(int pointer){
        if(pointer == 0){
            for(Item item : items){
                if(item.touched) item.effect();
            }
        }
        if(pointer == 1){
            for(Item item : items){
                if(item.touched2) item.effect();
            }
        }

    }
    public void setUntouched(int pointer){
        if(pointer == 0){
            for(Item item : items){
                if(item.touched) item.resetPosition();
                item.touched = false;
            }
        }
        if(pointer == 1){
            for(Item item : items){
                if(item.touched2) item.resetPosition();
                item.touched2 = false;
            }
        }
    }
}

package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import minerals.Mineral;
import screens.MainGame;

public class Fruit extends Item {
    private int amount;

    public Fruit(Texture texture){
        super(texture,"select");
        amount = 10;

        //vec = new Vector3();
    }



    @Override
    public void effect() {

        rect.setPosition(virtualX-fixMovY/2,virtualY+fixMovY);
        rect.setSize(getWidth()/2,getHeight());

        Gdx.app.log("FRUiT RECTANGLE",""+rect);
        for(Mineral mineral: MainGame.minerals.getMinerals()){
            //Rectangle inter = new Rectangle();
            //Intersector.intersectRectangles(mineral.getBoundingRectangle(), rect, inter);
            if(rect.overlaps(mineral.getBoundingRectangle())){
                Gdx.app.log("USING","EFECT");
                mineral.heal(amount);
                used = true;
                break;
            }

        }

    }
}

package items;

import com.badlogic.gdx.graphics.Texture;

import minerals.Mineral;
import screens.MainGame;

public class Fruit extends Item {
    private int amount;

    public Fruit(Texture texture){
        super(texture,"select");
        amount = 10;

    }



    @Override
    public void effect() {
        for(Mineral mineral: MainGame.minerals.getMinerals()){
            if(getMovingRectangle().overlaps(mineral.getBoundingRectangle())){
                mineral.heal(amount);
                used = true;
                break;
            }

        }

    }
}

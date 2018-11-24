package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import bullets.MirrorShield;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class Mirror extends Item {
    private int amount;
    private Animator bulletAnimator;
    public Mirror(Texture texture){
        super(texture,"select");
        amount = 15;
        int[] size = {40,8};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/mirror.png")),1,1,1,0,size);


    }



    @Override
    public void effect() {

        for(Mineral mineral: MainGame.minerals.getMinerals()){
            if(getMovingRectangle().overlaps(mineral.getBoundingRectangle())){
                MainGame.bullets.add(new MirrorShield(mineral,bulletAnimator));
                used = true;
                break;
            }

        }

    }
}


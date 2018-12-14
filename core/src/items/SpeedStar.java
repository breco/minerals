package items;

import com.badlogic.gdx.graphics.Texture;

import minerals.Mineral;
import screens.MainGame;
import utils.TimeManager;

public class SpeedStar extends Item {
    private float amount;
    private TimeManager time;
    private int EFFECT_DURATION;
    private boolean finished = false;
    private Mineral affected;

    public SpeedStar(Texture texture) {
        super(texture, "select");
        amount = 0.3f;
        EFFECT_DURATION = 10;
        time = new TimeManager();


    }
    public void effect(){



        for(Mineral mineral: MainGame.minerals.getMinerals()){

            if(getMovingRectangle().overlaps(mineral.getBoundingRectangle())){
                mineral.changeSPD(amount);
                
                affected = mineral;
                used = true;
                time.start();
                break;
            }

        }




    }
    public void update(){
        move();
        finishEffect();
    }
    public void finishEffect(){
        if(finished) return;
        if((int)time.getTime() == EFFECT_DURATION){
            finished = true;
            affected.changeSPD(-amount);

        }
    }
}


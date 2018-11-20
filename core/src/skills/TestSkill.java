package skills;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Minerals.Mineral;
import screens.MainGame;
import utils.TimeManager;

public class TestSkill extends Skill {

    private boolean finished = false;
    private TimeManager time;
    private int EFFECT_DURATION;
    private float amount;


    public TestSkill(Mineral owner, Texture texture){
        super(owner,texture);
        amount = 0.4f;
        EFFECT_DURATION = 10;
        time = new TimeManager();
        REQUIRED_PP = 5;
        COOL_DOWN = 10;
    }

    @Override
    public void update() {
        finishEffect();
    }

    @Override
    public void effect() {
        if((int)time.getTime() < EFFECT_DURATION){
            revertEffect();
        }
        finished = false;
        owner.changeSPD(amount);

        MainGame.chargePP(-REQUIRED_PP);
        time.start();
        startCoolDown();

    }

    public void finishEffect(){
        if(finished) return;
        if((int)time.getTime() == EFFECT_DURATION){

            revertEffect();

        }
    }

    public void revertEffect(){

        finished = true;
        owner.changeSPD(-amount);

    }


    @Override
    public void drawEffect(SpriteBatch batch) {

    }
}

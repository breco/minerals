package skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.BubbleShield;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;


public class LaunchBubbleShield extends Skill {

    private Animator bulletAnimator;

    public LaunchBubbleShield(Mineral owner, Texture texture){
        super(owner,texture);


        REQUIRED_PP = 15;
        COOL_DOWN = 10;
        int size[] = {64,64};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/bubble shield.png")),1,1,1,0.5f,size);

    }

    @Override
    public void update() {
        finishEffect();
    }

    @Override
    public void effect() {

        MainGame.bullets.add(new BubbleShield(owner,bulletAnimator,1,1,' ',' '));
        startCoolDown();

    }

    public void finishEffect(){


    }

    public void revertEffect(){

    }


    @Override
    public void drawEffect(SpriteBatch batch) {

    }


}

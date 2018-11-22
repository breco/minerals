package skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.RockWheel;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class LaunchRockWheel extends Skill {

    private Animator bulletAnimator;

    public LaunchRockWheel(Mineral owner, Texture texture){
        super(owner,texture);


        REQUIRED_PP = 8;
        COOL_DOWN = 15;
        int size[] = {16,16};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/rock wheel.png")),1,1,1,0.5f,size);

    }

    @Override
    public void update() {
        finishEffect();
    }

    @Override
    public void effect() {
        for(int i = 0;i<5;i++){
            MainGame.bullets.add(new RockWheel(owner,bulletAnimator,72*i));
        }
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


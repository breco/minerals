package skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.FireBall;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class LaunchFireBalls extends Skill {

    private Animator bulletAnimator;

    public LaunchFireBalls(Mineral owner, Texture texture){
        super(owner,texture);


        REQUIRED_PP = 10;
        COOL_DOWN = 5;
        int size[] = {16,16};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/fireball.png")),3,2,6,0.5f,size);

    }

    @Override
    public void update() {
        finishEffect();
    }

    @Override
    public void effect() {

        MainGame.bullets.add(new FireBall(owner,bulletAnimator, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()), ' ', 'U'));
        MainGame.bullets.add(new FireBall(owner,bulletAnimator, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()), 'L', 'U'));
        MainGame.bullets.add(new FireBall(owner,bulletAnimator, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()), 'R', 'U'));
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

package skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.Firework;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

public class LaunchFireworks extends Skill {

    private Animator ba1,ba2,ba3,ba4,ba5;
    private TimeManager time, shotTimer;
    private int EFFECT_DURATION;
    private boolean finished = false;

    public LaunchFireworks(Mineral owner, Texture texture){
        super(owner,texture);

        EFFECT_DURATION = 10;
        time = new TimeManager();
        shotTimer = new TimeManager();

        REQUIRED_PP = 5;
        COOL_DOWN = 12;

        int size[] = {8,48};
        ba1 = new Animator(new Texture(Gdx.files.internal("bullets/firework bullet.png")),1,1,1,0.5f,size);
        ba2 = new Animator(new Texture(Gdx.files.internal("bullets/firework bullet.png")),1,1,1,0.5f,size);
        ba3 = new Animator(new Texture(Gdx.files.internal("bullets/firework bullet.png")),1,1,1,0.5f,size);
        ba4 = new Animator(new Texture(Gdx.files.internal("bullets/firework bullet.png")),1,1,1,0.5f,size);
        ba5 = new Animator(new Texture(Gdx.files.internal("bullets/firework bullet.png")),1,1,1,0.5f,size);

    }

    @Override
    public void update() {
        if(!time.started()) return;
        if(!shotTimer.ring()) return;

        MainGame.bullets.add(new Firework(owner, ba1, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()),-45));
        MainGame.bullets.add(new Firework(owner, ba2, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()),-22.5f));
        MainGame.bullets.add(new Firework(owner, ba3, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()),0));
        MainGame.bullets.add(new Firework(owner, ba4, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()),22.5f));
        MainGame.bullets.add(new Firework(owner, ba5, (int) (owner.getX() + owner.getWidth() / 2), (int) (owner.getY() + owner.getHeight()),45));

        shotTimer.reset();
        shotTimer.setChronometer(0.5f);
        shotTimer.start();
        finishEffect();
    }

    @Override
    public void effect() {

            time.start();
            startCoolDown();
            shotTimer.setChronometer(0.5f);
            shotTimer.start();

            owner.changeSPD(-10);
            finished = false;





    }

    public void finishEffect(){
        if(finished) return;
        if((int)time.getTime() == EFFECT_DURATION){
            finished = true;
            time.reset();
            owner.changeSPD(10);

        }

    }

    public void revertEffect(){

    }


    @Override
    public void drawEffect(SpriteBatch batch) {

    }
}

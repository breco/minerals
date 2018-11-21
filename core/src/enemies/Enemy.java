package enemies;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import minerals.Mineral;
import screens.MainGame;
import utils.Animator;
import utils.Counter;


public abstract class Enemy extends Sprite {
    //stats
    int HP,ATK;
    int CURRENT_HP;
    //others
    int appearance;
    //animation
    boolean blink = false;
    Counter impactCounter;
    Animator animator;
    Animator bulletAnimator;
    //sound effects
    Sound pium;
    public Enemy(int x, int y,int HP,int ATK, int appearance){
        setPosition(x, y);
        setSize(32,32);
        scale(2);
        this.HP = HP;
        this.ATK = ATK;
        CURRENT_HP = HP;
        this.appearance = appearance;
        //animation
        impactCounter = new Counter();

    }
    public abstract void shoot();
    public abstract void update();
    public abstract void move();
    public abstract void animation();

    public boolean onScreen(){
        if(getY() <= - 0) return false;
        if(appearance <= MainGame.time.getTime()) return true;
        return false;

    }
    public abstract void draw(SpriteBatch batch);
    public void getDamage(int dmg) {
        if(!impactCounter.started())  impactCounter.setLimit(25);
        CURRENT_HP -= dmg;
        if (CURRENT_HP < 0) {
            CURRENT_HP = 0;
            MainGame.enemies.remove(this);
            MainGame.chargePP(1);
        }

    }
    public void attack(){
        for(Mineral mineral : MainGame.minerals.getMinerals()){
            if(mineral.getBoundingRectangle().overlaps(getBoundingRectangle())){
                mineral.impact(ATK);
                return;
            }
        }
    }
}





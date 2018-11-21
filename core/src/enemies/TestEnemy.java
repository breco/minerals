package enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bullets.EnemyBullet;
import screens.MainGame;
import utils.Animator;


public class TestEnemy extends Enemy {

    //movement variables
    char ori;
    int MAX_SPD = 100;
    int SPD_X = 3;
    int SPD_Y = 1;
    int SPD_CONT = 75;
    int SHOT_CONT =  100; //ThreadLocalRandom.current().nextInt(10,61);


    // HP ATK
    public TestEnemy(int x, int y, int appearance){
        super(x,y,8,2,appearance);
        int index = 0; //ThreadLocalRandom.current().nextInt(0,2);
        char[] orientations = {'L','R'};
        ori = orientations[index];
        setPosition(x,y);
        animator = new Animator(new Texture(Gdx.files.internal("enemies/tuzzu.png")),1,4,4,0.15f);
        //pium = Gdx.audio.newSound(Gdx.files.internal("sound effects/04.wav"));
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/tuzzu bullet.png")),1,2,2,0.8f,size);


    }

    public void move(){
        SPD_CONT++;
        if(ori == 'L'){
            setX(getX() - SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'R';
                SPD_CONT = 0;
            }
        }
        else if(ori == 'R'){
            setX(getX() + SPD_X);
            if(SPD_CONT >= MAX_SPD){
                ori = 'L';
                SPD_CONT = 0;
            }
        }
        setY(getY()-SPD_Y);
    }
    public void shoot(){
        SHOT_CONT--;
        if(SHOT_CONT == 0){
            SHOT_CONT = 20;//ThreadLocalRandom.current().nextInt(100,151);
            float x = getX()+ getWidth();
            float y = getY();

            MainGame.bullets.add(new EnemyBullet(bulletAnimator,(int)x,(int)y,' ','D',ATK,6));
        }
    }
    public void update(){
        attack();
        move();
        shoot();
        animation();
        impactCounter.update();
        if(impactCounter.check()){
            impactCounter.reset();
        }
    }
    public void animation(){
        if(impactCounter.started()){
            if(blink){
                blink = false;
                setColor(Color.WHITE);
            }
            else{
                setColor(Color.BLACK);
                blink = true;
            }
        }
        else{
            setColor(Color.WHITE);
        }
    }
    public void draw(SpriteBatch batch){
        animator.draw(this,batch);
    }
}




package minerals;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import bullets.MineralBullet;
import huds.maingame.MineralPin;
import screens.MainGame;
import skills.Skill;
import utils.Animator;
import utils.Counter;
import utils.MyGestures;
import utils.TimeManager;

public class Mineral extends Sprite {

    //stats

    private int HP;
    private int CURRENT_HP;
    private int PP;
    private int EARNED_PP;


    //

    private String status = "";

    //INPUT VARIABLES
    public Rectangle touchRect;
    public boolean pointerTwoTouched = false;
    //input variables finger 1
    public boolean touched = false;
    public int touchDraw = 0;
    public boolean longTouched = false;
    public boolean canBeTouched = true;
    public boolean canBeLongTouched = true;

    //input variables finger 2
    public boolean touched2 = false;
    public boolean longTouched2 = false;
    public boolean canBeTouched2 = true;
    public boolean canBeLongTouched2 = true;


    //shoot variables
    private float SPEED_SHOOT = 0.5f;
    public Texture bulletTexture;
    private TimeManager timer;
    public Animator bulletAnimator;


    //attack
    private int ATK_FIXED, ATK_VARIABLE = 0,ATK_TOTAL;
    //BULLET STATS
    public int BULLET_SPD = 20;


    //impact variables
    private Counter impactCounter;
    private int IMPACT_COUNTER_VALUE = 50;

    //variables for hud

    public float PERCENT_HP;
    public char COLOR_HP;
    public Texture green = new Texture(Gdx.files.internal("colors/green.png"));
    public Texture red = new Texture(Gdx.files.internal("colors/red.png"));
    public Texture yellow = new Texture(Gdx.files.internal("colors/yellow.png"));
    public Texture gray = new Texture(Gdx.files.internal("colors/gray.png"));
    public Texture curr = green;
    public MineralPin pin;


    //SKILL VARIABLES

    public Skill skill;

    //ANIMATION VARIABLES
    private boolean blink = false;
    Animator animator;

    public Mineral(float x, float y, int HP, int ATK, int SPD, int PP){
        super();
        setPosition(x,y);
        setSize(64,64);

        touchRect = new Rectangle(getX() - getWidth()/2,getY()-getHeight()*5/2,getWidth()*2,getHeight()*4);





        this.HP = HP;
        CURRENT_HP = HP;
        this.PP = PP;
        EARNED_PP = 0;
        //hud
        PERCENT_HP = 100;

        //animations
        impactCounter = new Counter();
        //shoot
        timer = new TimeManager();
        timer.setChronometer(0);
        timer.start();
        //GAME LOGIC
        ATK_FIXED = ATK;
        ATK_TOTAL = ATK_FIXED + ATK_VARIABLE;
    }


    public void update(){
        animation();
        move();
        shoot();
        impactCounter.update();
        if(impactCounter.check()){
            impactCounter.reset();
        }
    }

    public void move(){
        if(touched){
            /*if(MyGestures.newTouch.y >= 5* MainGame.HEIGHT/6-getHeight()){
                return;
            }*/
            //Gdx.app.log("diff",MyGestures.diff.toString());
            setX(getX() - MyGestures.diff.x);
            setY(getY() - MyGestures.diff.y);
            touchRect.setX(touchRect.getX() - MyGestures.diff.x);
            touchRect.setY(touchRect.getY() - MyGestures.diff.y);
            fixMove();
            return;
        }
        if(touched2){
            /*if(MyGestures.newTouch2.y >= 5* MainGame.HEIGHT/6-getHeight()){
                return;
            }*/

            setX(getX() - MyGestures.diff2.x);
            setY(getY() - MyGestures.diff2.y);
            touchRect.setX(touchRect.getX() - MyGestures.diff2.x);
            touchRect.setY(touchRect.getY() -  MyGestures.diff2.y);
            fixMove();
        }



    }

    public void fixMove(){
        int horizontalBorder = Initial.HEIGHT - ((int) getWidth()*2);
        int verticalBorder = Initial.WIDTH - ((int) getHeight());
        if(getX() > horizontalBorder){
            setX(horizontalBorder);
            touchRect.setX(horizontalBorder - getWidth()/2);
        }
        if(getX() < getWidth()){
            setX(getWidth());
            touchRect.setX(getWidth()-getWidth()/2);
        }
        if(getY() < Initial.WIDTH/6.5f){
            setY(Initial.WIDTH/6.5f);
            touchRect.setY(Initial.WIDTH/6.5f-getHeight()*5/2);
        }
        if(getY() > verticalBorder){
            setY(verticalBorder);
            touchRect.setY(verticalBorder - getHeight()*5/2);
        }
    }


    public void input(Vector3 vec, int pointer){
        if(pointer == 0){
            if(!canBeTouched) return;
            if (touchRect.contains(vec.x,vec.y)){

                touched = true;
                touchDraw++;
                return;
            }
            touched = false;
            touchDraw--;
        }
        else if(pointer == 1){
            pointerTwoTouched = true;
            if(!canBeTouched2) return;
            if (touchRect.contains(vec.x,vec.y)){
                touched2 = true;
                touchDraw++;
                return;
            }
            touched2 = false;
            touchDraw--;
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

    public void draw(SpriteBatch batch) {

        //batch.draw(red, touchRect.getX(),touchRect.getY(), touchRect.width, touchRect.getHeight());
        animator.draw(this,batch);
        drawHUD(batch);
    }

    public void drawHUD(SpriteBatch batch){
        if(status.equals("dead")) return;
        batch.draw(gray,getX() - getWidth()/5,getY()-getHeight()/3, Initial.WIDTH*0.07f , Initial.HEIGHT*0.006f);
        batch.draw(curr, getX() - getWidth()/5, getY()-getHeight()/3, PERCENT_HP * Initial.WIDTH*0.07f / 100f, Initial.HEIGHT*0.006f);
        pin.draw(batch);
        //batch.draw(gray,getX() - getWidth(),getY()+getHeight()*3.5f-initial.HEIGHT*0.006f,initial.WIDTH*0.19f ,initial.HEIGHT*0.006f);
        //batch.draw(purple, getX() - getWidth(), getY() + getHeight()*3.5f-initial.HEIGHT*0.006f, PERCENT_PP * initial.WIDTH*0.19f / 100f, initial.HEIGHT*0.006f);

    }


    public void shoot(){
        if(!timer.ring()) return;

        MainGame.bullets.add(new MineralBullet(this,bulletAnimator, (int) (getX() + getWidth()/2f), (int) (getY() + getHeight()*2/3), ' ', 'U', ATK_TOTAL, BULLET_SPD));
        timer.reset();
        timer.setChronometer(SPEED_SHOOT);
        timer.start();
    }


    public void impact(int dmg){
        if(impactCounter.started()){
            return;
        }
        getDamage(dmg);
    }

    public void getDamage(int dmg){
        if(!impactCounter.started())  impactCounter.setLimit(IMPACT_COUNTER_VALUE);
        CURRENT_HP-= dmg;
        if(CURRENT_HP < 0){
            CURRENT_HP = 0;
            status = "dead";
            pause();

        }
        updateHPbar();

    }

    public void heal(int amount){
        if(status.equals("dead")) return;
        CURRENT_HP+= amount;
        if(CURRENT_HP > HP){
            CURRENT_HP = HP;
        }
        updateHPbar();
    }



    public void pause(){
        //timer.pause();
        //pium.stop(piumId);
    }


    public void updateHPbar(){
        PERCENT_HP = CURRENT_HP*100f/HP;
        if(PERCENT_HP >= 50){
            curr = green;
            COLOR_HP = 'G';
        }
        else if(PERCENT_HP >= 25){
            curr = yellow;
            COLOR_HP = 'Y';
        }
        else{
            curr = red;
            COLOR_HP = 'R';
        }
    }

    public void changeSPD(float amount){
        //SPD_VARIABLE += amount;
        //SPD_TOTAL = SPD_FIXED + SPD_VARIABLE;
        SPEED_SHOOT -= amount;
        //piumPlayed = false;
        //piumPitch = (int)(1/SPEED_SHOOT);
    }




    //GETTERS

    public String getStatus(){
        return status;
    }
    public int getPP(){
        return PP;
    }
    public int getEARNED_PP(){
        return EARNED_PP;
    }
    public Skill getSkill(){
        return skill;
    }
}

package skills;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import minerals.Mineral;
import screens.MainGame;
import utils.TimeManager;

public abstract class Skill extends Sprite {

    //STATS
    public int REQUIRED_PP;
    public int COOL_DOWN;

    //COOL DOWN VARIABLES

    TimeManager coolDownTimer;


    Mineral owner;
    public float virtualX,virtualY;
    public int i;
    public boolean touched = false;
    public boolean touched2 = false;
    public boolean used = false;

    //TEST

    private Sprite darkSkill;
    private TextureRegion region;
    private int dif,prev = 0;
    private boolean canDraw = false;
    private float fixedHeight;
    public Skill(Mineral owner, Texture texture){
        super(texture);
        this.owner = owner;

        setSize(90,90);
        coolDownTimer = new TimeManager();

        //TEST

        darkSkill = new Sprite(texture);
        darkSkill.setSize(90,90);

    }
    public void update(){

    }
    public abstract void effect();
    public abstract void revertEffect();
    public abstract void drawEffect(SpriteBatch batch);

    public void startCoolDown(){
        used = true;
        MainGame.chargePP(-REQUIRED_PP);
        coolDownTimer.start();
    }

    public boolean checkCoolDown(){
        if(coolDownTimer.getTime() >= COOL_DOWN){
            return true;
        }
        return false;
    }

    public void setPosition(int i){
        setPosition(Initial.HEIGHT/7f+i* Initial.HEIGHT/3.375f,Initial.WIDTH/24);
        darkSkill.setPosition(Initial.HEIGHT/7f+i* Initial.HEIGHT/3.375f,Initial.WIDTH/24);
        virtualX = getX();
        virtualY = getY();
        this.i = i;
    }

    public void setAvailability(int CURRENT_PP){
        if(CURRENT_PP >= REQUIRED_PP){
            if(checkCoolDown())
                setEnabled();
        }
        else{
            setDisabled();
        }
    }

    public void stopCoolDown(){
        if(checkCoolDown()){
            used = false;
        }
    }
    public void setDisabled(){
        if(getColor().equals(Color.GRAY)) return;
        setColor(Color.GRAY);

    }
    public void setEnabled(){
        if(getColor().equals(Color.WHITE)) return;
        setColor(Color.WHITE);

    }
    public void draw(SpriteBatch batch){

        super.draw(batch);


        if(used){
            float height = coolDownTimer.getTime()/COOL_DOWN;

            dif = ((int) (24 * (1 - height)));
            if(prev - dif != 0){
                fixedHeight = (int)(darkSkill.getHeight()*height);
            }
            else{
            }
            prev = dif;

            region = new TextureRegion(darkSkill.getTexture(),0, ((int) (24 * (1 - height))),24,24);

            batch.draw(region,darkSkill.getX(), darkSkill.getY()-darkSkill.getHeight() + fixedHeight + 5 ,darkSkill.getWidth(),darkSkill.getHeight());
        }



    }
    public void input(Vector3 vec, int pointer){
        if(used) return;
        if(MainGame.CURRENT_PP < REQUIRED_PP) return;
        if(getBoundingRectangle().contains(vec.x,vec.y)){
            effect();
            setDisabled();
        }
    }
}

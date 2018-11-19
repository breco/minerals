package bullets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import utils.Animator;

public abstract class Bullet extends Sprite {
    char oriX,oriY;
    int SPD,ATK;
    public Animator animator;
    public Bullet(Animator animator, int x, int y,char oriX,char oriY,int ATK,int SPD){
        setPosition(x, y);
        this.oriX = oriX;
        this.oriY = oriY;
        this.SPD = SPD;
        this.ATK = ATK;
        this.animator = animator;
        //setSize(animator.width*2,animator.height*2);
        setSize(animator.width,animator.height);
        setX(getX()-getWidth()/2);
    }
    public void move(){
        if(oriX == 'L'){
            setX(getX()-SPD);
        }
        else if(oriX == 'R'){
            setX(getX()+SPD);
        }
        if(oriY == 'D'){
            setY(getY()-SPD);
        }
        else if(oriY == 'U'){
            setY(getY()+SPD);
        }
    }
    public void destroy(){
    }
    public void update(){
        move();
        destroy();
    }
    public void draw(SpriteBatch batch){
        //
        //super.draw(batch);

        animator.draw(this,batch);
    }
}

package bullets;

import com.badlogic.gdx.math.Rectangle;

import minerals.Mineral;
import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

public class BubbleShield extends MineralBullet {
    Mineral owner;
    private Rectangle rect;
    private TimeManager time;
    private int finishTime;
    private Bullet bullet;
    public BubbleShield(Mineral owner, Animator animator, int x, int y, char oriX, char oriY) {
        super(owner,animator, x, y, oriX, oriY,0,0);
        this.owner = owner;
        finishTime = 10;
        time = new TimeManager();
        time.start();
        setSize(128,128);

    }
    public void attack(){
        rect = getBoundingRectangle();
        for (int i = 0; i< MainGame.bullets.getBullets().size; i++){
            bullet = MainGame.bullets.getBullets().get(i);
            if(bullet instanceof MineralBullet) continue;
            if(rect.overlaps(bullet.getBoundingRectangle())){
                MainGame.bullets.remove(bullet);
            }
        }
    }
    public void move(){
        setX(owner.getX()- owner.getWidth()/2);// - owner.getWidth()/2);
        setY(owner.getY()- owner.getHeight()/2); //- owner.getHeight()/2);
    }
    public void destroy(){
        if ((int)time.getTime() == finishTime){
            MainGame.bullets.remove(this);
        }
    }
}

package bullets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

import minerals.Mineral;
import screens.MainGame;
import utils.Animator;
import utils.TimeManager;

public class MirrorShield extends MineralBullet {
    private Mineral owner;
    private TimeManager time;
    private int finishTime = 10;
    private Rectangle rect;
    private Bullet bullet;
    public MirrorShield(Mineral owner, Animator animator) {
        super(owner,animator, 1, 1, ' ', ' ', 0, 0);
        scale(2);
        this.owner = owner;
        time = new TimeManager();
        time.start();
        setX(owner.getX() - owner.getWidth()/2.5f);
        setY(owner.getY() + owner.getHeight());
    }
    public void move(){
        setX(owner.getX() - owner.getWidth()/2.5f);
        setY(owner.getY() + owner.getHeight());
    }
    public void destroy(){
        if ((int)time.getTime() == finishTime){
            MainGame.bullets.remove(this);
        }
    }
    public void attack(){
        rect = getBoundingRectangle();
        for (int i = 0; i< MainGame.bullets.getBullets().size; i++){
            bullet = MainGame.bullets.getBullets().get(i);
            if(bullet instanceof MineralBullet) continue;
            if(rect.overlaps(bullet.getBoundingRectangle())){
                MainGame.bullets.remove(bullet);
                Bullet newBullet = new MineralBullet(owner,bullet.animator,(int)bullet.getX(),(int)bullet.getY(),' ','U',bullet.ATK,bullet.SPD);
                newBullet.setColor(Color.LIGHT_GRAY);
                MainGame.bullets.add(newBullet);
                return;

            }
        }

    }


}

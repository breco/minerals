package bullets;

import com.badlogic.gdx.math.Rectangle;

import minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class RockWheel extends MineralBullet {
    private Rectangle rect;
    private Bullet bullet;
    private float grados;
    private int radio;
    private Mineral owner;
    private float BONUS_SPD = 0.025f;
    public RockWheel(Mineral owner, Animator animator, int grados) {
        //ATK = 20, SPD = 1
        super(owner,animator, 1,1, ' ', ' ', 0, 0);
        radio = 64;
        this.owner = owner;
        this.grados = grados;

        //upgrade spd
        owner.changeSPD(BONUS_SPD);
    }

    public void attack(){
        rect = getBoundingRectangle();
        for (int i = 0; i< MainGame.bullets.getBullets().size; i++){
            bullet = MainGame.bullets.getBullets().get(i);
            if(bullet instanceof MineralBullet) continue;
            if(rect.overlaps(bullet.getBoundingRectangle())){
                MainGame.bullets.remove(bullet);
                MainGame.bullets.remove(this);
                owner.changeSPD(-BONUS_SPD);
                return;

            }
        }

    }
    public void move(){
        setX(owner.getX() + owner.getWidth()/3  + (float) (radio * Math.cos(Math.toRadians(grados))));
        setY(owner.getY() + owner.getHeight()/2 + (float) (radio * Math.sin(Math.toRadians(grados))));
        grados+= 5;
        if(grados== 360) grados = 0;
    }
    public void destroy(){

    }
}


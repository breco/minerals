package bullets;

import com.artificialmemories.minerals.Initial;

import minerals.Mineral;
import enemies.Enemy;
import screens.MainGame;
import utils.Animator;

public class MineralBullet extends Bullet {
    public Mineral owner;
    public MineralBullet(Mineral owner, Animator animator, int x, int y, char oriX, char oriY, int ATK, int SPD) {
        super(animator, x, y, oriX,oriY, ATK,SPD);
        this.owner = owner;
    }
    public void update(){
        move();
        destroy();
        attack();
    }
    public void attack(){
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemy.getBoundingRectangle().contains(getBoundingRectangle())){
                enemy.getDamage(ATK);
                MainGame.bullets.remove(this);
                return;
            }
        }
    }
    public void destroy(){
        if(getY() >= Initial.WIDTH){

            MainGame.bullets.remove(this);
        }
    }
}


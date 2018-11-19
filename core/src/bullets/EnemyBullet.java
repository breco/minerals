package bullets;

import Minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class EnemyBullet extends Bullet {
    public EnemyBullet(Animator animator, int x, int y, char oriX, char oriY, int ATK, int SPD) {
        super(animator, x, y, oriX, oriY, ATK, SPD);
    }
    public void update(){
        move();
        destroy();
        attack();
    }
    public void attack(){
        for(Mineral mineral: MainGame.minerals.getMinerals()){

            if(mineral.getBoundingRectangle().contains(getBoundingRectangle())){
                mineral.getDamage(ATK);
                MainGame.bullets.remove(this);
                return;
            }
        }
    }
    public void destroy(){
        if(getY() <= 0){
            //Gdx.app.log("DELETE ENEMY BULLET","THIS");
            MainGame.bullets.remove(this);
        }
    }
}


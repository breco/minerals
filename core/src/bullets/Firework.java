package bullets;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class Firework extends MineralBullet {
    private Array<Enemy> enemiesSelected;
    private int SPD_X,SPD_Y;
    private Rectangle rect;
    public Firework(Mineral owner, Animator animator, int x, int y, float angle) {
        super(owner,animator, x, y, ' ', 'U', 2, 20);
        enemiesSelected = new Array<Enemy>();
        SPD_Y = 10;
        if (angle < 0) {
            oriX = 'R';
        }
        else if(angle > 0){
            oriX = 'L';
        }
        if(Math.abs(angle) > 0 && Math.abs(angle) <= 22.5f ){
            SPD_X = 5;
        }
        else if(Math.abs(angle) > 22.5f && Math.abs(angle) <= 45){
            SPD_X = 10;
        }
        setSize(10,60);

        animator.setRotation(angle);
        setPosition(owner.getX()+owner.getWidth()/2,owner.getY()+owner.getHeight()/2);
    }

    public void attack(){
        rect = getBoundingRectangle();
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemiesSelected.contains(enemy,false)) continue;
            if(rect.overlaps(enemy.getBoundingRectangle())){
                enemiesSelected.add(enemy);
                enemy.getDamage(ATK);
                MainGame.bullets.remove(this);
            }
        }

    }
    public void move(){
        if(oriX == 'L'){
            setX(getX()-SPD_X);
        }
        else if(oriX == 'R'){
            setX(getX()+SPD_X);
        }
        if(oriY == 'D'){
            setY(getY()-SPD_Y);
        }
        else if(oriY == 'U'){
            setY(getY()+SPD_Y);
        }
    }


}

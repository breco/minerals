package bullets;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import minerals.Mineral;
import screens.MainGame;
import utils.Animator;

public class FireBall extends MineralBullet {
    private Array<Enemy> enemiesSelected;
    private int SPD_X,SPD_Y;
    private Rectangle rect;
    public FireBall(Mineral owner, Animator animator, int x, int y, char oriX, char oriY) {
        super(owner,animator, x, y, oriX, oriY, 50, 20);
        enemiesSelected = new Array<Enemy>();
        SPD_X = 5;
        SPD_Y = 10;
        setSize(60,60);
    }

    public void attack(){
        rect = getBoundingRectangle();
        for(Enemy enemy : MainGame.enemies.getEnemies()){
            if(enemiesSelected.contains(enemy,false)) continue;
            if(rect.overlaps(enemy.getBoundingRectangle())){
                enemiesSelected.add(enemy);
                enemy.getDamage(ATK);
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

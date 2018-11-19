package bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Bullets {
    public Array<Bullet> bullets;
    public Bullets() {
        bullets = new Array<Bullet>();


    }
    public void draw(SpriteBatch batch){
        for(Bullet bullet : bullets){
            bullet.draw(batch);
        }
    }
    public void update(){
        for(Bullet bullet : bullets){
            bullet.update();

        }
    }
    public void remove(Bullet bullet){
        bullets.removeValue(bullet, false);
    }
    public Array<Bullet> getBullets(){
        return bullets;
    }
    public void add(Bullet bullet){
        bullets.add(bullet);
    }
}

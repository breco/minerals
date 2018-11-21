package backgrounds;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Background {
    Texture bg;
    int cont;
    float bg1,bg2;
    public Background(Texture texture){
        bg = texture;
        bg1 = -Initial.WIDTH/4;
        bg2 = -Initial.WIDTH/4+Initial.WIDTH;
        cont = 0;
    }
    public void update(){
        bg1-=2;
        bg2-=2;
        /*if(bg1 + Initial.WIDTH <= -Initial.WIDTH/4){
            bg1 = -Initial.WIDTH/4+Initial .WIDTH;
        }
        if(bg2 + Initial.WIDTH <= -Initial.WIDTH/4){
            bg2 = -Initial.WIDTH/4+Initial .WIDTH;
        }*/
        if(bg1 <= - Initial.WIDTH){
            bg1 = Initial.WIDTH;
        }
        if(bg2 <= -Initial.WIDTH){
            bg2 = Initial.WIDTH;
        }
    }
    public void draw(SpriteBatch batch){

        batch.draw(bg,0,bg2,Initial.HEIGHT,Initial.WIDTH); // 2
        batch.draw(bg,0,bg1,Initial.HEIGHT,Initial.WIDTH); // 1

    }
}

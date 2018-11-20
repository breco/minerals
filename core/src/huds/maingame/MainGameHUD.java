package huds.Initial;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import Minerals.Minerals;
import items.Item;
import items.Items;
import screens.MainGame;
import skills.Skills;

public class MainGameHUD {
    Minerals minerals;
    Items items;
    Skills skills;
    MainGame game;

    private Texture black,gray,purple;


    //private My_Button pauseButton;
    public MainGameHUD(MainGame game){
        this.game = game;
        this.minerals = game.minerals;
        this.items = game.items;
        this.skills = game.skills;


        black = new Texture(Gdx.files.internal("huds/mainGame/black.png")); // Cambiar direccion de archivo black.png a carpeta colors
        gray = new Texture(Gdx.files.internal("colors/gray.png"));
        purple = new Texture(Gdx.files.internal("colors/purple.png"));

        int i=0;
        /*for(Item item : items.getItems()){
            item.setPosition(Initial.WIDTH/10.5f+i*Initial.WIDTH/3,Initial.HEIGHT/24);
            i++;
        }*/
        //pauseButton = new My_Button(new Texture(Gdx.files.internal("buttons/pause_button.png")));
        //pauseButton.scale(0.6f);
        //pauseButton.setPosition(Initial.WIDTH-pauseButton.getWidth()*1.1f,Initial.HEIGHT-pauseButton.getHeight()*1.1f);
    }
    public void update(){

    }
    public void draw(SpriteBatch batch){



        //draw item section
        batch.draw(black,0,0,Initial.HEIGHT,Initial.WIDTH/game.itemSection);
        batch.draw(gray, 0, Initial.WIDTH/game.itemSection - 5, Initial.HEIGHT, 15 );
        batch.draw(purple, 0, Initial.WIDTH/game.itemSection - 5, game.PERCENT_PP * Initial.HEIGHT/100f, 15 );

        skills.draw(batch);
        /*for(int i =0;i<3;i++){

            items.getItems().get(i).draw(batch);
            if(items.getItems().get(i).used) batch.draw(cross,Initial.HEIGHT/10.5f+i*Initial.HEIGHT/3,Initial.WIDTH/24,100,100);

        }
        for(Item item : items.getItems()){
            if(item.touched || item.touched2) item.draw(batch);
        }*/
        //pauseButton.draw(batch);
    }
    public void input(Vector3 vec){
        vec.set(vec.x, Initial.HEIGHT - vec.y, 0);
        /*if(pauseButton.getBoundingRectangle().contains(vec.x,vec.y)){
            game.pause();
        }
        */

    }
}


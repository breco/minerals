package huds.maingame;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import items.Item;
import items.Items;
import minerals.Mineral;
import minerals.Minerals;
import screens.MainGame;
import skills.Skills;

public class MainGameHUD {
    Minerals minerals;
    Items items;
    Skills skills;
    MainGame game;

    private Texture black,gray,purple;
    private Texture skillSlots,itemSlots;

    public Texture HPContainerLeft = new Texture(Gdx.files.internal("huds/mainGame/bar left.png"));
    public Texture HPContainerRight = new Texture(Gdx.files.internal("huds/mainGame/bar right.png"));
    public Texture HPContainerCenter = new Texture(Gdx.files.internal("huds/mainGame/bar center.png"));
    public Texture blue = new Texture(Gdx.files.internal("huds/mainGame/pp center blue.png"));

    public int baseItemX = Initial.HEIGHT - 130;
    public float itemX = baseItemX;


    //private My_Button pauseButton;
    public MainGameHUD(MainGame game){
        this.game = game;
        this.minerals = game.minerals;
        this.items = game.items;
        this.skills = game.skills;


        black = new Texture(Gdx.files.internal("huds/mainGame/black.png")); // Cambiar direccion de archivo black.png a carpeta colors
        gray = new Texture(Gdx.files.internal("colors/gray.png"));
        purple = new Texture(Gdx.files.internal("colors/purple.png"));
        skillSlots = new Texture(Gdx.files.internal(("huds/mainGame/skillSlots.png")));
        itemSlots = new Texture(Gdx.files.internal(("huds/mainGame/itemSlots.png")));
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
        Mineral mineral = minerals.getMostRight();
        if(mineral.getX() < baseItemX && itemX > baseItemX){
            itemX = baseItemX;
        }
        if(mineral.getX() +  mineral.getWidth()*1.25f >= itemX ){
            itemX = mineral.getX() +  mineral.getWidth()*1.25f;

        } else if (mineral.getX() +  mineral.getWidth()*1.25f < itemX && mineral.getX() +  mineral.getWidth()*1.25f > baseItemX ) {
            itemX = mineral.getX() +  mineral.getWidth()*1.25f;

        }
        for(Item item : items.getItems()){
            item.setX(itemX + 20);
        }

    }
    public void draw(SpriteBatch batch){



        //draw item section
        batch.draw(black,0,0,Initial.HEIGHT,Initial.WIDTH/game.itemSection);
        batch.draw(skillSlots,45,0,Initial.HEIGHT*7/8f,Initial.WIDTH/game.itemSection);
        batch.draw(itemSlots, itemX,Initial.WIDTH/2 - ((50+10)*3), 90, 250);


        //batch.draw(gray, 0, Initial.WIDTH/game.itemSection - 5, Initial.HEIGHT, 15 );
        //batch.draw(purple, 0, Initial.WIDTH/game.itemSection - 5, game.PERCENT_PP * Initial.HEIGHT/100f, 15 );

        batch.draw(HPContainerLeft,40, Initial.WIDTH/game.itemSection - HPContainerLeft.getHeight()/2, HPContainerLeft.getWidth()*2,HPContainerLeft.getHeight()*2);
        batch.draw(HPContainerCenter, 40 + HPContainerLeft.getWidth(), Initial.WIDTH/game.itemSection - HPContainerLeft.getHeight()/2, Initial.HEIGHT - HPContainerLeft.getWidth()*6, HPContainerCenter.getHeight()*2);
        batch.draw(HPContainerRight, 45 + Initial.HEIGHT - HPContainerLeft.getWidth()*5.5f, Initial.WIDTH/game.itemSection - HPContainerLeft.getHeight()/2, HPContainerLeft.getWidth()*2,HPContainerLeft.getHeight()*2);
        batch.draw(blue, 40 + 18,  Initial.WIDTH/game.itemSection + 6 - HPContainerLeft.getHeight()/2, (Initial.HEIGHT - HPContainerLeft.getWidth()*2 - 67)*game.PERCENT_PP/100f, blue.getHeight()*2);




        skills.draw(batch);
        items.draw(batch);

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


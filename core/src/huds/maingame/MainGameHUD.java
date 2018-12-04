package huds.maingame;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import huds.menu.BasicButton;
import items.Item;
import items.Items;
import minerals.Mineral;
import minerals.Minerals;
import screens.MainGame;
import screens.WorldScreen;
import skills.Skills;

public class MainGameHUD {
    Minerals minerals;
    Items items;
    Skills skills;
    MainGame game;

    private Texture black;
    private Texture skillSlots,itemSlots;

    public Texture HPContainerLeft = new Texture(Gdx.files.internal("huds/mainGame/bar left.png"));
    public Texture HPContainerRight = new Texture(Gdx.files.internal("huds/mainGame/bar right.png"));
    public Texture HPContainerCenter = new Texture(Gdx.files.internal("huds/mainGame/bar center.png"));
    public Texture blue = new Texture(Gdx.files.internal("huds/mainGame/pp center blue.png"));

    public int baseItemX = Initial.HEIGHT - 90;
    public float itemX = baseItemX;



    private BasicButton pauseButton, resumeButton, quitButton;
    private BasicButton winButton, loseButton;
    public MainGameHUD(MainGame game){
        this.game = game;
        this.minerals = game.minerals;
        this.items = game.items;
        this.skills = game.skills;


        black = new Texture(Gdx.files.internal("huds/mainGame/black.png")); // Cambiar direccion de archivo black.png a carpeta colors
        skillSlots = new Texture(Gdx.files.internal(("huds/mainGame/skillSlots.png")));
        itemSlots = new Texture(Gdx.files.internal(("huds/mainGame/itemSlots.png")));


        pauseButton = new BasicButton(new Texture(Gdx.files.internal("huds/mainGame/menu button.png")),new Texture(Gdx.files.internal("huds/mainGame/menu button pressed.png")), 0,0);

        //pauseButton.setBounds(Initial.HEIGHT - pauseButton.getWidth()*5,Initial.WIDTH - pauseButton.getHeight()*10, 88,88);
        pauseButton.setBounds(Initial.HEIGHT - 88,Initial.WIDTH - 88, 88,88);

        resumeButton = new BasicButton(new Texture(Gdx.files.internal("huds/menu/square button.png")),new Texture(Gdx.files.internal("huds/menu/square button pressed.png")), 0,0);
        resumeButton.setScale(1.5f);
        resumeButton.setSize(resumeButton.getWidth()*3,resumeButton.getHeight());
        resumeButton.setPosition(Initial.HEIGHT/2- resumeButton.getWidth()/2, Initial.WIDTH/2 - resumeButton.getHeight()/2);
        resumeButton.setText("resume");
        resumeButton.setShow(false);

        quitButton = new BasicButton(new Texture(Gdx.files.internal("huds/menu/square button.png")),new Texture(Gdx.files.internal("huds/menu/square button pressed.png")), Initial.HEIGHT/2, Initial.WIDTH/2);
        quitButton.setScale(1.5f);
        quitButton.setSize(quitButton.getWidth()*3,quitButton.getHeight());
        quitButton.setPosition(Initial.HEIGHT/2- resumeButton.getWidth()/2, Initial.WIDTH/2 - resumeButton.getHeight()*5/2);
        quitButton.setText("  quit");
        quitButton.setShow(false);

        winButton = new BasicButton(new Texture(Gdx.files.internal("huds/menu/square button.png")),new Texture(Gdx.files.internal("huds/menu/square button pressed.png")), 0,0);
        winButton.setScale(1.5f);
        winButton.setSize(resumeButton.getWidth(),resumeButton.getHeight());
        winButton.setPosition(Initial.HEIGHT/2- resumeButton.getWidth()/2, Initial.WIDTH/2 - resumeButton.getHeight()/2);
        winButton.setText("Victory");
        winButton.setShow(false);

        loseButton = new BasicButton(new Texture(Gdx.files.internal("huds/menu/square button.png")),new Texture(Gdx.files.internal("huds/menu/square button pressed.png")), 0,0);
        loseButton.setScale(1.5f);
        loseButton.setSize(resumeButton.getWidth(),resumeButton.getHeight());
        loseButton.setPosition(Initial.HEIGHT/2- resumeButton.getWidth()/2, Initial.WIDTH/2 - resumeButton.getHeight()/2);
        loseButton.setText("Failure");
        loseButton.setShow(false);
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

        if(game.enemies.enemies.size == game.enemies.deadEnemies.size + game.enemies.escapedEnemies.size){
            winButton.setShow(true);
        }
        if(game.minerals.getMinerals().size == 0){
            loseButton.setShow(true);
        }

    }
    public void draw(SpriteBatch batch){



        //draw item section
        batch.draw(black,0,0,Initial.HEIGHT,Initial.WIDTH/game.itemSection);
        batch.draw(skillSlots,45,0,Initial.HEIGHT*7/8f,Initial.WIDTH/game.itemSection);
        batch.draw(itemSlots, itemX,Initial.WIDTH/2 - ((50+10)*3), 90, 250);


        batch.draw(HPContainerLeft,40, Initial.WIDTH/game.itemSection - HPContainerLeft.getHeight()/2, HPContainerLeft.getWidth()*2,HPContainerLeft.getHeight()*2);
        batch.draw(HPContainerCenter, 40 + HPContainerLeft.getWidth(), Initial.WIDTH/game.itemSection - HPContainerLeft.getHeight()/2, Initial.HEIGHT - HPContainerLeft.getWidth()*6, HPContainerCenter.getHeight()*2);
        batch.draw(HPContainerRight, 45 + Initial.HEIGHT - HPContainerLeft.getWidth()*5.5f, Initial.WIDTH/game.itemSection - HPContainerLeft.getHeight()/2, HPContainerLeft.getWidth()*2,HPContainerLeft.getHeight()*2);
        batch.draw(blue, 40 + 18,  Initial.WIDTH/game.itemSection + 6 - HPContainerLeft.getHeight()/2, (Initial.HEIGHT - HPContainerLeft.getWidth()*2 - 67)*game.PERCENT_PP/100f, blue.getHeight()*2);




        skills.draw(batch);
        items.draw(batch);

        pauseButton.draw(batch);
        resumeButton.draw(batch);
        quitButton.draw(batch);

        winButton.draw(batch);
        loseButton.draw(batch);
    }
    public void touchUp(MainGame.State state,Vector3 vec){
        switch(state){
            case RUN:
                if(pauseButton.touched && pauseButton.getBoundingRectangle().contains(vec.x,vec.y)){
                    game.pause();
                    resumeButton.setShow(true);
                    quitButton.setShow(true);
                }
                break;
            case PAUSE:
                if(resumeButton.touched && resumeButton.getBoundingRectangle().contains(vec.x,vec.y)){
                    game.resume();
                    resumeButton.setShow(false);
                    quitButton.setShow(false);
                }
                if(quitButton.touched && quitButton.getBoundingRectangle().contains(vec.x,vec.y)){
                    game.game.setScreen(new WorldScreen(game.game));
                    game.dispose();
                }
                break;
            case WIN:
                if(winButton.touched && winButton.getBoundingRectangle().contains(vec.x,vec.y)){
                    game.game.setScreen(new WorldScreen(game.game));
                    game.dispose();
                }

                break;
            case LOSE:
                if(loseButton.touched && loseButton.getBoundingRectangle().contains(vec.x,vec.y)){
                    game.game.setScreen(new WorldScreen(game.game));
                    game.dispose();
                }
                break;
        }


        pauseButton.touchUp();
        resumeButton.touchUp();
        quitButton.touchUp();
        winButton.touchUp();
        loseButton.touchUp();
    }
    public void input(MainGame.State state, Vector3 vec){
        switch(state){
            case RUN:

                pauseButton.input(vec);
                break;
            case PAUSE:
                resumeButton.input(vec);
                quitButton.input(vec);
                break;
            case WIN:
                winButton.input(vec);

                break;
            case LOSE:
                loseButton.input(vec);
                break;
        }



    }
}


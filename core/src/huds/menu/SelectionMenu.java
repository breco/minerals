package huds.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import screens.WorldScreen;
import utils.MyGestures;

public class SelectionMenu extends BasicMenu {


    //Array<MineralButton> minerals;
    Array<ItemSelectButton> items;

    //BasicButton mineral;
    //BasicButton item;
    BasicButton ok;
    Texture normal,pressed;
    Sprite slots;
    Sprite slotSelected;
    Sprite selected;
    String selectedname;
    Sprite touched;
    String touchedName;
    boolean moving = false;

    public SelectionMenu(WorldScreen screen) {
        super(screen);

        slots = new Sprite(new Texture(Gdx.files.internal("huds/menu/selection slots.png")));
        slots.setPosition(menuLU.getX(),menuLC.getY()+menuLU.getHeight()*2);
        slots.setSize(menuLU.getWidth()+menuCD.getWidth()+menuRU.getWidth(),menuLU.getHeight()*5);
        items = new Array<ItemSelectButton>();
        items.add(new ItemSelectButton(new Texture(Gdx.files.internal("items/mirror.png")),"mirror","Protects 1 of your minerals reflecting bullets to your enemies during 15 seconds.",0,0));
        items.add(new ItemSelectButton(new Texture(Gdx.files.internal("items/grapes.png")),"fruit","Heals 10 damage from 1 of your minerals.",1,0));
        items.add(new ItemSelectButton(new Texture(Gdx.files.internal("items/gemstone.png")),"gemstone","Adds 15 PP to the PP bar when used.",2,0));
        items.add(new ItemSelectButton(new Texture(Gdx.files.internal("items/star.png")),"speed star","Temporarily increases attack speed from 1 of your minerals.",0,1));


        for(ItemSelectButton item : items){
            item.setParent(this);
        }

        slotSelected = new Sprite(new Texture(Gdx.files.internal("huds/menu/level slot.png")));
        slotSelected.setSize(slotSelected.getWidth() * 3, slotSelected.getHeight() * 3);
        slotSelected.setPosition(menuCD.getX()+menuLD.getWidth()*0.6f,menuCD.getY()+menuCD.getHeight()*1.1f);

        touched = new Sprite(new Texture(Gdx.files.internal("items/grapes.png")));


        normal = new Texture(Gdx.files.internal("huds/menu/square button.png"));
        pressed = new Texture(Gdx.files.internal("huds/menu/square button pressed.png"));


        backbutton.setPosition(menuLU.getX() + backbutton.getWidth(),backbutton.getY());

        ok = new BasicButton(normal,pressed,backbutton.getX()+backbutton.getWidth()*2,backbutton.getY());
        ok.setSize(backbutton.getWidth(),backbutton.getHeight());
        ok.setText(" Ok");

    }



    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);
        slots.draw(batch);
        slotSelected.draw(batch);
        selected.draw(batch);
        for(ItemSelectButton item : items){
            item.draw(batch);
        }
        ok.draw(batch);

        if(moving){
            batch.draw(touched.getTexture(),touched.getX(),touched.getY()+100,touched.getWidth(), touched.getHeight());
            //touched.draw(batch);
        }

    }

    public void update(){
        move();
    }

    public void move(){

        if(moving){
            touched.setX(touched.getX() - MyGestures.diff.x);
            touched.setY(touched.getY() - MyGestures.diff.y);

        }
    }
    public void input(Vector3 vec){
        super.input(vec);
        for(ItemSelectButton item : items){
            item.input(vec);
            if(item.isTouched()){
                moving = true;
                touched.setTexture(item.item.getTexture());
                touched.setSize(item.item.getWidth(),item.item.getHeight());
                touched.setPosition(item.item.getX(),item.item.getY());
                Gdx.app.log("INITIAL POSITION",touched.getX()+","+touched.getY());
                touchedName = item.name;
            }
        }

        ok.input(vec);
    }
    public void touchUp(Vector3 vec){
        if(moving){
            moving = false;
            touched.setY(touched.getY()+100);
            if(touched.getBoundingRectangle().overlaps(selected.getBoundingRectangle())){
                selected.setTexture(touched.getTexture());
                selectedname = touchedName;
            }
        }
        if(closebutton.touched && closebutton.contains(vec)){
            show = false;
            screen.inputState = WorldScreen.InputState.WORLD;
            screen.mineralmenu.reset();
        }

        if(backbutton.touched && backbutton.contains(vec) ){
            show = false;
            screen.inputState = WorldScreen.InputState.MINERALS;
            screen.mineralmenu.show(planet);


        }

        if(ok.touched && ok.contains(vec)){
            screen.inputState = WorldScreen.InputState.MINERALS;
            show = false;
            screen.mineralmenu.show(planet);
        }


        for(ItemSelectButton item : items){
            item.touchUp(vec);
        }

        backbutton.touchUp();
        closebutton.touchUp();
        ok.touchUp();

    }

    public void setSelectedItem(String name, Sprite item){
        selected = new Sprite(item.getTexture());
        selected.setSize(96,96);
        selected.setPosition(slotSelected.getX()+slotSelected.getWidth()*0.25f,slotSelected.getY()+slotSelected.getHeight()*0.25f);
        selectedname = name;
    }

}

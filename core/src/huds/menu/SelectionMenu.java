package huds.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
    String selectedvalue;
    String selectedname;
    String selectedEffect;
    Sprite touched;
    String touchedValue;
    String touchedName;
    String touchedEffect;
    boolean moving = false;
    ItemButton button;
    public SelectionMenu(WorldScreen screen) {
        super(screen);

        slots = new Sprite(new Texture(Gdx.files.internal("huds/menu/selection slots.png")));
        slots.setPosition(menuLU.getX(),menuLC.getY()+menuLU.getHeight()*2);
        slots.setSize(menuLU.getWidth()+menuCD.getWidth()+menuRU.getWidth(),menuLU.getHeight()*5);
        items = new Array<ItemSelectButton>();


        //CREATE READING CLASS
        FileHandle file = Gdx.files.internal("data/items.txt");
        String[] items = file.readString().split("\n");
        String[] itemdata;
        int x = 0, y = 0;
        for(String item : items){
            itemdata = item.split("/");
            this.items.add(new ItemSelectButton(new Texture(Gdx.files.internal("items/"+itemdata[2]+".png")),itemdata[0],itemdata[2],itemdata[3],x, y));
            x++;
            if(x >= 3){
                x = 0;
                y++;
            }
        }


        for(ItemSelectButton item : this.items){
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
                touchedValue = item.value;
                touchedName = item.name;
                touchedEffect = item.originalDescription;

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
                selectedvalue = touchedValue;
                selectedname = touchedName;
                selectedEffect = touchedEffect;
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
            Gdx.app.log("SELECTEDNAME",selectedname+"");
            Gdx.app.log("SELECTEDEFFECT",""+selectedEffect);
            button.updateItem(selectedvalue,selectedname,selectedEffect);
        }


        for(ItemSelectButton item : items){
            item.touchUp(vec);
        }

        backbutton.touchUp();
        closebutton.touchUp();
        ok.touchUp();

    }

    public void setSelectedItem(ItemButton button){
        this.button = button;
        selected = new Sprite(button.item.getTexture());
        selected.setSize(96,96);
        selected.setPosition(slotSelected.getX()+slotSelected.getWidth()*0.25f,slotSelected.getY()+slotSelected.getHeight()*0.25f);
        selectedname = button.name;
        selectedvalue = button.value;
    }




}

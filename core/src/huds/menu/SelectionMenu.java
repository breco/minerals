package huds.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import items.ItemLoader;
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
    ItemSelectButton touchedItem, selectedItem;
    boolean moving = false;
    ItemButton button;
    public SelectionMenu(WorldScreen screen) {
        super(screen);

        slots = new Sprite(new Texture(Gdx.files.internal("huds/menu/selection slots.png")));
        slots.setPosition(menuLU.getX(),menuLC.getY()+menuLU.getHeight()*2);
        slots.setSize(menuLU.getWidth()+menuCD.getWidth()+menuRU.getWidth(),menuLU.getHeight()*5);
        items = new Array<ItemSelectButton>();


        //CREATE READING CLASS
        FileHandle file = Gdx.files.internal("data/owneditems.txt");
        String[] items = file.readString().split("\n");
        String[] owneditemdata;
        String[] itemdata;
        int x = 0, y = 0;
        for(String item : items){
            owneditemdata = item.split("/");

            itemdata = ItemLoader.getItemData(owneditemdata[0]);
            this.items.add(new ItemSelectButton(new Texture(Gdx.files.internal("items/"+itemdata[2]+".png")),itemdata[0],itemdata[2],itemdata[3], Integer.parseInt(owneditemdata[1]),x,y));
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


        //backbutton.setPosition(menuLU.getX() + backbutton.getWidth(),backbutton.getY());
        backbutton.setPosition(1000,backbutton.getY());

        ok = new BasicButton(normal,pressed,menuLD.getX()+backbutton.getWidth()*2,backbutton.getY());
        ok.setSize(backbutton.getWidth(),backbutton.getHeight());
        ok.setText(" Ok");

        String selecteditems = screen.previewmenu.getSelectedItems();
        for (String itemId : selecteditems.split(",")){
            for(ItemSelectButton item : this.items){
                if (item.getValue().equals(itemId)){
                    item.setSelected();
                    item.amount--;
                }
            }
        }

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
            batch.draw(touched.getTexture(),touched.getX(),touched.getY()+50,touched.getWidth(), touched.getHeight());

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
                touchedItem = item;

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
                touchedItem.setSelected();
                touchedItem.amount--;
                selectedItem.amount++;
                selectedItem.setUnselected();
                selectedItem = touchedItem;
                touchedItem = null;

            }
        }
        if(closebutton.touched && closebutton.contains(vec)){
            show = false;
            screen.inputState = WorldScreen.InputState.WORLD;
            screen.previewmenu.reset();
        }

        if(backbutton.touched && backbutton.contains(vec) ){
            show = false;
            screen.inputState = WorldScreen.InputState.MINERALS;
            screen.previewmenu.show(planet);


        }

        if(ok.touched && ok.contains(vec)){
            screen.inputState = WorldScreen.InputState.MINERALS;
            show = false;
            screen.previewmenu.show(planet);

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
        selectedvalue = button.getValue();
        selectedEffect = button.effect;
        for(ItemSelectButton item : items){
            if(item.value.equals(button.getValue())){
                item.setSelected();
                selectedItem = item;
                break;
            }
        }
    }




}

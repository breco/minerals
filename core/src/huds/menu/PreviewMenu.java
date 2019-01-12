package huds.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import screens.WorldScreen;

public class PreviewMenu extends BasicMenu {

    Array<MineralButton> minerals;
    Array<ItemButton> items;
    public Sprite delimiter;
    BasicButton mineral;
    BasicButton item;
    BasicButton go;
    Texture normal,pressed;
    boolean showmineral = true;
    public PreviewMenu(WorldScreen screen) {
        super(screen);

        minerals = new Array<MineralButton>();
        minerals.add(new MineralButton("terro",0, 15, 2, 2, 1, "rock wheel"));
        minerals.add(new MineralButton("redmi",1, 12,2,4,1, "fireworks"));
        minerals.add(new MineralButton("pyro",2,10,3,2,1, "fire balls"));

        items = new Array<ItemButton>();
        loadItems();

        delimiter = new Sprite(new Texture(Gdx.files.internal("huds/menu/delimiter.png")));
        delimiter.setSize(delimiter.getWidth()*5,delimiter.getHeight()*2);

        normal = new Texture(Gdx.files.internal("huds/menu/square button.png"));
        pressed = new Texture(Gdx.files.internal("huds/menu/square button pressed.png"));
        mineral = new BasicButton(pressed,pressed, menuLU.getX() + 50, menuLU.getY() - 15);
        mineral.setText("    Minerals");
        //mineral.setSize(menuLU.getWidth()*2.4f, menuLU.getHeight());
        mineral.setSize(menuLU.getWidth()*4, menuLU.getHeight()*3/4);
        mineral.forceTexture(pressed);
        mineral.setColor(Color.GOLD);
        item = new BasicButton(pressed,pressed, mineral.getX() , menuLC.getY() + menuLC.getHeight()/2 - 60);
        item.setText("       Items");
        item.setSize(menuLU.getWidth()*4, menuLU.getHeight()*3/4);
        item.forceTexture(pressed);
        item.setColor(Color.GOLD);

        backbutton.setPosition(menuLU.getX() + backbutton.getWidth(),backbutton.getY());

        go = new BasicButton(normal,pressed,backbutton.getX()+backbutton.getWidth()*2,backbutton.getY());
        go.setSize(backbutton.getWidth(),backbutton.getHeight());
        go.setText(" Go");


        for(ItemButton item : this.items){
            item.setParent(this);
        }


    }


    public void loadItems(){

        items.add(new ItemButton("1", 0,"grapes","Heals 10 damage from 1 of your minerals."));
        items.add(new ItemButton("2", 1,"mirror","Protects 1 of your minerals reflecting bullets to your enemies during 15 seconds."));
        items.add(new ItemButton("3", 2,"gemstone","Adds 15 PP to the PP bar when used."));
    }



    public void loadMinerals() {

        class JsonMineral {
            public String name, status;
            public int stars;
        }

        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("levels/planets/"+ Initial.prefs.getString("load_planet")+".json"));

        JsonValue levels = base.get("levels");
        JsonMineral t;
        int i = 0;
        while(levels.get(i) != null){
            t = json.fromJson(JsonMineral.class, levels.get(i).toString());
            //this.levels.add(new LevelButton(i, t.name,t.stars));
            i++;
        }


    }


    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);
            for(MineralButton mineral : minerals){
                mineral.draw(batch);
                //batch.draw(delimiter.getTexture(),menuLU.getX() + 25,mineral.slot.getY()- 30, delimiter.getWidth()*2.9f,delimiter.getHeight());

            }
            for(ItemButton item : items){
                item.draw(batch);
                //batch.draw(delimiter.getTexture(),menuLU.getX() + 25,item.slot.getY()- 30, delimiter.getWidth()*2.9f,delimiter.getHeight());
            }
        mineral.draw(batch);
        item.draw(batch);
        go.draw(batch);

    }
    public void input(Vector3 vec){
        super.input(vec);
            for(MineralButton button : minerals){
                button.input(vec);
            }

            for(ItemButton button : items){
                button.input(vec);
            }

        go.input(vec);
    }
    public void touchUp(Vector3 vec){

        if(closebutton.touched && closebutton.contains(vec)){
            show = false;
            screen.inputState = WorldScreen.InputState.WORLD;
            reset();
        }

        if(backbutton.touched && backbutton.contains(vec) ){
            show = false;
            screen.inputState = WorldScreen.InputState.MENU;
            screen.levelmenu.show(planet);

        }

        if(go.touched && go.contains(vec)){
            screen.inputState = WorldScreen.InputState.GO;
        }

        /*if(showmineral && item.touched && item.contains(vec)){
            showmineral = false;
            item.forceTexture(pressed);
            mineral.forceTexture(normal);

        }
        if(!showmineral && mineral.touched && mineral.contains(vec)){
            showmineral = true;
            item.forceTexture(normal);
            mineral.forceTexture(pressed);

        }*/



        for(MineralButton button : minerals){
            if(button.getBoundingRectangle().contains(vec.x,vec.y)){
                //
            }
            button.touchUp();

        }



        for(ItemButton button : items){
            if(button.getBoundingRectangle().contains(vec.x,vec.y)){
                show = false;
                screen.inputState = WorldScreen.InputState.SELECTION;
                screen.selectionmenu.show(planet);
                screen.selectionmenu.show("Select item");
                screen.selectionmenu.setSelectedItem(button);
            }
            button.touchUp();

        }

        mineral.touchUp();
        item.touchUp();
        backbutton.touchUp();
        closebutton.touchUp();
        go.touchUp();
    }

    public void reset(){
        showmineral = true;
        item.forceTexture(normal);
        mineral.forceTexture(pressed);
    }

    public String getSelectedItems(){
        String selecteditems = "";
        for(ItemButton item : items){
            selecteditems += item.getValue() + ",";

        }
        return selecteditems;
    }

}
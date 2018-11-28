package huds.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import screens.WorldScreen;

public class MineralMenu extends BasicMenu {

    Array<MineralButton> minerals;
    public Sprite delimiter;
    BasicButton mineral;
    BasicButton item;
    BasicButton go;
    Texture normal,pressed;
    boolean showmineral = true;
    public MineralMenu(WorldScreen screen) {
        super(screen);

        minerals = new Array<MineralButton>();
        minerals.add(new MineralButton("terro",0, 15, 2, 2, 1, "rock wheel"));
        minerals.add(new MineralButton("aqualis",1, 12,2,4,1, "bubble shield"));
        minerals.add(new MineralButton("pyro",2,10,3,2,1, "fire balls"));

        delimiter = new Sprite(new Texture(Gdx.files.internal("huds/menu/delimiter.png")));
        delimiter.setSize(delimiter.getWidth()*5,delimiter.getHeight()*2);

        normal = new Texture(Gdx.files.internal("huds/menu/square button.png"));
        pressed = new Texture(Gdx.files.internal("huds/menu/square button pressed.png"));
        mineral = new BasicButton(pressed,pressed, menuLU.getX() + 10, menuLU.getY() - 15);
        mineral.setText("Minerals");
        mineral.setSize(menuLU.getWidth()*2.4f, menuLU.getHeight());
        mineral.forceTexture(pressed);

        item = new BasicButton(normal,pressed, mineral.getX() + mineral.getWidth(), mineral.getY());
        item.setText("  Items");
        item.setSize(menuLU.getWidth()*2.4f, menuLU.getHeight());
        item.forceTexture(normal);

        backbutton.setPosition(menuLU.getX() + backbutton.getWidth(),backbutton.getY());

        go = new BasicButton(normal,pressed,backbutton.getX()+backbutton.getWidth()*2,backbutton.getY());
        go.setSize(backbutton.getWidth(),backbutton.getHeight());
        go.setText(" Go");

    }
    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);
        mineral.draw(batch);
        item.draw(batch);
        go.draw(batch);
        for(MineralButton mineral : minerals){
            mineral.draw(batch);
            batch.draw(delimiter.getTexture(),menuLU.getX() + 25,mineral.slot.getY()- 30, delimiter.getWidth()*2.9f,delimiter.getHeight());

        }
    }
    public void input(Vector3 vec){
        super.input(vec);
        for(MineralButton button : minerals){
            button.input(vec);
        }
        mineral.input(vec);
        item.input(vec);
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

        if(showmineral && item.touched && item.contains(vec)){
            showmineral = false;
            item.forceTexture(pressed);
            mineral.forceTexture(normal);
            Gdx.app.log("Changing to item","o0o");
        }
        if(!showmineral && mineral.touched && mineral.contains(vec)){
            showmineral = true;
            item.forceTexture(normal);
            mineral.forceTexture(pressed);
            Gdx.app.log("changint to mineral","o.ó");
        }



        for(MineralButton button : minerals){
            if(button.getBoundingRectangle().contains(vec.x,vec.y)){
                //show = false;
                //screen.inputState = WorldScreen.InputState.MINERALS;
                //level = button.name;

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

}
package huds.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import screens.WorldScreen;

public class MineralMenu extends BasicMenu {

    Array<MineralButton> minerals;

    public MineralMenu(WorldScreen screen) {
        super(screen);
        minerals = new Array<MineralButton>();
        for(int i = 0; i < 3; i++){
            minerals.add(new MineralButton(i));
        }

    }
    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);

        for(MineralButton mineral : minerals){
            mineral.draw(batch);
            //batch.draw(delimiter.getTexture(),menuLU.getX() + 25,level.slot.getY()- 7.5f, delimiter.getWidth()*2.9f,delimiter.getHeight());

        }
    }
    public void input(Vector3 vec){
        super.input(vec);
        for(MineralButton button : minerals){
            button.input(vec);
        }
    }
    public void touchUp(Vector3 vec){
        super.touchUp(vec);
        for(MineralButton button : minerals){
            if(button.getBoundingRectangle().contains(vec.x,vec.y)){
                //show = false;
                //screen.inputState = WorldScreen.InputState.MINERALS;
                //level = button.name;
            }
            button.touchUp();

        }

    }



}

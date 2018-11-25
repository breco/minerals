package huds.maingame.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import screens.WorldScreen;

public class LevelMenu extends BasicMenu{
    public LevelButton first;
    Array<LevelButton> levels;
    public LevelMenu(WorldScreen screen) {
        super(screen);
        first = new LevelButton(1,1,2);
        levels = new  Array<LevelButton>();
        levels.add(new LevelButton(0,1,3));
        levels.add(new LevelButton(1,2,1));
        levels.add(new LevelButton(2,3,2));
        levels.add(new LevelButton(3,4,1));
        levels.add(new LevelButton(4,5,0));
    }
    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);
        for(LevelButton level : levels){
            level.draw(batch);
        }
    }
}

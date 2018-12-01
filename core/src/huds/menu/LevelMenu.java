package huds.menu;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import screens.WorldScreen;

public class LevelMenu extends BasicMenu {

    Array<LevelButton> levels;
    public Sprite delimiter;
    int i;
    public String level;

    public LevelMenu(WorldScreen screen) {
        super(screen);
        levels = new  Array<LevelButton>();
        delimiter = new Sprite(new Texture(Gdx.files.internal("huds/menu/delimiter.png")));
        delimiter.setSize(delimiter.getWidth()*5,delimiter.getHeight()*2);


    }

    public static class JsonPlanet {
        public String name, status;
        public int stars;
    }

    public void loadPlanet() {

        JsonReader reader = new JsonReader();
        Json json = new Json();
        JsonValue base = reader.parse(Gdx.files.internal("levels/planets/"+Initial.prefs.getString("load_planet")+".json"));

        JsonValue levels = base.get("levels");
        JsonPlanet t;
        int i = 0;
        while(levels.get(i) != null){
            t = json.fromJson(JsonPlanet.class, levels.get(i).toString());
            this.levels.add(new LevelButton(i, t.name,t.stars));
            i++;
        }


    }

    public void tryLoadLevel(){
        loadPlanet();
    }

    public void draw(SpriteBatch batch){
        if(!show) return;
        super.draw(batch);
        i = 0;
        for(LevelButton level : levels){
            level.draw(batch);
            batch.draw(delimiter.getTexture(),menuLU.getX() + 25,level.slot.getY()- 7.5f, delimiter.getWidth()*2.9f,delimiter.getHeight());

        }
    }
    public void input(Vector3 vec){
        super.input(vec);
        for(LevelButton button : levels){
            button.input(vec);
        }
    }
    public void touchUp(Vector3 vec){
        super.touchUp(vec);
        for(LevelButton button : levels){
            if(button.touched && button.getBoundingRectangle().contains(vec.x,vec.y)){
                show = false;
                screen.inputState = WorldScreen.InputState.MINERALS;
                level = button.name;

                screen.mineralmenu.show(planet);
                screen.mineralmenu.addTitle("  "+level);
                Gdx.app.log("screen.mineralmenu.show","planet");

            }
            button.touchUp();

        }
    }
    public String getLevel(){
        return level;
    }
}

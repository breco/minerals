package items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemLoader {
    public static Item getItem(String id){
        FileHandle file = Gdx.files.internal("data/items.txt");
        String[] items = file.readString().split("\n");
        String[] itemdata;
        for(String item : items){
            itemdata = item.split("/");
            if(!itemdata[0].equals(id)) continue;

            Class<?> clazz = null;
            try {
                clazz = Class.forName(itemdata[1]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Constructor<?> ctor = null;
            try {
                ctor = clazz.getConstructor(Texture.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                return (Item)(ctor.newInstance(new Texture(Gdx.files.internal("items/"+itemdata[2]+".png"))));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

}


/*
        while(enemigos.get(i) != null){
            t = json.fromJson(JsonEnemy.class, enemigos.get(i).toString());
            Class<?> clazz = Class.forName(t.clase);
            //Gdx.app.log("t.clase",t.clase);
            Constructor<?> ctor = clazz.getConstructor(int.class,int.class,int.class);
            enemies.add((Enemy) ctor.newInstance(t.posx,Initial.WIDTH,t.time));
            i++;
        }
 */
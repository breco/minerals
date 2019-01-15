package minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MineralLoader {
    public static Mineral getMineral(String id){
        FileHandle file = Gdx.files.internal("data/minerals.txt");
        String[] minerals = file.readString().split("\n");
        String[] mineraldata;
        for(String mineral : minerals){
            mineraldata = mineral.split("/");
            if(!mineraldata[0].equals(id)) continue;

            Class<?> clazz = null;
            try {
                clazz = Class.forName(mineraldata[1]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Constructor<?> ctor = null;
            try {

                ctor = clazz.getConstructor(float.class, float.class, int.class, int.class, int.class, int.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                return (Mineral)(ctor.newInstance(0,0, Integer.parseInt(mineraldata[3]), Integer.parseInt(mineraldata[4]),Integer.parseInt(mineraldata[5]),Integer.parseInt(mineraldata[6])));
                /*

                 */
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

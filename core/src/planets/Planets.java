package planets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Planets {
        public Array<Planet> planets;
        public Planets(){
            planets = new Array<Planet>();
        }
        public void update(){
            for(Planet planet : planets){
                planet.update();
            }

        }
        public Planet getTouched(){
            for(Planet planet : planets){
                if(planet.touched) return planet;
            }
            return null;
        }
        public boolean planetTouched(){
            for(Planet planet : planets){
                if(planet.touched) return true;
            }
            return false;
        }

        public void setUntouched(){
            for(Planet planet : planets){
                planet.touched = false;
            }
        }
        public void draw(SpriteBatch batch){
            for(Planet planet : planets){
                planet.draw(batch);
            }
        }
        public void input(Vector3 vec){
            for(Planet planet : planets){
                planet.input(vec);
            }
        }
        public void add(Planet planet){
            planets.add(planet);
        }
        public void remove(Planet planet){
            planets.removeValue(planet,false);
        }

}


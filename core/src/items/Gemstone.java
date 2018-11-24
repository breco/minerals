package items;

import com.badlogic.gdx.graphics.Texture;

import screens.MainGame;

public class Gemstone extends Item {
    private int amount;

    public Gemstone(Texture texture){
        super(texture,"auto");
        amount = 15;


    }



    @Override
    public void effect() {

        MainGame.chargePP(amount);
        used = true;
    }
}

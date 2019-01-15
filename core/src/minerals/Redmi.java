package minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import huds.maingame.MineralPin;
import skills.LaunchFireworks;
import utils.Animator;

public class Redmi extends Mineral {

    public Redmi(float x, float y, int HP, int ATK, int SPD, int PP) {
        super(x, y, HP, ATK, SPD, PP);
        bulletTexture = new Texture(Gdx.files.internal("bullets/crystal bullet.png"));
        animator = new Animator(new Texture(Gdx.files.internal("minerals/redmi.png")),2,2,4,0.2f);
        int[] size = {12,12};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/redmi bullet.png")),1,2,2,0.5f,size);
        pin = new MineralPin(this,"purple");
        skill = new LaunchFireworks(this, new Texture(Gdx.files.internal("skills/icons/fireworks.png")));
    }


}


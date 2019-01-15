package minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import huds.maingame.MineralPin;
import skills.LaunchFireBalls;
import utils.Animator;

public class Agni extends Mineral{

    public Agni(float x, float y, int HP, int ATK, int SPD, int PP) {
        super(x, y, HP, ATK, SPD, PP);
        bulletTexture = new Texture(Gdx.files.internal("bullets/fire bullet.png"));
        int[] size2 = {40,40};
        animator = new Animator(new Texture(Gdx.files.internal("minerals/agni.png")),2,2,4,0.2f,size2);
        int[] size = {16,16};
        bulletAnimator = new Animator(bulletTexture,2,1,2,0.5f,size);
        pin = new MineralPin(this,"yellow");

        skill = new LaunchFireBalls(this, new Texture(Gdx.files.internal("skills/icons/fireball.png")));
    }
}

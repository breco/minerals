package minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import huds.maingame.maingame.MineralPin;
import skills.LaunchRockWheel;
import utils.Animator;

public class Terro extends Mineral {

    public Terro(int x, int y, int HP, int ATK, int SPD, int PP) {
        super(x, y, HP, ATK, SPD, PP);
        bulletTexture = new Texture(Gdx.files.internal("bullets/crystal bullet.png"));
        animator = new Animator(new Texture(Gdx.files.internal("minerals/terro.png")),1,4,4,0.2f);
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/crystal bullet.png")),1,4,4,0.5f,size);
        pin = new MineralPin(this,"purple");
        skill = new LaunchRockWheel(this, new Texture(Gdx.files.internal("skills/icons/rock wheel.png")));
    }


}

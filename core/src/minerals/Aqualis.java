package minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import huds.maingame.MineralPin;
import skills.TestSkill;
import utils.Animator;

public class Aqualis extends Mineral {

    public Aqualis(float x, float y, int HP, int ATK, int SPD, int PP) {
        super(x, y, HP, ATK, SPD, PP);
        animator = new Animator(new Texture(Gdx.files.internal("minerals/aqualis.png")),1,2,2,0.4f);
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/bubble bullet.png")),1,4,4,0.6f,size);
        pin = new MineralPin(this,"green");
        skill = new TestSkill(this, new Texture(Gdx.files.internal("skills/icons/skill1.png")));
    }
}

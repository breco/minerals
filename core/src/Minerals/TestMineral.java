package Minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import utils.Animator;

public class TestMineral extends Mineral {

    public TestMineral(float x, float y, int HP, int ATK, int SPD) {
        super(new Texture(Gdx.files.internal("minerals/aqualis.png")), x, y, HP, ATK, SPD);
        animator = new Animator(new Texture(Gdx.files.internal("minerals/aqualis.png")),1,2,2,0.4f);
        int[] size = {15,15};
        bulletAnimator = new Animator(new Texture(Gdx.files.internal("bullets/bubble bullet.png")),1,4,4,0.6f,size);

    }
}

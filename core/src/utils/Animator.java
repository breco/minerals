package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    public Texture sheet;
    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;
    public int width, height;
    float rotation = 1000;
    public Animator(Texture texture,int rows, int columns, int frames,float speed,int[]... size){

        int x,y;
        if(size.length == 0){
            x = 32;
            y = 32;

        }
        else{
            x = size[0][0];
            y = size[0][1];

        }
        width = x;
        height = y;
        elapsedTime = 0f;
        sheet = texture;
        TextureRegion[][] tmpFrames = TextureRegion.split(sheet,x,y);
        animationFrames = new TextureRegion[frames];
        int index = 0;
        for(int i = 0; i < rows;i++){
            for(int j = 0; j < columns; j++) {
                animationFrames[index++] = tmpFrames[i][j];
            }
        }



        animation = new Animation(speed,animationFrames);
    }
    public void draw(Sprite sprite, SpriteBatch batch){
        elapsedTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion((TextureRegion) animation.getKeyFrame(elapsedTime,true));
        if(rotation != 1000){
            sprite.setOriginCenter();
            sprite.setRotation(rotation);
        }
        sprite.draw(batch);
    }
    public TextureRegion getTextureRegion(){
        return (TextureRegion) animation.getKeyFrame(elapsedTime,true);
    }

    public void setRotation(float angle){
        this.rotation = angle;
        //this.rotation = (float) (angle*Math.PI/180f);

    }
}

package skills;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import minerals.Mineral;
import screens.MainGame;

public class Skills {
    public Array<Skill> skills;
    MainGame game;
    public Skills(MainGame game) {
        skills = new Array<Skill>();
        this.game = game;


    }
    public void draw(SpriteBatch batch){
        for(Skill skill  : skills){
            skill.draw(batch);
        }
    }
    public void update(){
        for(Skill skill : skills){
            skill.update();
            skill.stopCoolDown();
            skill.setAvailability(game.CURRENT_PP);
        }
    }
    public void remove(Skill skill){
        skills.removeValue(skill, false);
    }
    public Array<Skill> getSkills(){
        return skills;
    }
    public void add(Skill skill){
        skills.add(skill);
    }
    public void setPosition(){
        int  i = 0;
        for(Skill skill : skills){
            skill.setPosition(i);
            i++;
        }
    }
    public void input(Vector3 vec, int pointer){
        //vec.set(vec.x, Initial.HEIGHT - vec.y, 0);
        for(Skill skill : skills){
            skill.input(vec, pointer);
        }


    }

    public void setSkills(){

        for (Mineral mineral : game.minerals.getMinerals()){
            this.add(mineral.getSkill());

        }
    }

    public void touchUp(int pointer){
        if(pointer == 0){
            for(Skill skill : skills){
                if(skill.touched) skill.effect();
            }
        }
        if(pointer == 1){
            for(Skill skill : skills){
                if(skill.touched2) skill.effect();
            }
        }

    }
    public void setUntouched(int pointer){
        if(pointer == 0){
            for(Skill skill : skills){
                //if(skill.touched) skill.resetPosition();
                skill.touched = false;
            }
        }
        if(pointer == 1){
            for(Skill skill : skills){
                //if(skill.touched2) skill.resetPosition();
                skill.touched2 = false;
            }
        }
    }

    public void pause(){
        for(Skill skill : skills){
            skill.pause();
        }
    }
    public void unpause(){
        for(Skill skill : skills){
            skill.unpause();
        }
    }
}


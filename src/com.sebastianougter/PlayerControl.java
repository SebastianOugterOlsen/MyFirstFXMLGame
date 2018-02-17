//Angiver hvilken package kildekoden er placeret i
package com.sebastianougter;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

/**
 * Klassen "PlayerControl" står for styringen af Player
 * Klassen nedarver fra superklassen "Control", som er en del af FXGL-biblioteket.
 *
 * @author Sebastian Ougter Olsen (SebastianOugterOlsen) (seba3440@edu.easj.dk)
 * @version 1.0
 */

@Required(PositionComponent.class)
public class PlayerControl extends Control {

    private PhysicsComponent physics;

    private AnimatedTexture texture;

    private AnimationChannel animIdle, animWalk;

    FixtureDef fd = new FixtureDef();

    public PlayerControl(){ //Indsætter Agentoo.png filen og definere antallet af og størrelsen pr. frame

        animIdle = new AnimationChannel("Agent00.png", 9, 28, 48, Duration.seconds(1),0,0);
        animWalk = new AnimationChannel("Agent00.png", 9, 28, 48, Duration.seconds(1),1,8);

        texture = new AnimatedTexture(animIdle);

    }

    @Override
    public void onAdded(Entity entity) {
        entity.setView(texture);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {



        if(isMoving()){ //Styrer hvornår Player går og står
            texture.setAnimationChannel(animWalk);
        } else{
            texture.setAnimationChannel(animIdle);
        }
        if (Math.abs(physics.getVelocityX()) < 145){ //Player stopper med at gå hvis hastigheden kommer under 140.
            physics.setVelocityX(0);}

    }

    private boolean isMoving(){
        return FXGLMath.abs(physics.getVelocityX()) > 0;

    }

    public void left(){
        getEntity().setScaleX(-1);
        physics.setVelocityX(-150);
    }

    public void right(){
        getEntity().setScaleX(1);
        physics.setVelocityX(150);
    }

    public void jump(){
        if(physics.getVelocityY()==0) {
            physics.setVelocityY(-340);

    }
}


}

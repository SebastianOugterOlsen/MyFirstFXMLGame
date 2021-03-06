//Angiver hvilken package kildekoden er placeret i
package com.sebastianougter;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;


/**
 * Klassen "AndroidControl" forudsætter styringen af hver enkelt Android
 * Klassen nedarver fra superklassen "Control", som er en del af FXGL-biblioteket.
 *
 * @author Sebastian Ougter Olsen (SebastianOugterOlsen) (seba3440@edu.easj.dk)
 * @version 1.0
 */

public class AndroidControl extends Control {

    private PhysicsComponent physics;

    private LocalTimer jumpTimer;

    @Override
    public void onAdded(Entity entity) {
        jumpTimer = FXGL.newLocalTimer();
        jumpTimer.capture();
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        if (jumpTimer.elapsed(Duration.seconds(Math.random()*4+2))) {
            jump();
            jumpTimer.capture();
        }
    }

    public void jump() {
        physics.setVelocityY(-300);
    }
}

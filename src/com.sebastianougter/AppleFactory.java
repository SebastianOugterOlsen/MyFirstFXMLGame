//Angiver hvilken package kildekoden er placeret i
package com.sebastianougter;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;


/**
 * An APPLE guy's adventure - ET LILLE JAVA-SPIL UDVIKLET MED BIBLIOTEKET FXGL (version 0.4.2)
 * Klassen "AppleFactory" indeholder alle de forskellige elementer som findes i de forskellige baner.
 * Klassen nedarver fra superklassen "EntityFactory", som er en del af FXGL-biblioteket.
 *
 * @author Sebastian Ougter Olsen (SebastianOugterOlsen) (seba3440@edu.easj.dk)
 * @version 1.0
 */

@SetEntityFactory
public class AppleFactory implements EntityFactory {


    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return Entities.builder()
                .type(AppleType.PLATFORM)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();

    }

    @Spawns("android")
    public Entity newAndroid(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(AppleType.ANDROID)
                .from(data)
                .viewFromTextureWithBBox("Android.png")
                .bbox(new HitBox(BoundingShape.box(20, 25)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new AndroidControl())
                .build();
    }

    @Spawns("lift")
    public Entity newLift(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(AppleType.LIFT)
                .from(data)
                .viewFromTextureWithBBox("Lift.png")
                .bbox(new HitBox(BoundingShape.box(135, 20)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new LiftControl())
                .build();
    }

    @Spawns("laiser")
    public Entity newlaiser(SpawnData data) {
        return Entities.builder()
                .type(AppleType.LAISER)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("laiser2")
    public Entity newlaiser2(SpawnData data) {
        return Entities.builder()
                .type(AppleType.LAISER2)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }



    @Spawns("portal")
    public Entity newPortal(SpawnData data) {
        return Entities.builder()
                .type(AppleType.PORTAL)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("portal2")
    public Entity newPortal2(SpawnData data) {
        return Entities.builder()
                .type(AppleType.PORTAL2)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("portal3")
    public Entity newPortal3(SpawnData data) {
        return Entities.builder()
                .type(AppleType.PORTAL3)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("portal4")
    public Entity newPortal4(SpawnData data) {
        return Entities.builder()
                .type(AppleType.PORTAL4)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }


    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return Entities.builder()
                .type(AppleType.PLAYER)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(28, 42)))
                //.viewFromNodeWithBBox(new Rectangle(30,30, Color.BLUE))
                .with(physics)
                .with(new IrremovableComponent())
                .with(new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data) {
        return Entities.builder()
                .type(AppleType.COIN)
                .from(data)
                .viewFromTextureWithBBox("Apple_Rainbow.png")
                .bbox(new HitBox(BoundingShape.box(25, 28)))
                .with(new CollidableComponent(true))
                .build();
    }
}
//Angiver hvilken package kildekoden er placeret i
package com.sebastianougter;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsControl;
import com.almasb.fxgl.settings.GameSettings;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import com.almasb.fxgl.texture.Texture;
import java.util.Map;



/**
 * An APPLE guy's adventure - ET LILLE JAVA-SPIL UDVIKLET MED BIBLIOTEKET FXGL (version 0.4.2)
 * Klassen "AppleApp" indeholder den samlede main-kildekode for spillet.
 * Klassen nedarver fra superklassen "GameApplication", som er en del af FXGL-biblioteket.
 *
 * @author Sebastian Ougter Olsen (SebastianOugterOlsen) (seba3440@edu.easj.dk)
 * @version 1.0
 */


public class AppleApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(15 * 70);  //Vinduets bredde & Hver enkelts tile's bredde
        settings.setHeight(10 * 70);//Vinduets højde & Hver enkelts tile's højde
        settings.setTitle("An Apple guy's Adventure"); //Titlen i vinduets top
        settings.setVersion("1.0"); //Spillets version
        settings.setMenuEnabled(true); //Tilføjer default menu
        settings.setSceneFactory(new MySceneFactory()); //Sætter customized menu


    }

    private Entity player;

    Point2D despawn = new Point2D(70, 600); // Punkt hvorfra Player spawnes

    @Override
    protected void initInput() {   //Tilføjer funktionalitet
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.UP);

    }



/*   Mislykket forsøg på at lave en save/load funktion

    //Save
    @Override
    public DataFile saveState() {

        Bundle bundlePlayer = new Bundle("Player");

        player.save(bundlePlayer);

        Bundle bundleRoot = new Bundle("Root");
        bundleRoot.put("player", bundlePlayer);

        return new DataFile(bundleRoot);
    }

    //Load
    @Override
    public void loadState(DataFile dataFile) {

        // call "new" initGame
        initGame();

        // now load state back
        Bundle bundleRoot = (Bundle) dataFile.getData();

        System.out.println(player);

        player.load(bundleRoot.get("player"));

        System.out.println(player);
    }


*/



    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("Level1.json"); //Starter med Lvl1
        getGameScene().setBackgroundRepeat("Applelogo.jpg"); //Med denne baggrund
        player = getGameWorld().spawn("player", 70, 500); // Players start placering i banen
        getGameScene().getViewport().setBounds(-1500, 0, 5000, getHeight()); //Banens størrelse
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2); //Placering af player på skærmen
        getGameWorld().spawn("android", 750, 240); //Placering af Androids på banen
        getGameWorld().spawn("android", 1070, 600);// -//-


    }


    @Override
    protected void initPhysics() {  //Fortæller hvad der skal ske når man rammer Mønter/Æbler
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(AppleType.PLAYER, AppleType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
                getGameState().increment("score", +1); //"Score" får tillagt 1 point for hvert æble
            }
        });


        getPhysicsWorld().addCollisionHandler(new CollisionHandler(AppleType.PLAYER, AppleType.PORTAL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity portal) { //Fortæller hvad der skal ske når man rammer en portal
                getDisplay().showMessageBox("Level 1 Complete!", () -> { // Dialog kommer fra og fortæller at LVl 1 er complete
                    System.out.println("Dialog Closed!");
                    player.getWorld().setLevelFromMap("Level2.json"); //Starter Lvl2
                    getGameScene().setBackgroundRepeat("Applelogo.jpg");//Med samme baggrund
                    getGameScene().getViewport().setBounds(-1500, 0, 3000, 1050);//Banens str.
                    player.getControl(PhysicsControl.class).reposition(despawn); //Player plviver placeret i lev2
                    getGameWorld().spawn("lift", 1900, 100); //Lift bliver placret på banen
                    getGameWorld().spawn("android", 450, 450); // Placering af Androids på banen
                    getGameWorld().spawn("android", 570, 450); // -//-
                    getGameWorld().spawn("android", 690, 450); // -//-

                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(AppleType.PLAYER, AppleType.PORTAL2) {
            @Override
            protected void onCollisionBegin(Entity player, Entity portal2) {   //Samme som ved PORTAL
                getDisplay().showMessageBox("Level 2 Complete!", () -> {
                    System.out.println("Dialog Closed!");
                    player.getWorld().setLevelFromMap("Level3.json");
                    getGameScene().setBackgroundRepeat("Applelogo.jpg");
                    getGameScene().getViewport().setBounds(-500, 0, 1200, 2100);
                    player.getControl(PhysicsControl.class).reposition(despawn);


                });

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(AppleType.PLAYER, AppleType.PORTAL3) {
            @Override
            protected void onCollisionBegin(Entity player, Entity portal3) { //Handling når man rammer PORTAL3

                if (getGameState().getInt("score") == 33) { //Hvis man har samlet alle æbler får man denne besked
                    getDisplay().showMessageBox("THE END! \n\nYou got the highest score possible! \nGood Job!", () -> {
                        System.out.println("Dialog Closed!");
                    });
                } else {                                                // Hvis man ikke har samlet alle æbler får man denne besked
                    getDisplay().showMessageBox("THE END! \n\nBut seems like you missed some Apples! \n\nTry again to" +
                            " get all of them!", () -> {
                        System.out.println("Dialog Closed!");
                    });
                }
                getDisplay().showMessageBox("Level 3 Complete!", () -> {
                });
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(AppleType.PLAYER, AppleType.ANDROID) {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) { //HAndling hvis man rammer en ANDROID

                getGameState().increment("lives", -1); //Mister 1 liv
                if (getGameState().getInt("lives") == 0) { //Hvis liv er == 0 Restarter man fra lvl1
                    getDisplay().showMessageBox("Game over!", () -> {
                        player.getWorld().setLevelFromMap("Level1.json");
                        getGameScene().setBackgroundRepeat("Applelogo.jpg");
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("android", 750, 240);
                        getGameWorld().spawn("android", 1070, 600);
                        getGameState().setValue("lives", 3); //Man får 3 nye liv
                        getGameState().setValue("score", 0); //Og nulstillet point
                    });
                } else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn);

                    });
                }

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(AppleType.PLAYER, AppleType.LASER) {
            @Override
            protected void onCollisionBegin(Entity player, Entity laiser) { //Samme som med ANDROID

                getGameState().increment("lives", -1);
                if (getGameState().getInt("lives") == 0) {
                    getDisplay().showMessageBox("Game over!", () -> {
                        player.getWorld().setLevelFromMap("Level1.json");
                        getGameScene().setBackgroundRepeat("Applelogo.jpg");
                        player.getControl(PhysicsControl.class).reposition(despawn);
                        getGameWorld().spawn("enemy", 750, 240);
                        getGameWorld().spawn("enemy", 1070, 600);
                        getGameState().setValue("lives", 3);
                        getGameState().setValue("score", 0);
                    });
                } else {
                    getDisplay().showMessageBox("Try again!", () -> {
                        player.getWorld();
                        player.getControl(PhysicsControl.class).reposition(despawn);
                    });
                }

            }
        });

    }

    @Override
    protected void initUI() { //Printer score og liv på skærmen inkl. billeder af Æble og Player
        Text uiScore = getUIFactory().newText("Score", 50);
        uiScore.setTranslateX(getWidth() - 960);
        uiScore.setTranslateY(70);
        uiScore.fillProperty().bind(getGameState().objectProperty("stageColor"));
        uiScore.textProperty().bind(getGameState().intProperty("score").asString());
        getGameScene().addUINode(uiScore);

        Text uiLives = getUIFactory().newText("Lives", 50);
        uiLives.setTranslateX(getWidth() - 960);
        uiLives.setTranslateY(150);
        uiLives.fillProperty().bind(getGameState().objectProperty("stageColorL"));
        uiLives.textProperty().bind(getGameState().intProperty("lives").asString());
        getGameScene().addUINode(uiLives);

        Texture scoreTexture = getAssetLoader().loadTexture("Apple_Rainbow.png");
        scoreTexture.setTranslateX(getWidth() - 1030);
        scoreTexture.setTranslateY(25);
        getGameScene().addUINode(scoreTexture);

        Texture livesTexture = getAssetLoader().loadTexture("AgentLife.png");
        livesTexture.setTranslateX(getWidth() - 1030);
        livesTexture.setTranslateY(105);
        getGameScene().addUINode(livesTexture);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("stageColor", Color.WHITE);
        vars.put("score", 0);

        vars.put("stageColorL", Color.WHITE);
        vars.put("lives", 3);
    }

    public static void main(String[] args) {
        launch(args);

    }
}

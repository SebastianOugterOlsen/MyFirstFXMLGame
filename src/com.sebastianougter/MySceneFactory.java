//Angiver hvilken package kildekoden er placeret i
package com.sebastianougter;

//Importerer nødvendige klasser fra libraries
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.SceneFactory;
import com.almasb.fxgl.scene.menu.MenuType;
import javafx.scene.Node;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * Klasse, der samlet set bl.a. definerer customized menu.
 * Nedarver fra superklassen "SceneFactory", som er en del af FXGL-biblioteket.
 *
 *  @author Sebastian Ougter Olsen (SebastianOugterOlsen) (seba3440@edu.easj.dk)
 */
public class MySceneFactory extends SceneFactory {

    /**
     * Metode, der overordnet set håndterer baggrund og title i Main Menu screen.
     *
     * @param app Tager parameter "app" af typen "GameApplication" fra FXGL.
     * @return FXGLMenu Returnerer "FXGLMenu".
     */
    @NotNull
    @Override
    public FXGLMenu newMainMenu(@NotNull GameApplication app) {
        return new FXGLAppleMenu(app, MenuType.MAIN_MENU) {
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("AppleLogo1.png");
            }

            //Overskriften i Main Menu-skærmen (Anvend ikke nogen!)
            @Override
            protected Node createTitleView(String title) {
                return new Text("");
            }
        };
    }

    /**
     * Metode, der overordnet set håndterer baggrund og title i Game Menu screen.
     *
     * @param app Tager parameter "app" af typen "GameApplication" fra FXGL.
     * @return FXGLMenu Returnerer "FXGLMenu".
     */
    @NotNull
    @Override
    public FXGLMenu newGameMenu(@NotNull GameApplication app) {
        return new FXGLAppleMenu(app, MenuType.GAME_MENU) {
            @Override
            protected Node createBackground(double width, double height) {
                return FXGL.getAssetLoader().loadTexture("AppleLogo1.png");
            }

            //Overskriften i Game Menu-skærmen (Anvend ikke nogen!)
            @Override
            protected Node createTitleView(String title) {
                return new Text("");
            }
        };
    }
}
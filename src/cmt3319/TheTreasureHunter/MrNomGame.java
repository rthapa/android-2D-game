package cmt3319.TheTreasureHunter;

//import android.app.Activity;
//import android.os.Bundle;

import cmt3319.gameframework.Screen;
import cmt3319.gameframework.impl.AndroidGame;
/**
 * This class is the game class which calls the loading screen class.
 * @author Rabi Thapa
 * @since 2014-04-29
 */
public class MrNomGame extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}
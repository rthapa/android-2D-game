package cmt3319.TheTreasureHunter;

import java.util.List;

import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Screen;
import cmt3319.gameframework.Input.TouchEvent;
/**
 * This class is the highscore class which shows the highscore screen.
 * @author Rabi Thapa
 * @since 2014-04-29
 */
public class HighscoreScreen extends Screen {
    String lines[] = new String[5];

    public HighscoreScreen(Game game) {
        super(game);

        for (int i = 0; i < 5; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
    }

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        //game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y > 416) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);

        int y = 100;
        for (int i = 0; i < 5; i++) {
            drawText(g, lines[i], 20, y);
            y += 50;
        }

        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
    
            if (character == ' ') {
                x += 20;
                continue;
            }
    
            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
    
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}

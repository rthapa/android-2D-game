package cmt3319.TheTreasureHunter;


import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Screen;
import cmt3319.gameframework.Graphics.PixmapFormat;
/**
 * This class loads all the assets media files before starting the game.
 * @author Rabi Thapa
 * @since 2014-04-29
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        //Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.background = g.newPixmap("alternative/bgWater.png", PixmapFormat.RGB565);
        Assets.backgroundMenu = g.newPixmap("alternative/background.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        
        Assets.buttons = g.newPixmap("buttons1.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        
        //Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
        //Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
        //Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
        //Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
        //Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        
        Assets.headUp = g.newPixmap("alternative/shipup.png", PixmapFormat.ARGB4444);
        Assets.headLeft = g.newPixmap("alternative/shipleft.png", PixmapFormat.ARGB4444);
        Assets.headDown = g.newPixmap("alternative/shipdown.png", PixmapFormat.ARGB4444);
        Assets.headRight = g.newPixmap("alternative/shipright.png", PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("alternative/tail.png", PixmapFormat.ARGB4444);
        
        Assets.gameComplete = g.newPixmap("gameComplete.png", PixmapFormat.ARGB4444);
        Assets.rock1 = g.newPixmap("rock1.png", PixmapFormat.ARGB4444);
        Assets.rock2 = g.newPixmap("rock2.png", PixmapFormat.ARGB4444);
        Assets.rock3 = g.newPixmap("rock3.png", PixmapFormat.ARGB4444);
        Assets.enemy = g.newPixmap("enemy.png", PixmapFormat.ARGB4444);
        Assets.enemyCrazy = g.newPixmap("enemyCrazy.png", PixmapFormat.ARGB4444);
        Assets.nextLevel = g.newPixmap("levelup.png", PixmapFormat.ARGB4444);
        Assets.box = g.newPixmap("box.png", PixmapFormat.ARGB4444);
        Assets.key = g.newPixmap("key.png", PixmapFormat.ARGB4444);
        Assets.stain1 = g.newPixmap("power1.png", PixmapFormat.ARGB4444);
        Assets.stain2 = g.newPixmap("power2.png", PixmapFormat.ARGB4444);
        Assets.stain3 = g.newPixmap("power3.png", PixmapFormat.ARGB4444);
        Assets.nowallmsg = g.newPixmap("nowallmsg.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");
        Assets.foundkey = game.getAudio().newSound("foundkey.mp3");
        Assets.levelup = game.getAudio().newSound("levelup.mp3");
        Assets.gamestart = game.getAudio().newSound("gamestart.mp3");
        Assets.invincible = g.newPixmap("alternative/invincible.png", PixmapFormat.ARGB4444);
        Assets.invincibleMsg = g.newPixmap("invinciblemsg.png", PixmapFormat.ARGB4444);
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}

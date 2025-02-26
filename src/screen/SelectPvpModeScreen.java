package screen;

import engine.Cooldown;
import engine.Core;
import engine.SoundManager;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class SelectPvpModeScreen extends Screen{

    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 200;
    private static final Logger LOGGER = Logger.getLogger(Core.class
            .getSimpleName());

    /** Time between changes in user selection. */
    private Cooldown selectionCooldown;
    /** Check pvp Mode */
    public static int gameMode = 2;

    /**
     * pvp모드인지
     */
    public static boolean isPvpMode = false;

    /**
     * Constructor, establishes the properties of the screen.
     *
     * @param width  Screen width.
     * @param height Screen height.
     * @param fps    Frames per second, frame rate at which the game is run.
     */
    public SelectPvpModeScreen(int width, int height, int fps){
        super(width, height, fps);
        this.returnCode = 7;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    /**
     * Starts the action.
     *
     * @return Next screen code.
     */
    public final int run() {
        super.run();
        return this.returnCode;
    }

    /**
     * Updates the elements on screen and checks for events.
     */
    protected final void update() {
        super.update();

        draw();
        if (this.selectionCooldown.checkFinished()
                && this.inputDelay.checkFinished()) {
            if(inputManager.isKeyDown(KeyEvent.VK_ESCAPE)){
                this.returnCode = 1;
                this.isRunning = false;
            }
            if (inputManager.isKeyDown(KeyEvent.VK_RIGHT)
                    || inputManager.isKeyDown(KeyEvent.VK_D)|| inputManager.isKeyDown(KeyEvent.VK_LEFT)|| inputManager.isKeyDown(KeyEvent.VK_A)) {
                isPvpMode = !isPvpMode;
                this.selectionCooldown.reset();}
            if (inputManager.isKeyDown(KeyEvent.VK_SPACE)){
                SoundManager.playSound("SFX/S_MenuClick", "menu_select", false, false);
                this.isRunning = false;
                this.selectionCooldown.reset();
            }
        }
    }

    /**
     * Draws the elements associated with the screen.
     */
    private void draw() {
        drawManager.initDrawing(this);
        drawManager.drawSelectPvpModeScreen(this,isPvpMode);
        drawManager.completeDrawing(this);
    }

}

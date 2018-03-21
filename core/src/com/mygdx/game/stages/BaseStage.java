package com.mygdx.game.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.log.Logs;

/**
 * Created by user on 3/20/18.
 */

public class BaseStage extends Stage {
    private MainGame mainGame;

    /** 舞台是否可见（是否更新和绘制） */
    private boolean visible = true;

    long lastLogTime = 0;
    long logWaitTime = 2000;

    public BaseStage(MainGame mainGame, Viewport viewport) {
        super(viewport);
        this.mainGame = mainGame;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

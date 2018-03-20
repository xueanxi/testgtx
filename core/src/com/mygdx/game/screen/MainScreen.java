package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.log.Logs;
import com.mygdx.game.res.Res;
import com.mygdx.game.stages.PlayStage;
import com.mygdx.game.stages.PlayStage2;

/**
 * Created by user on 3/20/18.
 */

public class MainScreen extends ScreenAdapter{
    /** 舞台背景颜色, 60% 黑色 */
    private final Color bgColor = new Color(0, 1, 0, 0.6F);
    MainGame mainGame;
    PlayStage playStage;
    PlayStage2 playStage2;



    public MainScreen(MainGame mainGame) {
        this.mainGame = mainGame;

        init();
    }

    @Override
    public void render(float delta) {
        super.render(delta);


        Gdx.gl.glClearColor(Res.BG_RGBA.r, Res.BG_RGBA.g, Res.BG_RGBA.b, Res.BG_RGBA.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(playStage!=null && playStage.isVisible()){
            playStage.act();
            playStage.draw();
        }

        if(playStage2!=null && playStage2.isVisible()){
            playStage2.act();
            playStage2.draw();
        }

    }

    @Override
    public void dispose() {
        super.dispose();
        if(playStage!=null){
            playStage.dispose();
        }
    }

    private void init() {
        // 1. 创建主游戏舞台
        playStage = new PlayStage(
                mainGame,
                new StretchViewport(
                        mainGame.getWorldWidth(),
                        mainGame.getWorldHeight()
                )
        );
        playStage.setVisible(true);

        playStage2 = new PlayStage2(
                mainGame,
                new StretchViewport(
                        mainGame.getWorldWidth(),
                        mainGame.getWorldHeight()
                )
        );
        playStage2.setVisible(false);

        Gdx.input.setInputProcessor(playStage);
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }


    public void showPlayStage2(){
        if(playStage2!= null && !playStage2.isVisible()){
            playStage.setVisible(false);
            playStage2.setVisible(true);
            Gdx.input.setInputProcessor(playStage2);
        }
    }

    public void showPlayStage(){
        if(playStage!= null && !playStage.isVisible()){
            playStage2.setVisible(false);
            playStage.setVisible(true);
            Gdx.input.setInputProcessor(playStage);
        }
    }


}

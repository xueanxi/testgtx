package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.log.Logs;
import com.mygdx.game.res.Res;
import com.mygdx.game.stages.TestStage;
import com.mygdx.game.stages.DpiStage;

/**
 * Created by user on 3/20/18.
 */

public class MainScreen extends ScreenAdapter{
    /** 舞台背景颜色, 60% 黑色 */
    private final Color bgColor = new Color(0, 1, 0, 0.6F);
    MainGame mainGame;
    TestStage testStage;
    DpiStage dpiStage;
    Camera camera;

    public MainScreen(MainGame mainGame) {
        this.mainGame = mainGame;

        init();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        if(testStage !=null && testStage.isVisible()){
            testStage.act();
            testStage.draw();
        }

        if(dpiStage !=null && dpiStage.isVisible()){
            dpiStage.act();
            dpiStage.draw();
        }

/*        Logs.e("getDeltaTime() = "+Gdx.graphics.getDeltaTime());
        Logs.e("delta() = "+delta);*/

    }

    public void handleInput(){
        if( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ){
            camera.position.add( -2f, 0, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.RIGHT )  ){
            camera.position.add( 2f, 0, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.UP )  ){
            camera.position.add( 0, 2f, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.DOWN )  ){
            camera.position.add( 0, -2f, 0 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.D )  ){
            //camera.zoom -= 0.05f;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.F )  ){
            //camera.zoom += 0.05f;
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.A )  ){
            //camera.rotate( 1 );
        }
        else if( Gdx.input.isKeyPressed( Input.Keys.S )  ){
            //camera.rotate( -1 );
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if(testStage !=null){
            testStage.dispose();
        }
    }

    private void init() {
        // 1. 创建主游戏舞台
        testStage = new TestStage(
                mainGame,
                new StretchViewport(
                        mainGame.getWorldWidth(),
                        mainGame.getWorldHeight()
                )
        );
        testStage.setVisible(true);

        /*dpiStage = new DpiStage(
                mainGame,
                new StretchViewport(
                        mainGame.getWorldWidth(),
                        mainGame.getWorldHeight()
                )
        );*/
        long realWidth = Gdx.graphics.getWidth();
        long realHeight = Gdx.graphics.getHeight();
        float width = 720;
        float height = 320;
        width = mainGame.getWorldWidth();
        height = mainGame.getWorldHeight();
        StretchViewport stretchViewport = new StretchViewport(width,height);
        FitViewport fitViewport = new FitViewport(width,height);
        FillViewport fillViewport = new FillViewport(width,height);
        dpiStage = new DpiStage(mainGame,fillViewport);
        dpiStage.setVisible(false);

        Gdx.input.setInputProcessor(testStage);
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }


    public void showDpiStage(){
        if(dpiStage != null && !dpiStage.isVisible()){
            testStage.setVisible(false);
            dpiStage.setVisible(true);
            Gdx.input.setInputProcessor(dpiStage);

            camera = dpiStage.getViewport().getCamera();
        }
    }

    public void showTestStage(){
        if(testStage != null && !testStage.isVisible()){
            dpiStage.setVisible(false);
            testStage.setVisible(true);
            Gdx.input.setInputProcessor(testStage);

            camera = testStage.getViewport().getCamera();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Logs.e("MainScreen resize w:"+width+" H:"+height);
        if(dpiStage.isVisible()){
            dpiStage.getViewport().update(width,height,true);
        }
    }
}

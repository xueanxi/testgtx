package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.res.Res;

/**
 * Created by user on 3/20/18.
 */

public class PlayStage2 extends BaseStage {



    /** 舞台背景颜色, 60% 黑色 */
    private final Color bgColor = new Color(0, 0, 0, 1F);
    private final Color bgColor2 = new Color(1, 0, 0, 1F);

    /** 提示文本的颜色 */
    private final Color msgColor = new Color(0xFFFFFFFF);

    /** 背景 */
    private Image bgImage;

    /** 游戏结束文本提示标签 */
    private Label msgLabel;

    /** 返回按钮 */
    private Button backButton;

    /** 再来一局按钮 */
    private Button againButton;

    public PlayStage2(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();

    }

    private void init() {
        /*
         * 背景
         */
        // Res.AtlasNames.GAME_BLANK 是一张纯白色的小图片
        bgImage = new Image(getMainGame().getAtlas().findRegion(Res.AtlasNames.GAME_BLANK));
        bgImage.setColor(bgColor);
        bgImage.setOrigin(0, 0);
        // 缩放到铺满整个舞台
        bgImage.setScale(getWidth() / bgImage.getWidth(), getHeight() / bgImage.getHeight());
        addActor(bgImage);

        //游戏结束文本提示标签
        Label.LabelStyle msgStyle = new Label.LabelStyle();
        msgStyle.font = getMainGame().getBitmapFont();
        msgStyle.fontColor = msgColor;
        msgLabel = new Label("Stage2", msgStyle);
        msgLabel.setFontScale(0.7F);
        addActor(msgLabel);

        //返回按钮
        Button.ButtonStyle backStyle = new Button.ButtonStyle();
        backStyle.up = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(Res.AtlasNames.BTN_BACK, 1));
        backStyle.down = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(Res.AtlasNames.BTN_BACK, 2));
        backButton = new Button(backStyle);
        backButton.setX(40);
        backButton.setY(140);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 点击返回按钮, 隐藏结束舞台（返回主游戏舞台）
                getMainGame().getMainScreen().showPlayStage();
            }
        });
        addActor(backButton);

        /*
         * 再来一局按钮
         */
        Button.ButtonStyle againStyle = new Button.ButtonStyle();
        againStyle.up = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(Res.AtlasNames.BTN_AGAIN, 1));
        againStyle.down = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(Res.AtlasNames.BTN_AGAIN, 2));
        againButton = new Button(againStyle);
        againButton.setX(getWidth() - againButton.getWidth() - 40);
        againButton.setY(140);
        againButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 隐藏结束舞台（返回主游戏舞台）
                //getMainGame().getGameScreen().hideGameOverStage();
                // 重新初始化游戏
                //getMainGame().getGameScreen().restartGame();
            }
        });
        addActor(againButton);

        /*
    	 * 添加舞台输入监听器
    	 */
        //addListener(new InputListenerImpl());


    }
}

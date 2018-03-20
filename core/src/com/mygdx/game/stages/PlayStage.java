package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.log.Logs;
import com.mygdx.game.res.Res;

import java.awt.Font;

/**
 * Created by user on 3/20/18.
 */

public class PlayStage extends BaseStage {



    /** 舞台背景颜色, 60% 黑色 */
    private final Color bgColor = new Color(0, 0, 0, 1F);
    private final Color bgColor2 = new Color(1, 0, 0, 1F);

    /** 提示文本的颜色 */
    private final Color msgColor = new Color(0xFFFFFFFF);

    /** 背景 */
    private Image bgImage;
    private Image imageMove;

    /** 游戏结束文本提示标签 */
    private Label msgLabel;
    private Label msgLabelChinese;

    /** 返回按钮 */
    private Button backButton;
    private TextButton dailogButton;
    private Dialog dialog;

    Skin skin;



    public PlayStage(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init() {
        /*
         * 背景
         */
        // Res.AtlasNames.GAME_BLANK 是一张纯白色的小图片
        bgImage = new Image(getMainGame().getAtlas().findRegion(Res.AtlasNames.GAME_BLANK));
        //bgImage.setColor(bgColor);
        bgImage.setOrigin(0, 0);
        // 缩放到铺满整个舞台
        bgImage.setScale(getWidth() / bgImage.getWidth(), getHeight() / bgImage.getHeight());
        addActor(bgImage);

        //游戏结束文本提示标签
        Label.LabelStyle msgStyle = new Label.LabelStyle();
        msgStyle.font = getMainGame().getBitmapFont();
        msgStyle.fontColor = msgColor;
        msgLabel = new Label("Stage1", msgStyle);
        msgLabel.setFontScale(0.7F);
        addActor(msgLabel);

//返回按钮
        Button.ButtonStyle backStyle = new Button.ButtonStyle();
        backStyle.up = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(Res.AtlasNames.BTN_BACK, 1));
        backStyle.down = new TextureRegionDrawable(getMainGame().getAtlas().findRegion(Res.AtlasNames.BTN_BACK, 2));
        backButton = new Button(backStyle);
        backButton.setX(200);
        backButton.setY(20);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 点击返回按钮, 隐藏结束舞台（返回主游戏舞台）
                //getMainGame().getGameScreen().hideGameOverStage();
                getMainGame().getMainScreen().showPlayStage2();
            }
        });
        addActor(backButton);

//添加一个带动画的图片
        AlphaAction action7 = Actions.fadeOut( 1 );
        AlphaAction action8 = Actions.fadeIn( 1 );

        imageMove = new Image(getMainGame().getAtlas().findRegion(Res.AtlasNames.GAME_LOGO));
        imageMove.setSize(imageMove.getWidth()/2, imageMove.getHeight()/2);

        Action totalAction;
        // 动画带by的是相对当前的状态进行变化的
        MoveByAction action1 = Actions.moveBy( this.getWidth()/2-imageMove.getWidth()/2, this.getHeight()/2-imageMove.getHeight()/2, 2 );
        ScaleByAction action2 = Actions.scaleBy( 2, 2, 2 );
        RotateByAction action3 = Actions.rotateBy( -360, 2 );
        // 动画带to的是以初始状态为基础的变化
        RotateToAction action4 = Actions.rotateTo( 0, 2 );
        ScaleToAction action5 = Actions.scaleTo( 1, 2, 2 );
        MoveToAction action6 = Actions.moveTo( 0, 0, 2 );
        totalAction = Actions.forever(Actions.sequence( Actions.sequence(action1, action2, action3,action7),
                Actions.sequence(action8,action4, action5, action6) ) );
        //imageMove.addAction(totalAction);
        addActor(imageMove);

// 弹出对话框的按钮
        skin = new Skin();
        Pixmap pixmap = new Pixmap( 1, 1, Pixmap.Format.RGBA8888 );
        pixmap.setColor( Color.GRAY );
        pixmap.fill();
        skin.add( "gray", new Texture(pixmap) );
        pixmap.setColor( Color.LIGHT_GRAY );
        pixmap.fill();
        skin.add( "light_gray", new Texture(pixmap));

        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle( font, Color.BLUE );

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle( skin.getDrawable("gray"), skin.getDrawable("light_gray"),null,font );
        skin.add("default",buttonStyle);
        Window.WindowStyle windowStyle = new Window.WindowStyle( font, Color.GREEN, skin.getDrawable( "light_gray" ) );
        skin.add("default",windowStyle);
        skin.add("default",labelStyle);

        dailogButton = new TextButton("click",skin);
        dailogButton.setPosition(300,20);
        dailogButton.setSize(100,60);
        addActor(dailogButton);
        dailogButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
               if(dialog!= null){
                   dialog.show(PlayStage.this);
               }
            }
        });


// 对话框
        dialog = new Dialog("dialog",skin){
            @Override
            protected void result(Object object) {
                super.result(object);
                removeActor(dialog);
                if(object.toString().equals("yes")){
                    Logs.e("dialog yes.");
                    Gdx.app.exit();
                }else{
                    Logs.e("dialog "+object.toString());
                }
            }
        };
        dialog.text("Do you want to exist?");
        dialog.button("yes","yes");
        dialog.button("no",false);
        dialog.pack();

//显示中文的lable
        Label.LabelStyle chinaStyle = new Label.LabelStyle();
        chinaStyle.font = getMainGame().getFont();
        msgLabelChinese = new Label("中国人啊啊啊啊",chinaStyle);
        msgLabelChinese.setX(getWidth()/2);
        msgLabelChinese.setY(getHeight()/2);
        addActor(msgLabelChinese);

        Camera camera = this.getCamera();
        //camera.translate(100,0,0);
        //camera.rotate(0,45,45,0);
        


    }

    @Override
    public void act() {
        super.act();

    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
    }
}

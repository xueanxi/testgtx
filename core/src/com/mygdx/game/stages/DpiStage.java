package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;
import com.mygdx.game.log.Logs;
import com.mygdx.game.res.Res;

/**
 * Created by user on 3/20/18.
 */

public class DpiStage extends BaseStage {



    /** 舞台背景颜色, 60% 黑色 */
    private final Color bgColor = new Color(1, 1, 1, 1F);
    private final Color bgColor2 = new Color(0, 0, 0, 1F);

    /** 提示文本的颜色 */
    private final Color msgColor = new Color(0xFFFFFFFF);

    /** 背景 */
    private Image bgImage;

    /** 游戏结束文本提示标签 */
    private Label msgLabel;

    /** 返回按钮 */
    private TextButton backButton;

    /** 再来一局按钮 */
    private Button againButton;

    private BitmapFont font;
    private Skin skin;
    private TextButton.TextButtonStyle textButtonStye;

    public DpiStage(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init() {
        initSkin();
        /*
         * 背景
         */
        // Res.AtlasNames.GAME_BLANK 是一张纯白色的小图片
        bgImage = new Image(getMainGame().getAtlas().findRegion(Res.AtlasNames.GAME_BLANK));
        bgImage.setColor(bgColor2);
        bgImage.setOrigin(0, 0);
        // 缩放到铺满整个舞台
        bgImage.setScale(getWidth() / bgImage.getWidth(), getHeight() / bgImage.getHeight());
        addActor(bgImage);

        Image bgImage2 = new Image(new Texture(Gdx.files.internal("my3.PNG")));
        bgImage2.setColor(bgColor);
        bgImage2.setOrigin(0, 0);
        // 缩放到铺满整个舞台
        //bgImage2.setScale(getWidth() / bgImage2.getWidth(), getHeight() / bgImage2.getHeight());
        addActor(bgImage2);


        //返回按钮
        backButton = new TextButton("Back",skin);
        backButton.setSize(100,60);
        backButton.setX(0);
        backButton.setY(getHeight() - backButton.getHeight());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // 点击返回按钮, 隐藏结束舞台（返回主游戏舞台）
                getMainGame().getMainScreen().showTestStage();
            }
        });
        addActor(backButton);



        /*
    	 * 添加舞台输入监听器
    	 */
        //addListener(new InputListenerImpl());


    }




    private void initSkin(){
        skin = new Skin();
        Pixmap pixmap = new Pixmap( 1, 1, Pixmap.Format.RGBA8888 );
        pixmap.setColor( Color.GRAY );
        pixmap.fill();
        skin.add( "gray", new Texture(pixmap) );
        pixmap.setColor( Color.LIGHT_GRAY );
        pixmap.fill();
        skin.add( "light_gray", new Texture(pixmap));

        Label.LabelStyle chinaStyle = new Label.LabelStyle();
        font = getMainGame().getFont();
        chinaStyle.font = font;

        textButtonStye = new TextButton.TextButtonStyle(skin.getDrawable("gray"), skin.getDrawable("light_gray"),null,font);

        skin.add("default",textButtonStye);
        Window.WindowStyle windowStyle = new Window.WindowStyle( font, Color.GREEN, skin.getDrawable( "light_gray" ) );
        skin.add("default",windowStyle);
        skin.add("default",chinaStyle);
    }

    @Override
    public void draw() {
        super.draw();
        long time = System.currentTimeMillis();
        if(time - lastLogTime > logWaitTime){
            lastLogTime = time;
            Logs.e("DpiStage w:"+this.getWidth()+" H:"+getHeight() );
        }
    }
}

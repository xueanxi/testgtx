package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.async.ThreadUtils;
import com.mygdx.game.log.Logs;
import com.mygdx.game.res.Res;
import com.mygdx.game.screen.MainScreen;

public class MainGame extends Game {
	public final String TAG = "=MainGame";


	private int FIX_WORLD_WIDTH = 480;

	/** 世界宽度 */
	private float worldWidth;
	/** 世界高度 */
	private float worldHeight;

	/** 纹理图集 */
	private TextureAtlas atlas;
	/** 位图字体 */
	private BitmapFont bitmapFont;
	private Texture texture;

	AssetManager assetManager;
	MainScreen mainScreen;
	FreeTypeFontGenerator freeTypeFontGenerator;
	BitmapFont font;

	
	@Override
	public void create () {
		worldWidth = Res.FIX_WORLD_WIDTH;
		worldHeight = Gdx.graphics.getHeight() * worldWidth / Gdx.graphics.getWidth();

		Logs.e("worldWidth = "+worldWidth+" worldHeight = "+worldHeight);

		texture = new Texture(Gdx.files.internal("button1.png"));

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/nangong.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 25;
		parameter.characters +="中国人啊分辨率";//需要用到什么字，需要先加进来
		parameter.color = Color.BLACK;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		assetManager = new AssetManager();
		assetManager.load(Res.ATLAS_PATH, TextureAtlas.class);
		assetManager.load(Res.BITMAP_FONT_PATH, BitmapFont.class);
		assetManager.load(Res.Audios.MOVE, Sound.class);
		assetManager.finishLoading();

		// 获取资源
		atlas = assetManager.get(Res.ATLAS_PATH, TextureAtlas.class);
		bitmapFont = assetManager.get(Res.BITMAP_FONT_PATH, BitmapFont.class);

		mainScreen = new MainScreen(this);
		setScreen(mainScreen);
		//Logs.e("after setScreen mainScreen.width = "+mainScreen.);
	}

/*	@Override
	public void render () {
		Logger logger = new Logger(TAG);
		long timeStart = TimeUtils.millis();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}*/
	
	@Override
	public void dispose () {
		if(mainScreen != null){
			mainScreen.dispose();
		}

		if(assetManager != null){
			assetManager.dispose();
		}

		if(freeTypeFontGenerator != null){
			freeTypeFontGenerator.dispose();
		}
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}

	public float getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(float worldWidth) {
		this.worldWidth = worldWidth;
	}

	public float getWorldHeight() {
		return worldHeight;
	}

	public void setWorldHeight(float worldHeight) {
		this.worldHeight = worldHeight;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

	public void setBitmapFont(BitmapFont bitmapFont) {
		this.bitmapFont = bitmapFont;
	}

	public MainScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
}

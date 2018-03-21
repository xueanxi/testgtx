package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.log.Logs;
import com.mygdx.game.res.Res;
import com.mygdx.game.screen.MainScreen;

public class MainGame2 extends Game {
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
	private SpriteBatch spriteBatch;


	FreeTypeFontGenerator freeTypeFontGenerator;
	BitmapFont font;
	OrthographicCamera camera;
	Camera camera2;

	@Override
	public void create () {
		worldWidth = Res.FIX_WORLD_WIDTH;
		worldHeight = Gdx.graphics.getHeight() * worldWidth / Gdx.graphics.getWidth();

		Logs.e("worldWidth = "+worldWidth+" worldHeight = "+worldHeight);

		texture = new Texture(Gdx.files.internal("my.PNG"));

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/nangong.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 25;
		parameter.characters +="中国人啊分辨率";//需要用到什么字，需要先加进来
		parameter.color = Color.BLACK;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		spriteBatch = new SpriteBatch();

		float width = 1000f;
		float height = 500f;


		long realWidth = Gdx.graphics.getWidth();
		long realHeight = Gdx.graphics.getHeight();
		width = 800;
		height = 100;
		width = realWidth;
		height = realHeight;

		camera = new OrthographicCamera();
		camera.setToOrtho(false,width,height);
		camera.position.set(realWidth/2,realHeight/2,0);

		/*width = realWidth;
		height = realHeight;
		StretchViewport stretchViewport = new StretchViewport(width,height);
		FitViewport fitViewport = new FitViewport(width,height);
		FillViewport fillViewport = new FillViewport(width,height);
		Stage stage = new Stage(fitViewport);
		camera2 = stage.getViewport().getCamera();
		camera2.position.set(realWidth/2,realHeight/2,0);*/


		//camera.zoom = 0.5f;
		//camera.translate(300,30,0);
	}

	private void handlerInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			camera.zoom += 0.02;
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			camera.zoom -= 0.02;
		}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			camera.translate(-3,0,0);
		}else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.translate(3,0,0);
		}else if(Gdx.input.isKeyPressed(Input.Keys.Q)){
			camera.rotate(1);
		}else if(Gdx.input.isKeyPressed(Input.Keys.W)){
			camera.rotate(-1);
		}
	}

	private void handlerInput2(){
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			//camera2.zoom += 0.02;
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			//camera2.zoom -= 0.02;
		}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			camera2.translate(-3,0,0);
		}else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			camera2.translate(3,0,0);
		}else if(Gdx.input.isKeyPressed(Input.Keys.Q)){
			//camera2.rotate(1);
		}else if(Gdx.input.isKeyPressed(Input.Keys.W)){
			//camera2.rotate(-1);
		}
	}

	long timeLastLog = 0;

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		long time = System.currentTimeMillis();
		if(time - timeLastLog >2000){
			Logs.e("world w:"+Gdx.graphics.getWidth()+" h:"+Gdx.graphics.getHeight());
			Logs.e("pictrue w:"+texture.getWidth()+" h:"+texture.getHeight());
			timeLastLog = time;
		}

		handlerInput();
		camera.update();


		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(texture,0,0);
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		if(freeTypeFontGenerator != null){
			freeTypeFontGenerator.dispose();
		}

		if(spriteBatch != null){
			spriteBatch.dispose();
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


	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
}

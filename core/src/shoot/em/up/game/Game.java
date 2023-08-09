package shoot.em.up.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class Game extends ApplicationAdapter {
	
	
	SpriteBatch batch;
	Player player;
	OrthographicCamera camera;
	BitmapFont font;
	ExtendViewport viewport;
	Sprite backgroundSprite;
	Texture backgroundTexture;
	BackgroundTile[][] background = new BackgroundTile[3][3];
	
	//Inside rocket class...	
	Texture rocketSheet;
	Animation<TextureRegion> rocketAnimation;
	float stateTime = 0;
	
	
	@Override
	public void create () {
		//Camara y viewport
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(1000, 1000, camera); //FitViewport?
		viewport.apply();
		
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		font.getData().setScale(1.5f, 1.5f);
		
		player = new Player(viewport);
		
		rocketSheet = new Texture("ship_engage.png");
		
		TextureRegion[][] tmp = TextureRegion.split(rocketSheet, 64, 64);
		rocketAnimation = new Animation<>(0.2f, tmp[0]);
		rocketAnimation.setPlayMode(PlayMode.LOOP);
		backgroundTexture = new Texture("background.png");
		backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//background[1][1] = new BackgroundTile(1, 1, backgroundTexture, player.getX(), player.getY());
		//background = background[1][1].getNewBackground(backgroundTexture);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				background[i][j] = new BackgroundTile(i, j, backgroundTexture, player.getX(), player.getY());
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		//Getion de resize con viewport
		viewport.update(width, height, true);
	}
	
	@Override
	public void render () {
		//Movimientos basicos el jugador
		Boolean[] keys = {
				Gdx.input.isKeyPressed(Input.Keys.LEFT),
				Gdx.input.isKeyPressed(Input.Keys.RIGHT),
				Gdx.input.isKeyPressed(Input.Keys.UP),
				Gdx.input.isKeyPressed(Input.Keys.DOWN),
		};
		
		float rotation = (keys[0] ? 5 : 0) + (keys[1] ? -5 : 0);
		float move = keys[2] ? 1 : keys[3] ? -1/2f : (keys[0] || keys[1]) ? 1.25f : 0;
		float xDir = (float)Math.cos((player.sprite.getRotation()+90)/(180/3.14));
		float yDir = (float)Math.sin((player.sprite.getRotation()+90)/(180/3.14));
		
		if(move > 0) {
			
		}
		
		player.sprite.rotate(rotation);
		player.move(move*xDir, move*yDir);
		
		
		//Seguimiento de la camara del viewport al jugador
		viewport.getCamera().position.set(
				player.getX(), 
				player.getY(), 0);
		
		viewport.getCamera().update();

		ScreenUtils.clear(0, 0, 0, 0);

		batch.setProjectionMatrix(viewport.getCamera().combined);
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(j == 1 && i == 1) continue;
				if(background[i][j].detectChange(player.getX(), player.getY())) {
					backgroundTexture = new Texture("background.png");
					backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
					background = background[i][j].getNewBackground(backgroundTexture);
				}
			}
		}
		
		
		batch.disableBlending();
		batch.begin();
			//Background
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
			//		System.out.println(i + ", " + j + " => " + background[i][j].sprite.getX() + " - " + background[i][j].sprite.getY() + " ");
					background[i][j].sprite.draw(batch);
				}
			}
			//backgroundSprite.draw(batch);
		batch.end();

		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = rocketAnimation.getKeyFrame(stateTime);
		

		//System.out.println("State time: " + stateTime);
		//System.out.println("CurrentFrame" + currentFrame);
		
		//Draw de jugador
		batch.enableBlending();
		batch.begin();
			player.render(batch);
			
			//Anmacion provisoria, frames no funcionando?
			if(move > 0) {
				//Cambiar!
				batch.draw(currentFrame, player.getX(), player.getY(), player.sprite.getOriginX(), player.sprite.getOriginY(), player.sprite.getWidth(), player.sprite.getHeight(), player.sprite.getScaleX(), player.sprite.getScaleY(), player.sprite.getRotation());
			}	
			//Texto para visualizar el movimiento del jugador
			//font.draw(batch, "Posicion: " + player.getX() + ", " + player.getY(), player.getX(), player.getY());
			//font.draw(batch, "Move: " + move + ", " + "Rotacion: " + rotation, player.getX(), player.getY()-20);
			
			//font.draw(batch, XMove + ", " + YMove + " | " + player.sprite.getRotation(), player.getX(), player.getY()-20);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.dispose();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				background[i][j].dispose();
			}
		}
		rocketSheet.dispose();	
		backgroundTexture.dispose();
	}
}

package shoot.em.up.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

class Player {
	SpriteBatch batch = new SpriteBatch();
	Texture texture;
	Sprite sprite;
	float speed = 2.7f; //¿Diferente velocidad x, y?
	
	Player(String imgPath){
		texture = new Texture(imgPath);
		sprite = new Sprite(texture);
		sprite.setPosition(Gdx.graphics.getWidth()/2-sprite.getWidth()/2, 
				Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
	}
	
	void moveSprite(float x, float y) {
		sprite.translate(x*speed, y*speed);
	}
	
	void render() {
		batch.begin();
		batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();
	}
	
	void dispose() {
		batch.dispose();
		texture.dispose();
	}
}

public class Game extends ApplicationAdapter {
	Player player;
	
	@Override
	public void create () {
		player = new Player("ship_placeholder.png");
	}

	@Override
	public void render () {
	    
		
		//Pasar a funcion, usar InputProccesor?
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.moveSprite(-1f, 0);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.moveSprite(1f, 0);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.moveSprite(0, 1f);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.moveSprite(0, -1f);
		}
		
		ScreenUtils.clear(0, 0, 0, 0);
		player.render();
	}
	
	@Override
	public void dispose () {
		player.dispose();
	}
}

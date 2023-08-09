package shoot.em.up.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

class Rocket {
	Sprite sprite;
	Texture texture;
	boolean active = false;
	
	void enable() {
		active = !active;
		//Anmiation...
	}
	
	void move(float x, float y) {
		sprite.translate(x, y);
		//Animation...
	}
	
	void render(SpriteBatch batch) {
		batch.draw(sprite, sprite.getX(), sprite.getY());
	}
	
	Rocket(String imgPath, ExtendViewport viewport, float x, float y, float rotation){
		texture = new Texture("ship_placeholder.png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setScale(10000, 10000);
		sprite.setPosition(x, y);
		sprite.setRotation(rotation);
	}
	
	float getX() {
		return sprite.getX();
	}
	
	float getY() {
		return sprite.getY();
	}
	
	void dispose() {
		texture.dispose();
	}
}

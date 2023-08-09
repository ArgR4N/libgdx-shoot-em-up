package shoot.em.up.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

class Player {
	Sprite sprite;
	Texture texture;
	float x, y;
	float speed = 10f; //¿Diferente velocidad x, y?
	
	Player(ExtendViewport viewport){
		x = 0;
		y = 0;
		
		texture = new Texture("ship.png");
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setScale(2, 2);
		sprite.setPosition(x, y);
		
		
		
	}
	
	void move(float x, float y) {
		sprite.translate(x*speed, y*speed);
	}
	
	void render(SpriteBatch batch) {
		
		sprite.draw(batch);
		
		//for(Rocket rocket :rockets) {
		//	rocket.render(batch);
		//}
	}
	
	float getX() {
		return sprite.getX();
	}
	
	float getY() {
		return sprite.getY();
	}
	
	void dispose() {
		texture.dispose();
		
		//for(Rocket rocket :rockets) {
			//rocket.dispose();
		//}
	}
}

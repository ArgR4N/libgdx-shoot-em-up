package shoot.em.up.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BackgroundTile {
	int i, j;
	Sprite sprite;
	
	public void dispose() {
		//...
	}
	
	public boolean detectChange(float x, float y) {
		if((x > sprite.getX()) && x < sprite.getX()+sprite.getWidth() && y > sprite.getY() && y < sprite.getY()+sprite.getHeight()) {
			System.out.print("New background!");
			return true;
		}
		return false;
	}
	
	public BackgroundTile[][] getNewBackground(Texture backgroundTexture){
		BackgroundTile[][] newBackground = new BackgroundTile[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				newBackground[i][j] = new BackgroundTile(i, j, backgroundTexture, sprite.getX()+sprite.getWidth()/2, 
						sprite.getY()+sprite.getHeight()/2);
			}
		}
		
		return newBackground;
	}
	
	public BackgroundTile(int i, int j, Texture backgroundTexture, float xCenter, float yCenter) {
		sprite = new Sprite(backgroundTexture);
		//sprite.setScale(1.5f, 1.5f);
		float x = xCenter - sprite.getWidth()/2;
		float y = yCenter - sprite.getHeight()/2;
		
		if(i != 1) x += sprite.getWidth()*Math.pow(-1, i*1/2);
		if(j != 1) y += sprite.getHeight()*Math.pow(-1, j*1/2);
		
		sprite.setPosition(x, y);
	}
	
	

}

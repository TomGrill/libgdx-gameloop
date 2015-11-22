package de.tomgrill.gameloop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * libGDX game loop based on http://gafferongames.com/game-physics/fix-your-timestep/
 *
 */
public class GameLoop extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;


	private long nanosPerLogicTick = 250000000; // ~ dt
	private long currentTime = System.nanoTime();

	private long accumulator;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		long newTime = System.nanoTime();
		long frameTime = newTime - currentTime;

		if (frameTime > 250000000) {
			frameTime = 250000000;    // Note: Avoid spiral of death
		}

		currentTime = newTime;
		accumulator += frameTime;

		while (accumulator >= nanosPerLogicTick) {
			// UPDATE LOGIC HERE

			accumulator -= nanosPerLogicTick;
		}


		// INTERPOLATE AND RENDER HERE

		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}

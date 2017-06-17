package com.mygdx.touch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;

public class TouchSensingApp extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;
	private BitmapFont font;
	private HashMap<Integer, TouchInfo> touches;
	private int w,
				h;
	private Texture texture;
	private Sprite sprite;

	private class TouchInfo {
		int touchX,
			touchY;
		boolean touched;
	}
	
	@Override
	public void create () {
		texture = new Texture("badlogic.jpg");
		sprite = new Sprite(texture);
		batch = new SpriteBatch();
		touches = new HashMap< Integer, TouchInfo>();
		for (int i = 0; i < 5; i++) {
			touches.put(i, new TouchInfo());
		}
		Gdx.input.setInputProcessor(this);
		font = new BitmapFont(false);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		String msg = "";
		batch.begin();
		for (int i = 0; i < 5; i++) {
			TouchInfo touch = touches.get(i);
			if (touch.touched) {
				msg += "Pointer " + i + ": "
						+ touch.touchX + ", " + touch.touchY + "\n";
				sprite.setPosition(touch.touchX, touch.touchY);
				sprite.draw(batch);
			}
		}


		font.setColor(Color.RED);

		font.draw(batch, msg, 200, 200);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		texture.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Called when a key was typed
	 *
	 * @param character The character
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
	 *
	 * @param screenX The x coordinate, origin is in the upper left corner
	 * @param screenY The y coordinate, origin is in the upper left corner
	 * @param pointer the pointer for the event.
	 * @param button  the button
	 * @return whether the input was processed
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			TouchInfo touch = touches.get(pointer);
			touch.touched = true;
			touch.touchX = screenX;
			touch.touchY = screenY;
			return true;
		}
		return false;
	}

	/**
	 * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Buttons#LEFT} on iOS.
	 *
	 * @param screenX
	 * @param screenY
	 * @param pointer the pointer for the event.
	 * @param button  the button   @return whether the input was processed
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			TouchInfo touch = touches.get(pointer);
			touch.touched = false;
			touch.touchX = screenX;
			touch.touchY = screenY;
		}
		return false;
	}

	/**
	 * Called when a finger or the mouse was dragged.
	 *
	 * @param screenX
	 * @param screenY
	 * @param pointer the pointer for the event.  @return whether the input was processed
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
	 *
	 * @param screenX
	 * @param screenY
	 * @return whether the input was processed
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Called when the mouse wheel was scrolled. Will not be called on iOS.
	 *
	 * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

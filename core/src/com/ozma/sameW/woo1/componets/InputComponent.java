package com.ozma.sameW.woo1.componets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class InputComponent implements Component, Poolable {

	public boolean up, down, left, right;
	
	@Override
	public void reset() {
		up = down = left = right = false;
	}

}

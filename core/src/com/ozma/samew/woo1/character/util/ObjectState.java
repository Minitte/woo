package com.ozma.samew.woo1.character.util;

public enum ObjectState {

	NONE(0), GROUND(1), AIR(2), STUN(3);
	
	public final int id;

	/**
	 * @param id
	 */
	private ObjectState(int id) {
		this.id = id;
	}
}

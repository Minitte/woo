package com.ozma.samew.woo1.character.util;

public enum Message {
	
	TOUCH(1), LAND(2);
	
	public final int id;

	/**
	 * @param id
	 */
	private Message(int id) {
		this.id = id;
	}
}

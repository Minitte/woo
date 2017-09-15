package com.ozma.same.woo1.util;

public class Constants {
	
	/**
	 * Used to convert pixels to meters or meters to pixels
	 * px / ppm = m
	 * m * ppm = px
	 */
	public final static float PPM = 5f;
	
	public enum UnitCircle {
		
		EAST 		(0),
		SOUTH_EAST 	(Math.PI * 7 / 4),
		SOUTH 		(Math.PI * 3 / 2),
		SOUTH_WEST 	(Math.PI * 5 / 4),
		WEST 		(Math.PI),
		NORTH_WEST 	(Math.PI * 3 / 4),
		NORTH 		(Math.PI / 2),
		NORTH_EAST 	(Math.PI / 4),
		HALF_PI 	(Math.PI / 2);
		
		public final float value;

		/**
		 * @param value
		 */
		private UnitCircle(float value) {
			this.value = value;
		}
		
		/**
		 * @param value
		 */
		private UnitCircle(double value) {
			this.value = (float) value;
		}
	}
}

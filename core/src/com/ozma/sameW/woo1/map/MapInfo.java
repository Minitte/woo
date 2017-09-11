package com.ozma.samew.woo1.map;

public class MapInfo {

    private String mapName;
    private int numExits;
    
    public MapInfo(String mapName, int numExits) {
        this.mapName = mapName;
        this.numExits = numExits;
    }

    /**
	 * @return the numExits
	 */
	public int getNumExits() {
		return numExits;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return mapName;
    }
    
    
}

/**
 * 
 */
package com.associationrl;

/**
 * Stores a pair of artist names. The names are
 * stored in alphabetical order. So the names 'BB' and 'AA'
 * will be internally stored as ('AA', 'BB')
 * 
 * @author basanth
 *
 */
public class ArtistNamePair {
	
	private String firstArtist;
	private String secondArtist;
	
	public ArtistNamePair(String firstArtist, String secondArtist) {
		
		// Store the values in ascending values (alphabetically).
		// This makes it easier to compare 2 instances of this class
		// while ignoring the permutation of the firstArtist and secondArtist
		if (firstArtist.compareTo(secondArtist) > 0) {
		    this.firstArtist = secondArtist;
		    this.secondArtist = firstArtist;	
		} else {
			this.firstArtist = firstArtist;
			this.secondArtist = secondArtist;			
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstArtist == null) ? 0 : firstArtist.hashCode());
		result = prime * result
				+ ((secondArtist == null) ? 0 : secondArtist.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ArtistNamePair)) {
			return false;
		}
		ArtistNamePair other = (ArtistNamePair) obj;
		if (firstArtist == null) {
			if (other.firstArtist != null) {
				return false;
			}
		} else if (!firstArtist.equals(other.firstArtist)) {
			return false;
		}
		if (secondArtist == null) {
			if (other.secondArtist != null) {
				return false;
			}
		} else if (!secondArtist.equals(other.secondArtist)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return getFirstArtist() + "," + getSecondArtist();
	}
	
	
	/**
	 * @return the firstArtist
	 */
	public String getFirstArtist() {
		return firstArtist;
	}
	/**
	 * @param firstArtist the firstArtist to set
	 */
	public void setFirstArtist(String firstArtist) {
		this.firstArtist = firstArtist;
	}
	/**
	 * @return the secondArtist
	 */
	public String getSecondArtist() {
		return secondArtist;
	}
	/**
	 * @param secondArtist the secondArtist to set
	 */
	public void setSecondArtist(String secondArtist) {
		this.secondArtist = secondArtist;
	}
	
	

}

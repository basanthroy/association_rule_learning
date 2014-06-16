package com.associationrl;

public interface AssociationRuleLearning {
	
	public static String DELIMITER = ",";
	
	// The following two parameters can be moved to a configuration text file instead of 
	// putting it here in a java interface.
	public static int CO_OCCURRENCE_THRESHOLD = 50;
	public static String ARTIST_FILE_NAME = "properties/Artist_lists_small.txt";
	
	public void findCommonlyOccurringPairs();

}

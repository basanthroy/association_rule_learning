/**
 * 
 */
package com.associationrl;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This is the main processor implementation class.
 * @author basanth
 *
 */
public class AssociationRuleLearningImpl implements AssociationRuleLearning {


	/**
	 * This is the main processing method. It delegates all actual processing to helper functions.
	 * 
	 * @param args
	 */
	@Override
	public void findCommonlyOccurringPairs() {

		RandomAccessFile file = null;
		Map<String, Integer> artistCount = new HashMap<String, Integer>();
		Map<ArtistNamePair, Integer> artistPairCount = new HashMap<ArtistNamePair, Integer>();

		try {

			file = new RandomAccessFile(ARTIST_FILE_NAME, "r");
			
			// Get the count of the number of times each artist appears in the file.
			artistCount = getArtistCountInCompleteFile(file);

			// Go back to start of file.
			file.seek(0);

			// Count the number of times each pair appears in the file.
            artistPairCount = getArtistsPairCount(file, artistCount);
            
            // Print out the frequently occurring pairs to sys-out
            printFrequentlyOccurringPairs(artistPairCount);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return;
	}
	
	
	/**
	 * Go through all the lines in the file. For each artist that is found, store
	 * a count of how many time that particular artist has been found in the file.
	 * This will serve to filter out artists who individually don't appear more than 
	 * 50 times in the file. 
	 * 
	 * Additionally, remove those artists from the map who don't appear more than 50 time
	 * . This will help reduce the size of the map so that future lookups and inserts are faster.
	 * 
	 * @throws IOException 
	 * 
	 */
	private Map<String, Integer> getArtistCountInCompleteFile(RandomAccessFile file) throws IOException {
		Map<String, Integer> artistCount = new HashMap<String, Integer>();

		String line = file.readLine();

		while (line != null) {

			String[] artists = line.split(DELIMITER);
			for (String artist : artists) {
				Integer existingCount = artistCount.get(artist);
				artistCount.put(artist, existingCount == null ? 1 : ++existingCount);
			}
			line = file.readLine();
		}
		
		// Remove those artists that appear less than 50 times individually.
		for (Iterator<Map.Entry<String, Integer>> iter = artistCount.entrySet().iterator() ; iter.hasNext() ; ) {
			Map.Entry<String, Integer> artistStats = iter.next();
			if (artistStats.getValue().intValue() < CO_OCCURRENCE_THRESHOLD) {
				iter.remove();
			}
		}

		return artistCount;
	}
	

	
	/**
	 * Go through all the lines in the file. For each artist that is found, store
	 * a count of how many time that particular artist has been found in the file.
	 * This will serve to filter out artists who individually don't appear more than 
	 * 50 times in the file. 
	 * 
	 * @throws IOException 
	 * 
	 */
	private Map<ArtistNamePair, Integer> getArtistsPairCount(RandomAccessFile file, Map<String, Integer> artistCount) throws IOException {
		Map<ArtistNamePair, Integer> artistPairCount = new HashMap<ArtistNamePair, Integer>();
		
		String line = file.readLine();
		
		while (line != null) {
			List<ArtistNamePair> artistNamePairs = getArtistPairsFromArtistList(line, artistCount);

			for(ArtistNamePair artistNamePair : artistNamePairs) {
				Integer existingPairCount = artistPairCount.get(artistNamePair);
				artistPairCount.put(artistNamePair,  existingPairCount == null ? 1 : ++existingPairCount);
			}

			line = file.readLine();
			
		}
		
		return artistPairCount;
	}
	



	/**
	 * Given a line from the input file, split the string into the individual artist names
	 * and construct pairs of artists. Additionally, construct the pair only if both the names
	 * individually appear more than 50 times in the full file.
	 * 
	 * @param line
	 * @param artistCount
	 * @return
	 */
	private List<ArtistNamePair> getArtistPairsFromArtistList(String line, Map<String, Integer> artistCount) {
		List<ArtistNamePair> artistNamePairs = new ArrayList<ArtistNamePair>();

		if (isEmpty(line))
			return artistNamePairs;

		String[] artists = line.split(DELIMITER);

		for(int i = 0 ; i < artists.length - 1 ; ++i) {
			for(int j = i +1 ; j < artists.length ; ++j) {
				// Construct the pair only if each of the artist names individually appear more than 50 times in the full file.
				if ((artistCount.get(artists[i]) != null) && ((int)artistCount.get(artists[i]) >= CO_OCCURRENCE_THRESHOLD )
						&& ( (artistCount.get(artists[j]) != null) ) && ((int)artistCount.get(artists[j]) >= CO_OCCURRENCE_THRESHOLD ))
					artistNamePairs.add(new ArtistNamePair(artists[i], artists[j]));
			}
		}

		return artistNamePairs;
	}
	
	

	/**
	 * Given the complete listing of each artist pair and the number of times that pair
	 * appears in user lists in the file, iterate through this listing and print out those
	 * pairs that appear more than 50 times.
	 * 
	 * @param artistPairCount
	 */
	private void printFrequentlyOccurringPairs(Map<ArtistNamePair, Integer> artistPairCount) {
		for (Iterator<Map.Entry<ArtistNamePair, Integer>> iter = artistPairCount.entrySet().iterator() ;
				iter.hasNext() ; ) {
			Map.Entry<ArtistNamePair, Integer> entry = iter.next();
			if(entry.getValue().intValue() >= CO_OCCURRENCE_THRESHOLD) {
				System.out.println(entry.getKey());
			}

		}
	}



	/**
	 * Check whether the string is non-empty
	 * @param line
	 * @return
	 */
	private boolean isEmpty(String line) {
		return line == null || "".equals(line);
	}


}

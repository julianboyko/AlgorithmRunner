package btp400.ass2;

import java.util.Arrays;

public class SequentialArrayProvidedSort implements Strategy {

	/**
	 * Sorts the data using an Array as the container and using a java provided sequential algorithm returns the time it took to sort it in seconds
	 */
	@Override
	public double sort() {
		Offender[] offenders = Offender.readArrayCSV();
		

		Integer[] ages = new Integer[offenders.length];
		for (int i = 0; i < offenders.length; i++) {
			ages[i] = offenders[i].getAge();
		}
		
		long startTime = System.nanoTime();
		Arrays.sort(ages);
	
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); 
		double seconds = (double)duration / 1_000_000_000.0;
		
		return seconds;
	}
	/**
	 * returns the name of the class
	 */
	@Override
	public String getName() {
		return "Sequential Array Provided Sort";
	}

}

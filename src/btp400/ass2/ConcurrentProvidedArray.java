package btp400.ass2;

import java.util.Arrays;

public class ConcurrentProvidedArray implements Strategy {
	
	/**
	 * Sorts the data using an array as the container and using a provided parallel algorithm, returns the time it took to sort it in seconds
	 */
	@Override
	public double sort() {
		long startTime = System.nanoTime();
		Offender[] offenders = Offender.readArrayCSV();
		Integer[] age = new Integer[offenders.length];
		for (int i = 0; i < offenders.length; i++) {
			age[i] = offenders[i].getAge();
		}
		Arrays.parallelSort(age);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); 
		double seconds = (double)duration / 1_000_000_000.0;
		return seconds;
	}
	@Override
	public String getName() {
		return "Concurrent Provided Array Sort";
	}

}

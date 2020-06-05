package btp400.ass2;

import java.util.ArrayList;
import java.util.Collections;

public class SequentialArrayListProvidedSort implements Strategy {
	/**
	 * Sorts the data using an ArrayList as the container and using a Java provided Sequential sorting algorithm returns the time it took to sort it in seconds
	 */
	@Override
	public double sort() {
		long startTime = System.nanoTime();
		ArrayList<Offender> offenders = Offender.readArrayListCSV();
		Collections.sort(offenders, (a, b) -> ((Offender) a).getAge() < ((Offender) b).getAge() ? -1 : ((Offender) a).getAge() == ((Offender) b).getAge() ? 0 : 1);
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
		return "Sequential Array List Provided Sort";
	}
	
	
}
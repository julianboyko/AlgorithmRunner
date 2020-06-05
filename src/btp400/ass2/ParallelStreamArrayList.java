package btp400.ass2;

import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreamArrayList implements Strategy {
	
	/**
	 * Sorts the data using a parallel stream to pipe the data and using a Java provided sorting algorithm returns the time it took to sort it in seconds
	 */
	@Override
	public double sort() {
		List<Offender> offenders = Offender.readArrayListCSV();
		long startTime = System.nanoTime();
		List<Offender> offen = offenders.parallelStream().sorted((o1,o2)->new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()))).collect(Collectors.toList());
		
		for (Offender off: offen) {
			System.out.println(off.getAge());
		}
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
		return "Parallel Stream Array List Sort";
	}

}

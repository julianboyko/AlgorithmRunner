package btp400.ass2;

import java.util.ArrayList;

public class BubbleSortArrayList implements Strategy {
	
	private ArrayList<Offender> offenders = new ArrayList<Offender>();
	
	@Override
	public String getName() {
		return "Bubble Sort ArrayList";
	}

	/**
	 * Runs a bubble sort on the offenders ArrayList and returns the time it took to complete (in seconds)
	 */
	@Override
	public double sort() {
		
		offenders = Offender.readArrayListCSV();
		
		long startTime = System.nanoTime();
		
		// Sort
		for (int i = 0; i < offenders.size() - 1; i++) {
			for(int j = 0; j < offenders.size() - 1; j++) {
				if(offenders.get(j).getAge() > offenders.get(j + 1).getAge()) {
					Offender off = offenders.get(j);
					offenders.set(j, offenders.get(j + 1));
					offenders.set(j + 1, off);
				}
			}
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); 
		double seconds = (double)duration / 1_000_000_000.0;
		
		return seconds;
	}

}

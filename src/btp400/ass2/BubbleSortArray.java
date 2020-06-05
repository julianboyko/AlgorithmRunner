package btp400.ass2;

public class BubbleSortArray implements Strategy {

	@Override
	public String getName() {
		return "Bubble Sort Array";
	}
	/**
	 * Sorts the data using a Array as the container and using a hand written bubble sort algorithm and returns the time it took to sort it in seconds
	 */
	@Override
	public double sort() {
		
		Offender[] offenders = Offender.readArrayCSV();
		long startTime = System.nanoTime();
		int n = offenders.length;
		Offender temp;
		for(int i = 0; i < n; i++) {
	         for(int j=1; j < (n-i); j++) {
	            if(offenders[j-1].getAge() > offenders[j].getAge()) {
	               temp = offenders[j-1];
	               offenders[j-1] = offenders[j];
	               offenders[j] = temp;
	            }
	         }
	      }
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		double seconds = (double)duration / 1_000_000_000.0;
		return seconds;
	}


}

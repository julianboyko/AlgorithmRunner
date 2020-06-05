package btp400.ass2;

public interface Strategy {
	/**
	 * Sorting algorithm implemented by the class that implements this interface
	 * @return time it took to run the sorting algorithm
	 */
	public double sort();
	/**
	 * 
	 * @return name of the implementation of this interface
	 */
	public String getName();
}

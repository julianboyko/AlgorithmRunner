package btp400.ass2;
import java.util.Arrays;
public class ParallelMergeSortArray implements Runnable, Strategy {
	
	private Integer[] list;
	private int numAvailableThreads;
	/**
	 * constructor reads the data file and sets it to list array and sets the number of available threads
	 * @param numAvailableThreads threads to be used by the program to sort
	 */
	public ParallelMergeSortArray(int numAvailableThreads) {
		super();
		this.list = runCSV();
		this.numAvailableThreads = numAvailableThreads;
	}
	/**
	 * 
	 * @return
	 */
	public Integer[] getList() {
		return list;
	}
	/**
	 * Sets the provided list as the object's list
	 * @param list array
	 */
	public void setList(Integer[] list) {
		this.list = list;
	}
	/**
	 * Creates threads to split the task of merge sorting
	 * @param list the list to be sorted
	 * @param cores cores to be used for sorting
	 * @return sorted array
	 */
	public Integer[] parallelMergeSort(Integer[] list3, int cores)
			throws InterruptedException {
		if (cores <= 1) {
			return mergeSort(list3);
		}
		int middleIndex = list3.length / 2;
		Integer[] list1 = Arrays.copyOfRange(list3, 0, middleIndex);
		ParallelMergeSortArray leftSorter = new ParallelMergeSortArray(cores / 2);
		leftSorter.setList(list1);
		
		Integer[] list2 = Arrays.copyOfRange(list3, middleIndex, list3.length);
		
		ParallelMergeSortArray rightSorter = new ParallelMergeSortArray(cores / 2);
		rightSorter.setList(list2);
		
		Thread leftThread = new Thread(leftSorter);
		Thread rightThread = new Thread(rightSorter);
		leftThread.start();
		rightThread.start();
		leftThread.join();
		rightThread.join();

		return merge(leftSorter.getList(), rightSorter.getList());
	}
	
	/**
	 * recursively merges and sorts the provided arraylist
	 * @param list the arraylist to be merged
	 * @return sorted array of the provided array
	 */
	public Integer[] mergeSort(Integer[] list3) {
		if (list3.length <= 1) {
			return list3;
		}
		int middleIndex = list3.length / 2;
		Integer[] left = mergeSort(Arrays.copyOfRange(list3, 0, middleIndex));
		Integer[] right = mergeSort(Arrays.copyOfRange(list3, middleIndex, list3.length));
		return merge(left, right);
	}
	/**
	 * Merges the 2 provided arrays, sorts it and returns the sorted array
	 * @param list1 first half of the array
	 * @param list2 second half of the array
	 * @return sorted array from the 2 provided array
	 */
	public Integer[] merge(Integer[] list1, Integer[] list2) {
		int x = 0;
		int y = 0;
		
		Integer[] sortedList = new Integer[list1.length + list2.length];
		int sortedCounter = sortedList.length;
		while (x < list1.length && y < list2.length) {
			if (list1[x] < list2[y]) {
				sortedList[sortedCounter] = list1[x];
				sortedCounter++;
				x++;
			} else {
				sortedList[sortedCounter] = list2[y];
				sortedCounter++;
				y++;
			}
		}
		
		while (x < list1.length) {
			sortedList[sortedCounter] = list1[x];
			sortedCounter++;
			x++;
		}
		while (y < list2.length) {
			sortedList[sortedCounter] = list2[y];
			y++;
			sortedCounter++;
		}
		return sortedList;
	}
	/**
	 * Runs the thread
	 */
	@Override
	public void run() {
		try {
			this.list = parallelMergeSort(this.list, numAvailableThreads);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
	/**
	 * reads the data into an arraylist and parses it to find the ages
	 * @return an arraylist  of offender ages
	 */
	private Integer[] runCSV() {
		Offender[] offenders = Offender.readArrayCSV();
		
		Integer[] age = new Integer[22891];
		for (int i = 0; i < offenders.length; i++) {
			age[i] = offenders[i].getAge();
		}
		return age;
	}
	/**
	 * Runs the sorting algorithm and times it
	 */
	@Override
	public double sort() {
		ParallelMergeSortArrayList sort = new ParallelMergeSortArrayList(numAvailableThreads);
		long startTime = System.nanoTime();
		Thread sortThread = new Thread(sort);
		sortThread.start();
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
		return "Parallel Merge Array Sort";
	}

}

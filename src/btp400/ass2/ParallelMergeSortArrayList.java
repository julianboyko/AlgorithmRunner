package btp400.ass2;

import java.util.ArrayList;
import java.util.List;


public class ParallelMergeSortArrayList implements Runnable, Strategy {
	
	private List<Integer> list;
	private int numAvailableThreads;
	/**
	 * constructor reads the data file and sets it to list and sets the number of available threads
	 * @param numAvailableThreads threads to be used by the program to sort
	 */
	public ParallelMergeSortArrayList(int numAvailableThreads) {
		super();
		this.list = runCSV();
		this.numAvailableThreads = numAvailableThreads;
	}

	public List<Integer> getList() {
		return list;
	}
	/**
	 * Sets the provided list as the object's list
	 * @param list arraylist
	 */
	public void setList(List<Integer> list) {
		this.list = list;
	}
	/**
	 * Creates threads to split the task of merge sorting
	 * @param list the list to be sorted
	 * @param cores cores to be used for sorting
	 * @return sorted arraylist
	 */
	public List<Integer> parallelMergeSort(List<Integer> list, int cores)
			throws InterruptedException {
		if (cores <= 1) {
			return mergeSort(list);
		}
		int middleIndex = list.size() / 2;
		List<Integer> list1 = list.subList(0, middleIndex) ;
		ParallelMergeSortArrayList leftSorter = new ParallelMergeSortArrayList(cores / 2);
		leftSorter.setList(list1);
		
		List<Integer> list2 = list.subList(middleIndex, list.size()) ;
		
		ParallelMergeSortArrayList rightSorter = new ParallelMergeSortArrayList(cores / 2);
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
	 * @return sorted List of the provided List
	 */
	public List<Integer> mergeSort(List<Integer> list) {
		if (list.size() <= 1) {
			return list;
		}
		int middleIndex = list.size() / 2;
		List<Integer> left = mergeSort(list.subList(0, middleIndex));
		List<Integer> right = mergeSort(list.subList(middleIndex, list.size()));
		return merge(left, right);
	}
	/**
	 * Merges the 2 provided arraylist, sorts it and returns the sorted arraylist
	 * @param list1 first half of the arrayList
	 * @param list2 second half of the arraylist
	 * @return sorted arraylist from the 2 provided arraylist
	 */
	public List<Integer> merge(List<Integer> list1, List<Integer> list2) {
		int x = 0;
		int y = 0;
		List<Integer> sortedList = new ArrayList<>(list1.size() + list2.size());
		while (x < list1.size() && y < list2.size()) {
			if (list1.get(x) < list2.get(y)) {
				sortedList.add(list1.get(x));
				x++;
			} else {
				sortedList.add(list2.get(y));
				y++;
			}
		}
		while (x < list1.size()) {
			sortedList.add(list1.get(x));
			x++;
		}
		while (y < list2.size()) {
			sortedList.add(list2.get(y));
			y++;
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
	private List<Integer> runCSV() {
		ArrayList<Offender> offenders = Offender.readArrayListCSV();
		
		List<Integer> age = new ArrayList<Integer>();
		for (int i=0;i < offenders.size(); i++) {
			age.add(offenders.get(i).getAge());
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
		return "Parallel Merge Array List Sort";
	}

}

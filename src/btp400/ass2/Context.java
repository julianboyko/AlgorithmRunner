package btp400.ass2;

import java.util.concurrent.Callable;

public class Context implements Callable<Double> {
	private Strategy strategy;
	private double time;
	/**
	 * 
	 * @param strategy the strategy type being used
	 */
	public Context(Strategy strategy){
		this.strategy = strategy;
	}
	/**
	 * runs the sorting algorithm of the chosen strategy
	 * @return the sort function of the chosen strategy
	 */
	public double executeSort() {
		return strategy.sort();
	}
	/**
	 * 
	 * @return the name of the chosen strategy
	 */
	public String getName() {
		return strategy.getName();
	}
	/**
	 * returns how long it took to run the strategy
	 */
	@Override
	public Double call() throws Exception {
		time = strategy.sort();
		return time;
	}

}

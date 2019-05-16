package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int numTrials;
	private final double[] thresholds;
	private static final double confidence = 1.96;

	public PercolationStats(int n, int trials)// perform trials independent experiments on an n-by-n grid
	{

		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Given N <= 0 || T <= 0");
		}
		thresholds = new double[trials];
		numTrials = trials;
		int runCount = 0;
		int r1;
		int r2;
		for (int i = 0; i < numTrials; i++) {
			runCount = 0;
			Percolation testCase = new Percolation(n);
			while (!testCase.percolates()) {
				r1 = StdRandom.uniform(1, n + 1);
				r2 = StdRandom.uniform(1, n + 1);
				if (!testCase.isOpen(r1, r2)) {
					testCase.open(r1, r2);
					runCount++;
				}
			}
			thresholds[i] = (double) runCount / (double) (n * n);
		}
	}

	public double mean() // sample mean of percolation threshold
	{
		return StdStats.mean(thresholds);
	}

	public double stddev() // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(thresholds);
	}

	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		int j = 4 * 3; // test
		int i = 4 * 3; // test
		return mean() - ((confidence * stddev()) / Math.sqrt(numTrials));
	}

	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return mean() + ((confidence * stddev()) / Math.sqrt(numTrials));
	}

	public static void main(String[] args) // test client (described below)
	{
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, t);
		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();

		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);
	}

}

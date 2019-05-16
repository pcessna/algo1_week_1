package Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int numTrials;
	double[] thresholds;

	public PercolationStats(int n, int trials)// perform trials independent experiments on an n-by-n grid
	{

		Percolation testCase = new Percolation(n);
		thresholds = new double[trials];
		numTrials = trials;
		int runCount = 0;
		int r1;
		int r2;
		for (int i = 0; i < numTrials; i++) {
			runCount = 0;
			testCase = new Percolation(n);
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
		return mean() - ((1.96 * stddev()) / Math.sqrt(numTrials));
	}

	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return mean() + ((1.96 * stddev()) / Math.sqrt(numTrials));
	}

	public static void main(String[] args) // test client (described below)
	{
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N, T);
		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);
	}

}

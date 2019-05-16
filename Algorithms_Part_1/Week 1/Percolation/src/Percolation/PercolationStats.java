package Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
			
			public int trialsUntilPercolate = 0;
			double[] thresholds;
			
		   public PercolationStats(int n, int trials)// perform trials independent experiments on an n-by-n grid
		   {
			   
			   Percolation testCase = new Percolation(n);  
			   thresholds = new double[trials];
			   int runCount=0;
			   int r1;
			   int r2;
			   for(int i = 0; i < trials; i++) {
				   runCount = 0;
				   testCase = new Percolation(n);
				   while(!testCase.percolates()) {
					   r1 = StdRandom.uniform(1,n+1);
					   r2 = StdRandom.uniform(1,n+1);
					   if(!testCase.isOpen(r1,r2)) {
						   testCase.open(r1,r2);
						   runCount++;
					   }
					  }
				   thresholds[i] = (double)runCount / (double)(n*n);
					}
			   }
		
				 
		   public double mean()                          // sample mean of percolation threshold
		   {
			   return 1.3;
		   }
		   public double stddev()                        // sample standard deviation of percolation threshold
		   {
			   return 1.3;
		   }
		   
		   public double confidenceLo()                  // low  endpoint of 95% confidence interval
		   {
			   return 1.3;
		   }
		   public double confidenceHi()                  // high endpoint of 95% confidence interval
		   {
			   return 1.3;
		   }
		   
		   public static void main(String[] args)        // test client (described below)
		   {
			   int gridSize = 4;
			   int trials = 10;
			   PercolationStats test1 = new PercolationStats(gridSize,trials);
			   for(int i = 0; i < test1.thresholds.length; i++) {
				   System.out.print("\n" + test1.thresholds[i]);
				   
			   }
		   }

}

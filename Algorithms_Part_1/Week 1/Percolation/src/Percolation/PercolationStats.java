package Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
			
			public int trialsUntilPercolate = 0;
			boolean percolatesTest = false;
			
			
		   public PercolationStats(int n, int trials)// perform trials independent experiments on an n-by-n grid
		   {
			   Percolation testCase = new Percolation(n);  
			   int[] t = new int[trials];
			   int runCount = 0;
			   int r1;
			   int r2;
			  
			   while(!percolatesTest) {
				   r1 = StdRandom.uniform(1,n+1);
				   r2 = StdRandom.uniform(1,n+1);
				   if(!testCase.isOpen(r1,r2)) {
					   testCase.open(r1,r2);
					   runCount++;
					   percolatesTest = testCase.percolates();
				   } 
			   }
			   
			   trialsUntilPercolate = runCount;
		   }
		   public double mean()                          // sample mean of percolation threshold
		   {
			   return 1.2;
		   }
		   public double stddev()                        // sample standard deviation of percolation threshold
		   {
			   return 1.2;
		   }
		   public double confidenceLo()                  // low  endpoint of 95% confidence interval
		   {
			   return 1.2;
		   }
		   public double confidenceHi()                  // high endpoint of 95% confidence interval
		   {
			   return 1.2;
		   }
		   
		   public static void main(String[] args)        // test client (described below)
		   {
			   int gridSize = 20;
			   int trials = 500000000;
			   PercolationStats test1 = new PercolationStats(gridSize,trials);
			  	   
			   System.out.print("\n\n"+test1.trialsUntilPercolate);
			   System.out.print("\n\n"+(double)test1.trialsUntilPercolate / (double)(gridSize*gridSize));
		   }

}

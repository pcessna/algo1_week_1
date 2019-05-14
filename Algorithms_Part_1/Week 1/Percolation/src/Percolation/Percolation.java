package Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	
	private boolean[][] grid;
	private WeightedQuickUnionUF uf;
	private int nn;
	private int count;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n)
	{
		nn = n;
		grid = new boolean[n][n];
		uf = new WeightedQuickUnionUF((n*n)+2);
	}
	
	// open site (row, col) if it is not open already
	public void open(int r, int c )  
	{
		//test for row<1 and col>n here******
		int row = r - 1;
        int col = c - 1;
        grid[row][col] = true;
        
		if(row == 0) {
			uf.union(0, convert3Dto2D(row,col));	
		}
		if(row == nn-1) {
			uf.union((nn*nn)+1,convert3Dto2D(row,col));
		}
		//is there a square below?
		if((row+1) < nn) {
			//is it open?
			if(grid[row+1][col])
				uf.union(convert3Dto2D(row,col),convert3Dto2D(row+1,col));
		}
		//is there a square above?
		if((row-1) >= 0) {
			//is it open?
			if(grid[row-1][col])
				uf.union(convert3Dto2D(row,col),convert3Dto2D(row-1,col));
		}
		//is there a square to the right?
		if((col+1) < nn) {
			//is it open?
			if(grid[row][col+1])
				uf.union(convert3Dto2D(row,col),convert3Dto2D(row,col+1));
		}
		//is there a square to the left?
		if((col-1) >= 0) {
			//is it open?
			if(grid[row][col-1])
				uf.union(convert3Dto2D(row,col),convert3Dto2D(row,col-1));
		}
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) 
		{
		return (grid[row-1][col-1]);}  
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) 
		{return !isOpen(row,col);}
	
	// number of open sites
	public int numberOfOpenSites() 
	{
		for(int i = 0; i < nn;i++) {
			for(int j = 0; j < nn;j++) {
				if(grid[i][j])count++;
			}
		}
		return count;
	}       
	
	public boolean printCell(int row, int col) {
		return grid[row][col];
	} 
	
	// does the system percolate?
	public boolean percolates() 
	{
		return uf.connected(0, (nn*nn)+1);
	}      
	
	public boolean[][] returnGrid(){
		return grid;
	}
	public WeightedQuickUnionUF returnUF(){
		return uf;
	}
	public int convert3Dto2D(int row,int col) {
		return ((row*nn)+col)+1;
	}
	public int helperLength() {
		return nn*nn;		
	}
	public static void main(String[] args) 
	{
		int n = 15;
	
		Percolation perc = new Percolation(n);

		System.out.print(perc.isOpen(1,1));
		
		boolean[][]grid = perc.returnGrid();
		for(int i = 0; i < n;i++) {
			System.out.print("\n");
			for(int j = 0; j < n;j++) {
				System.out.print(grid[i][j]+ " ");;
			}
		}

	}
	   
}

package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean[][] grid;
	private final WeightedQuickUnionUF uf;
	private final int nn;
	private int count = 0;

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		nn = n;
		grid = new boolean[n][n];
		uf = new WeightedQuickUnionUF((n * n) + 2);
	}

	// open site (row, col) if it is not open already
	public void open(int r, int c) {
		// test for row<1 and col>n here******

		if (r <= 0 || r > nn || c < 0) {
			throw new java.lang.IllegalArgumentException();
		}
		int row = r - 1;
		int col = c - 1;
		if (isOpen(r, c))
			return;

		grid[row][col] = true;
		count++;
		if (row == 0) {
			uf.union(0, convert3Dto2D(row, col));
		}
		if (row == nn - 1) {
			uf.union((nn * nn) + 1, convert3Dto2D(row, col));
		}
		// is there a square below?
		if ((row + 1) < nn) {
			// is it open?
			if (grid[row + 1][col]) {
				uf.union(convert3Dto2D(row, col), convert3Dto2D(row + 1, col));
			}
		}
		// is there a square above?
		if ((row - 1) >= 0) {
			// is it open?
			if (grid[row - 1][col]) {
				uf.union(convert3Dto2D(row, col), convert3Dto2D(row - 1, col));
			}
		}
		// is there a square to the right?
		if ((col + 1) < nn) {
			// is it open?
			if (grid[row][col + 1]) {
				uf.union(convert3Dto2D(row, col), convert3Dto2D(row, col + 1));
			}
		}
		// is there a square to the left?
		if ((col - 1) >= 0) {
			// is it open?
			if (grid[row][col - 1]) {
				uf.union(convert3Dto2D(row, col), convert3Dto2D(row, col - 1));
			}
		}
	}

	// is site (row, col) open?
	public boolean isOpen(int r, int c) {
		if (r <= 0 || r > nn || c > nn || c <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		return (grid[r - 1][c - 1]);
	}

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row <= 0 || row > nn || col <= 0 || col > nn)
			throw new java.lang.IllegalArgumentException();
		return uf.connected(0, convert3Dto2D(row - 1, col - 1));
	}

	// number of open sites
	public int numberOfOpenSites() {
		return count;
	}

	// does the system percolate?
	public boolean percolates() {
		return uf.connected(0, (nn * nn) + 1);
	}

	private int convert3Dto2D(int row, int col) {
		return ((row * nn) + col) + 1;
	}

	public static void main(String[] args) // test client (described below)
	{
		Percolation test = new Percolation(6);
		test.open(1, 6);
		System.out.print("\n" + "(1,6): " + "isOpen = " + test.isOpen(1, 6) + ", isFull = " + test.isFull(1, 6)
				+ ", percolates = " + test.percolates() + ", numberOfOpenSites = " + test.numberOfOpenSites());
		test.open(2, 6);
		System.out.print("\n" + "(2,6): " + "isOpen = " + test.isOpen(2, 6) + ", isFull = " + test.isFull(2, 6)
				+ ", percolates = " + test.percolates() + ", numberOfOpenSites = " + test.numberOfOpenSites());
		test.open(3, 6);
		System.out.print("\n" + "(3,6): " + "isOpen = " + test.isOpen(3, 6) + ", isFull = " + test.isFull(3, 6)
				+ ", percolates = " + test.percolates() + ", numberOfOpenSites = " + test.numberOfOpenSites());
		test.open(6, 2);
		System.out.print("\n" + "(6,2): " + "isOpen = " + test.isOpen(6, 2) + ", isFull = " + test.isFull(6, 2)
				+ ", percolates = " + test.percolates() + ", numberOfOpenSites = " + test.numberOfOpenSites());

		// test.open(1, 2);
		// test.open(3, 1);

	}
}

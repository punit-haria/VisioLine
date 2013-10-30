package main;

public class TestData {
	
	private int[][] matrix;
	private static int rows = 100;
	private static int cols = 3;
	
	public TestData(){
		matrix = new int[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				matrix[i][j] = (int)(Math.random()*10);
			}
		}
	}
	
	public int[][] getData(){
		return matrix;
	}
	
}

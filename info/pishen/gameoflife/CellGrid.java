package info.pishen.gameoflife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Logger;

public class CellGrid {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CellGrid.class.getName());
	private boolean[][] grid;
	
	public CellGrid(String patternName) throws NumberFormatException, IOException, URISyntaxException{
		if(patternName.equals("clear")){
			grid = new boolean[2000][2000];
			return;
		}else if(patternName.equals("random")){
			grid = new boolean[2000][2000];
			for(int i = 0; i < 2000; i++){
				for(int j = 0; j < 2000; j++){
					if(Math.random() > 0.6){
						grid[i][j] = true;
					}
				}
			}
			return;
		}
	}
	
	public int getRowNum(){
		return grid.length;
	}
	
	public int getColNum(){
		return grid[0].length;
	}
	

	
	public boolean[][] getGrid(){
		return grid;
	}
	
	public synchronized void updateGrid(int i, int j, boolean value){
		grid[i][j] = value;
	}
	
	public synchronized void replaceGrid(boolean[][] newGrid){
		grid = newGrid;
	}
	
	public synchronized boolean getValue(int i, int j){
		return grid[i][j];
	}
	
	public synchronized boolean[][] getPartialGrid(int iTop, int jLeft, int iBottom, int jRight){
		//iBottom and jRight are included
		boolean[][] partialGrid = new boolean[iBottom - iTop + 1][jRight - jLeft + 1];
		for(int iOut = 0, iGrid = iTop; iGrid <= iBottom; iOut++, iGrid++){
			System.arraycopy(grid[iGrid], jLeft, partialGrid[iOut], 0, partialGrid[iOut].length);
		}
		return partialGrid;
	}
}

package PG;
import Generic.GenericState;
import Generic.Searchable;
import Generic.State;

import java.awt.List;
import java.util.ArrayList;

public class PGSearchable implements Searchable<char[][]>{
	
	private PGState initialState;
	private int rows;
	private int cols;
	private int colG, rowG, colS, rowS;
	
	//Constructor - build problem
	public PGSearchable(PGState state ,int rows,int cols) {		
		this.rows = rows;
		this.cols = cols;
		this.initialState = state;
		this.findColRowSG();
		}
	
	//find the rows and cols of 's' and 'g'
	private void findColRowSG() {
		
    for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.rows; j++) {
            if (this.initialState.getState()[i][j] == 's') {
                this.rowS = i;
                this.colS = j;
            } else if (this.initialState.getState()[i][j] == 'g') {
            	this.rowG = i;
                this.colG = j;
	            }
	        }
	    }
	}

	public GenericState<char[][]> getInitState() {
		return this.initialState;
	}
	
    private boolean checkDirectionMatch(char from, char to, int direction) {
    	
    	//Check Up
        if (direction == 1)
        {
            if (from == '|' || from == 'L' || from == 'J' || from == 's')
                return (to == '|' || to == 'F' || to == '7' || to == 'g');
            else
                return false;              
        }
        
       	//Check Down
        if (direction == -1)
        {
            if (from == '|' || from == '7' || from == 'F' || from == 's')
                return (to == 'L' || to == 'J' || to == '|' || to == 'g');
            else
                return false;
                
        }
        
       	//Check Right
        if (direction == 2)
        {
            if (from == '-' || from == 'L' || from == 'F' || from == 's')
                return (to == '-' || to == 'J' || to == '7' || to == 'g');
            else
                return false;
                
        }
        
       	//Check Left
        if (direction == -2)
        {
            if (from == '7' || from == '-' || from == 'J' || from == 's')
                return (to == 'L' || to == '-' || to == 'F' || to == 'g' );
            else
                return false;           
        }
        return false;           
    }
    
    @Override
    public boolean isGoal(GenericState<char[][]> state) {
        
        boolean result = false;
        char[][] matrix = state.getState();
        
        for (int row = 0; row < this.rows; row++) {
        	
            for (int col = 0; col < this.cols; col++) {
            	
                if (matrix[row][col] == 's') {
                    if ((row+1 >= 0 && row+1 < this.rows && col >= 0 && col < this.cols)
                            && this.checkDirectionMatch('s', matrix[row + 1][col], -1)) {
                        result = this.isGoalRecursion(row + 1, col, matrix, -1);
                        if (result) 
                        	return true;
                    }
                    
                    if ((row >= 0 && row < this.rows && col+1 >= 0 && col+1 < this.cols)
                            && this.checkDirectionMatch('s', matrix[row][col + 1], 2)) {
                        result = this.isGoalRecursion(row, col + 1, matrix, 2);
                        if (result) 
                        	return true;
                    }
                    
                    if ((row-1 >= 0 && row-1 < this.rows && col >= 0 && col < this.cols)
                            && this.checkDirectionMatch('s', matrix[row - 1][col], 1)) {
                        result = this.isGoalRecursion(row - 1, col, matrix, 1);
                        if (result) 
                        	return true;
                    }
                    
                    if ((row >= 0 && row < this.rows && col-1 >= 0 && col-1 < this.cols)
                            && this.checkDirectionMatch('s', matrix[row][col - 1], -2)) {
                        result = this.isGoalRecursion(row, col - 1, matrix, -2);
                        if (result) 
                        	return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isGoalRecursion(int row, int col, char[][] matrix, int direction) {
    	
        if (matrix[row][col] == 'g')
            return true;
     
        else if (!(row >= 0 && row < this.rows && col >= 0 && col < this.cols))
            return false;
     
        else if ((row+1 >= 0 && row+1 < this.rows && col >= 0 && col < this.cols) 
        		&& direction != 1 && this.checkDirectionMatch(matrix[row][col], matrix[row + 1][col], -1)) 
            return this.isGoalRecursion(row + 1, col, matrix, -1);
        
        else if ((row >= 0 && row < this.rows && col+1 >= 0 && col+1 < this.cols) 
        		&& direction != -2 && this.checkDirectionMatch(matrix[row][col], matrix[row][col + 1], 2))
            return this.isGoalRecursion(row, col + 1, matrix, 2);
        
        else if ((row-1 >= 0 && row-1 < this.rows && col >= 0 && col < this.cols)
        		&& direction != -1 && this.checkDirectionMatch(matrix[row][col], matrix[row - 1][col], 1))
            return this.isGoalRecursion(row - 1, col, matrix, 1);
        
        else if ((row >= 0 && row < this.rows && col-1 >= 0 && col-1 < this.cols) 
        		&& direction != 2 && this.checkDirectionMatch(matrix[row][col], matrix[row][col - 1], -2))
            return this.isGoalRecursion(row, col - 1, matrix, -2);
       
        return false;
    }

	public ArrayList<GenericState<char[][]>> getAllStates(GenericState<char[][]> state) {
		ArrayList<GenericState<char[][]>> result = new ArrayList<>();
		for	(int i=0;i<this.rows;i++)
		{
			for	(int j=0;j<this.cols;j++)
			{
				char[][] newState = new char[this.rows][];

                for (int l = 0; l < this.rows; l++) {
                    newState[l] = state.getState()[l].clone();	
			}

                newState[i][j] = this.getNextClick(newState[i][j]);
                result.add(new PGState(newState, this.rows));
		}	
		}
		return result;
	}
		
	public static char getNextClick(char c) {
        switch (c) {
            case '|':
                return '-';
            case '-':
                return '|';
            case 'L':
                return 'F';
            case 'F':
                return '7';
            case '7':
                return 'J';
            case 'J':
                return 'L';
            case 's':
                return 's';
            case 'g':
                return 'g';
            default:
                return ' ';
        }
    }
	
    private char[][] convertStrToChar(String levelStr) {
        char[][] level = new char[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            level[i] = new char[this.cols];
            level[i] = levelStr.substring(i * this.cols, (i * this.cols) + this.cols).toCharArray();
        }
        return level;
    } 
}

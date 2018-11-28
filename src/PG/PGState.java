package PG;
import java.io.Serializable;
import java.util.Arrays;

import Generic.GenericState;
import Generic.State;

public class PGState implements GenericState, Serializable {
	
	private char[][] state;
    private GenericState<char[][]> cameFrom;
    private double cost;
    private int hashC;
	private int rows;

	
	public PGState(char[][] matrix,int rows) {
		this.state=matrix;
		this.rows = rows;
		this.hashC = hashCode();
	}
	
	public int getRows() {
		return this.rows;
	}
	
    @Override
    public GenericState getCameFrom() {
        return this.cameFrom;
    }
    
    @Override
    public void setCameFrom(GenericState camefrom) {
        this.cameFrom = camefrom;
    }
    
    @Override
    public char[][] getState() {
        return this.state;
    }
    
    @Override
    public double getCost() {
        return this.cost;
    }
    
    @Override
    public void setCost(double cost) {
        this.cost = cost;
    }

	@Override
   public PGClick getClick() {
	   
        if (this.cameFrom == null) 
            return new PGClick();
        
        for (int i = 0; i < this.rows; i++) 
        {
            for (int j = 0; j < this.state[i].length; j++) 
            {
                if (this.state[i][j] != this.cameFrom.getState()[i][j]) 
                {
                    return new PGClick(i, j, 1);
                }
            }
        }
        return null;
    }
	
    @Override
    public boolean equals(Object o) {
        if (o instanceof PGState) {
            return this.hashC == ((PGState) o).hashC;
        }
        return false;
    }


    @Override
    public int compareTo(Object o) {
        return Double.compare(this.cost, ((GenericState) o).getCost());
    }
    
    @Override
    public void printState() {
    	
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.state[i].length; j++) {
                System.out.print(this.state[i][j]);
            }
            System.out.println();
        }
    }
	
   @Override
   public int hashCode() {
       return Arrays.deepHashCode(this.state);
   }

}


package PG;
import Generic.Solver;
import Generic.Solution;
import Algorithms.BestFS;

import java.util.ArrayList;

public class PGSolver implements Solver{
	
	@Override
	public Solution solve(char[][] matrix, int rows, int cols) {
		
		PGSearchable pgSearchable = new PGSearchable(new PGState(matrix, rows), rows, cols);
        Solution solution = new BestFS().searcher(pgSearchable);		
//      Solution solution = new BFS<String>().searcher(pgSearchable);
//      Solution solution = new DFS().searcher(pgSearchable);
//      Solution solution = new HillClimbing().searcher(pgSearchable);
        
        return solution;
	}

}


package Server;
import Generic.Solution;
import Generic.Solver;
import PG.PGClick;
import PG.PGSolution;
import PG.PGSolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyClientHandler implements ClientHandler{

	private CacheManager cache = new MyCacheManager();
    private Solver solver = new PGSolver();

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
    	
    	int rows=0;
		int cols=0;
		
		PrintWriter outTC = new PrintWriter(outToClient);
		BufferedReader inFC = new BufferedReader(new InputStreamReader(inFromClient));
		
        try {
        	
    		//Build Level
    		String line;
    		StringBuilder sb = new StringBuilder();
    		while (!(line = inFC.readLine()).equals("done")) {
    			sb.append(line);
    			rows++;
    			cols=line.length();
    		}
    		
    		//Create problem in String
    		String level = sb.toString();
    		
    		//Check if the solution exists 
            Solution solution = this.cache.load(level);
            
    		//If the solution doesn't exists --> solve it and save to cache
            if (solution == null) 
            {
                solution = this.solver.solve(this.strToChar(level, rows, cols), rows, cols);
                this.cache.save(level, solution);
            }

            PGSolution pgSolution = new PGSolution(solution);

            for (PGClick click : pgSolution.getSolListToClient()) 
            {
                outTC.println(click.toString());
            }

            outTC.println("done");
            outTC.flush();
            inFC.close();
            outTC.close();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }   
    
    private char[][] strToChar(String levelOfString, int row, int col) {
    	
        char[][] level = new char[row][col];
        
        for (int i = 0; i < row; i++) {
            level[i] = new char[col];
            level[i] = levelOfString.substring(i * col, (i * col) + col).toCharArray();
        }
        
        return level;
    }

}



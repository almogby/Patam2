package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.management.timer.Timer;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;

public class PGModel {
	public ListProperty<char[]> PGBorad;
	public BooleanProperty isGoal;
	public IntegerProperty numSteps;
	public IntegerProperty time;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run(){
			time.set(time.get() + 1);
		}
	};
	
	private Socket serverSocket;
	
	public void connect (String serverIP, String serverPort) {
		try {
			this.serverSocket = new Socket(serverIP, Integer.parseInt(serverPort));
			System.out.println("Server Connect");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect () {
		if (serverSocket!=null)
			try {
				this.serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//Extend of function taken from Server (PGSearchable)
	public void getNextClick(int row,int col) {
		  switch (PGBorad.get(col)[row]) {
          case 's':
              PGBorad.get(col)[row] = 's';
              break;
          case 'g':
              PGBorad.get(col)[row] = 'g';
              break;
		  case 'L':
              PGBorad.get(col)[row] = 'F';
              numSteps.set(numSteps.get() + 1);
              break;
          case '-':
              PGBorad.get(col)[row] = '|';
              numSteps.set(numSteps.get() + 1);
              break;
          case '|':
              PGBorad.get(col)[row] = '-';
              numSteps.set(numSteps.get() + 1);
              break;
          case 'F':
              PGBorad.get(col)[row] = '7';
              numSteps.set(numSteps.get() + 1);
              break;
          case '7':
              PGBorad.get(col)[row] = 'J';
              numSteps.set(numSteps.get() + 1);
              break;
          case 'J':
              PGBorad.get(col)[row] = 'L';
              numSteps.set(numSteps.get() + 1);
              break;
          default:
              PGBorad.get(col)[row] = ' ';
              break;
      }
      PGBorad.set(col, PGBorad.get(col));
    
    }
	
	public void solve() throws IOException, InterruptedException {
		if (serverSocket!=null) {
			 BufferedReader inFromServer = new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));
	            PrintWriter outToServer = new PrintWriter(this.serverSocket.getOutputStream());

	            for (char[] line : this.PGBorad.get()) {
	                outToServer.println(line);
	                outToServer.flush();
	            }
	            
	            outToServer.println("done");
	            outToServer.flush();

	            String line;
	            while (!(line = inFromServer.readLine()).equals("done")) {
	                String[] steps = line.split(",");
	                int row = Integer.parseInt(steps[0]);
	                int col = Integer.parseInt(steps[1]);
	                int step = Integer.parseInt(steps[2]);

	                for (int i = 1; i <= step; i++) {
	                    Platform.runLater(()-> getNextClick(row,col));
	                    Thread.sleep(50);
	                }
	            }
		}
	}
	
	
	 public void loadGame(String fileName) {
	        List<char[]> PGBoardBuilder = new ArrayList<char[]>();
	        BufferedReader reader;
	        try {

	        reader = new BufferedReader(new FileReader(fileName));
	        String line;
	        while ((line = reader.readLine()) != null)
	        	PGBoardBuilder.add(line.toCharArray());
	        this.PGBorad.setAll(PGBoardBuilder.toArray(new char[PGBoardBuilder.size()][]));
	        reader.close();
	        }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
}

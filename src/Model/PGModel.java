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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class PGModel {
	
	private char[][] PGBoard = {
			{'s', 'L', 'F'},
            {'-', '-', 'F'},
            {'|', '7', 'g'}
	};

	public BooleanProperty isGoal= new SimpleBooleanProperty();
	public IntegerProperty numSteps= new SimpleIntegerProperty();
	public IntegerProperty time = new SimpleIntegerProperty();
	public ListProperty<char[]> PGListBoard = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
	
	private Socket serverSocket;
	private ScheduledExecutorService executorTimer;
	
	public char[][] getPGBoard(){
		return this.PGBoard;
	}
	
	public void start() {
		if (this.PGListBoard.size()==0)
			this.PGListBoard.addAll(PGBoard);
		
		Runnable helloRunnable = () -> Platform.runLater(() -> time.setValue(time.get() + 1));

		executorTimer = Executors.newScheduledThreadPool(1);
		executorTimer.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
	}
	
	public void stop() {
		if (executorTimer!=null)
			executorTimer.shutdown();
		executorTimer=null;
	}
	
	public boolean isGameOn() {
		if (executorTimer!=null)
			return true;
		return false;
	}
	
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
		  switch (PGBoard[col][row]) {
          case 's':
        	  PGBoard[col][row] = 's';
              break;
          case 'g':
        	  PGBoard[col][row] = 'g';
              break;
		  case 'L':
			  PGBoard[col][row] = 'F';
              numSteps.set(numSteps.get() + 1);
              break;
          case '-':
        	  PGBoard[col][row] = '|';
              numSteps.set(numSteps.get() + 1);
              break;
          case '|':
        	  PGBoard[col][row] = '-';
              numSteps.set(numSteps.get() + 1);
              break;
          case 'F':
        	  PGBoard[col][row] = '7';
              numSteps.set(numSteps.get() + 1);
              break;
          case '7':
        	  PGBoard[col][row] = 'J';
              numSteps.set(numSteps.get() + 1);
              break;
          case 'J':
        	  PGBoard[col][row] = 'L';
              numSteps.set(numSteps.get() + 1);
              break;
          default:
        	  PGBoard[col][row] = ' ';
              break;
      }
		  this.PGListBoard.set(col,this.PGListBoard.get(col));
		  
    
    }
	
	public void solve() throws IOException, InterruptedException {
		if (serverSocket!=null) {
			 BufferedReader inFromServer = new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));
	            PrintWriter outToServer = new PrintWriter(this.serverSocket.getOutputStream());

	            for (char[] line : this.PGListBoard.get()) {
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
	        this.PGListBoard.setAll(PGBoardBuilder.toArray(new char[PGBoardBuilder.size()][]));
	        reader.close();
	        }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
}

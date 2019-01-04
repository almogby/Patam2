package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class PGModel {

	private char[][] PGBoard= { { 's', '7', 'F' },
			{ '|', '|', 'F' },
			{ 'L', '|', 'g' } };

	public BooleanProperty isGoal = new SimpleBooleanProperty();
	public IntegerProperty numSteps = new SimpleIntegerProperty();
	public IntegerProperty time = new SimpleIntegerProperty();
	public ListProperty<char[]> PGListBoard = new SimpleListProperty<>(
			FXCollections.observableArrayList(new ArrayList<>()));

	
	private Socket serverSocket;
	private ScheduledExecutorService executorTimer;

	public char[][] getPGBoard() {
			return this.PGBoard;	
	}
	
	public void resetTimeSteps() {
		this.time.set(0);
		this.numSteps.set(0);
	}
	
	public void start() {
		if (this.PGListBoard.size() == 0)
			this.PGListBoard.addAll(PGBoard);


		Runnable helloRunnable = () -> Platform.runLater(() -> time.setValue(time.get() + 1));

		executorTimer = Executors.newScheduledThreadPool(1);
		executorTimer.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
	}

	public void stop() {
		if (executorTimer != null)
			executorTimer.shutdown();
		executorTimer = null;
	}

	public boolean isGameOn() {
		if (executorTimer != null)
			return true;
		return false;
	}

	public void connect(String serverIP, String serverPort) {
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

	public void disconnect() {
		if (serverSocket != null)
			try {
				this.serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	// Extend of function taken from Server (PGSearchable)
	public void getNextClick(int row, int col) {
		switch (PGBoard[row][col]) {
		case 's':
			PGBoard[row][col] = 's';
			break;
		case 'g':
			PGBoard[row][col] = 'g';
			break;
		case 'L':
			PGBoard[row][col] = 'F';
			numSteps.set(numSteps.get() + 1);
			break;
		case '-':
			PGBoard[row][col] = '|';
			numSteps.set(numSteps.get() + 1);
			break;
		case '|':
			PGBoard[row][col] = '-';
			numSteps.set(numSteps.get() + 1);
			break;
		case 'F':
			PGBoard[row][col] = '7';
			numSteps.set(numSteps.get() + 1);
			break;
		case '7':
			PGBoard[row][col] = 'J';
			numSteps.set(numSteps.get() + 1);
			break;
		case 'J':
			PGBoard[row][col] = 'L';
			numSteps.set(numSteps.get() + 1);
			break;
		default:
			PGBoard[row][col] = ' ';
			break;
		}
		this.PGListBoard.set(row, this.PGListBoard.get(row));

		// check if player finish
		try {
			if(isPlayerFinish())
				this.isGoal.set(true);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public boolean isPlayerFinish() throws IOException, InterruptedException {
		if (serverSocket != null) {
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));
			PrintWriter outToServer = new PrintWriter(this.serverSocket.getOutputStream());

			for (char[] line : this.PGListBoard.get()) {
				outToServer.println(line);
				outToServer.flush();
			}

			outToServer.println("done");
			outToServer.flush();

			String line;
			
			if ((line = inFromServer.readLine()).equals("done")) 
				return true;
		}
		return false;

	}

	public void solve() throws IOException, InterruptedException {
		if (serverSocket != null) {
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
					Platform.runLater(() -> getNextClick(row, col));
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
			this.PGBoard=this.PGListBoard.toArray(new char [this.PGListBoard.size()][]);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void saveGame(File file) {
		try {
			PrintWriter outFile = new PrintWriter(file);
			for (int i = 0; i < this.PGListBoard.size(); i++)
				outFile.println(new String(this.PGListBoard.get(i)));

			outFile.println("time:" + time.get());
			outFile.println("step:" + numSteps.get());

			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

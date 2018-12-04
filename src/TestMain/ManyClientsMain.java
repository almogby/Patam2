package TestMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManyClientsMain {
	 public static void main(String[] args) throws Exception {
	        System.out.println("**** Multi Client Side ****");
	        String client1 = "sL\n-g\ndone";
	        String client2 = "s-L\n---\n--g\ndone";
	        String client3 = "s-L\n---\n---\n--g\ndone";
	        startClient(client2);
	        startClient(client1);
	     //   startClient(client3);
	    }

	    private static void startClient(String clientInput) throws IOException {
	        Socket theServer = new Socket("localhost", 6400);
	        System.out.println("Connected to server");
	        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
	        PrintWriter outToServer = new PrintWriter(theServer.getOutputStream());

	        outToServer.println(clientInput);//Don't forget to use line break.
	        outToServer.flush();
	        outToServer.println("done");
	        outToServer.flush();

	        //Close everything
	        inFromServer.close();
	        outToServer.close();
	    }

	}

package Server;
import Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer implements Server{

	private int port;
	private ServerSocket serverSocket;
	private volatile boolean stop;

	
	public MyServer(int port) {
		this.port=port;
		this.stop=false;
	}
	
	private void startServer(ClientHandler ch) throws IOException{
        this.serverSocket = new ServerSocket(this.port);
        this.serverSocket.setSoTimeout(5000);

        while (!this.stop) {
            try {
                Socket aClient = this.serverSocket.accept();
                
                ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());

                aClient.close();
            } catch (SocketTimeoutException e) {}
        }
        this.serverSocket.close();
		/*
		this.serverSocket = new ServerSocket(this.port);
		this.serverSocket.setSoTimeout(5000);
		while(!this.stop) {	
			try{
				Socket aClient=this.serverSocket.accept();
				
				new Thread(() -> {
					try{
						ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
						aClient.close();
					} catch (IOException e) {e.printStackTrace();}
				}).start();
			} catch (SocketTimeoutException e) {}
		}
		this.serverSocket.close();
		*/
	}
	
    public void stop()
    {
        this.stop=true;
    }

	@Override
	public void start(ClientHandler ch) {
		
        new Thread(() -> {
            try {
                startServer(ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
		/*
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.serverSocket.setSoTimeout(300);
        } catch (IOException e) {
            // e.printStackTrace();
        }
        
		new Thread(()->{
			try {
				startServer(ch);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		*/
	}
}
	



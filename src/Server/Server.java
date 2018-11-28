package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public interface Server {

    public void stop();
	public void start(ClientHandler ch);
	
}

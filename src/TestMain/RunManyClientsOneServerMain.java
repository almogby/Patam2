package TestMain;

import Server.MyClientHandler;
import Server.MyMultiServer;
import Server.Server;

public class RunManyClientsOneServerMain {
    public static void main(String[] args) throws Exception {
        System.out.println("**** Multi Server Side ****");
        Server s = new MyMultiServer(6400, 1);//Take the port from the args
        s.start(new MyClientHandler());
        System.in.read();
        s.stop();
        System.out.println("Closed server");
    }
}

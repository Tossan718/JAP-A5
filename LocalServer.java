package CST8221.week11;

import java.net.*;
import java.io.*;

/**
 * Class LocalServer
 * @author sousap
 *
 */
public class LocalServer {  
	
	/**
	 * Default constructor.
	 */
	public LocalServer() {
		; // No commands
	}
	
	/**
	 * Execute method.
	 */
  public void execute() {
    System.out.println("Starting lookup of servers...");
    ServerSocket server;
    for (int i=24; i<=65355; i++) {
      try {
        server = new ServerSocket(i);
        server.close();
       }  // end try
      catch (IOException e) {
        System.out.println("Server running on " + i + ".");
      }
    }  // for
    System.out.println("Search ended");
  }  // main
} // class

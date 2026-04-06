package CST8221.week11;

import java.net.*;
import java.io.*;

/**
 * Class LookForServices.
 * @author sousap
 *
 */
public class LookForServices {
	
	/**
	 * Default constructor.
	 */
	public LookForServices() {
		; // No commands
	}
	
	/**
	 * Execute method.
	 * @param args Param arguments.
	 */
	public void execute(String[] args) {
		Socket theSocket = null;
		String host = "localhost";
		if (args.length > 0) {
			host = args[0];
		}
		System.out.println("Scanning server: " + host + "...");
		for (int i = 0; i < 65355; i++) {
			try {
				theSocket = new Socket(host, i);
				System.out.println("Service available on port " + i);
				theSocket.close();
			} catch (UnknownHostException e) {
				System.err.println(e);
				break;
			} catch (IOException e) {
				/* No service found */ }
		} // end for
		System.out.println("Scanner ended!");
	} // end main
} // end class

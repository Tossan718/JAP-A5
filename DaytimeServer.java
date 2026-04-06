package CST8221.week11;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Class DaytimeServer
 * @author sousap
 *
 */
public class DaytimeServer {
	
	/**
	 * Default constructor
	 */
	public DaytimeServer() {
		; // No commands
	}
	
	/**
	 * Execute method
	 */
	public void execute() {
		int numrequest = 0;
		try (ServerSocket s = new ServerSocket(13)) {
			while (true) {
				System.out.println("Current request: " + numrequest++);
				Socket soc = s.accept();
				DataOutputStream out = new DataOutputStream(soc.getOutputStream());
				out.writeBytes("Server Date: " + (new Date()).toString() + "\n");
				out.close();
				soc.close();
			}
		} catch (Exception e) {
			System.out.println("Error on server!");
		}
	}
}

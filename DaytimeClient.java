package CST8221.week11;

import java.io.*;
import java.net.*;

/**
 * Class DaytimeClient
 * @author sousap
 *
 */
public class DaytimeClient {
	
	/**
	 * Default constructor
	 */
	public DaytimeClient() {
		; // No commands
	}
	
	/**
	 * Execute method
	 */
	public void execute()  {
		Socket soc;
		try {
			soc = new Socket(InetAddress.getLocalHost(), 13);
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println(in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

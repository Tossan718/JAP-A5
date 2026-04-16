import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Project: Quantum Millionaire
 * Purpose: Processes incoming messages using the # separator protocol.
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private MillionaireServer server;
    private PrintWriter out;
    private BufferedReader in;
    
    // Fixed: Declared missing variables to track player info [cite: 98]
    private String playerName;
    private int playerId;

    public ClientHandler(Socket socket, MillionaireServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String input;
            while ((input = in.readLine()) != null) {
                // Split the message using the designed # separator [cite: 18]
                String[] parts = input.split("#");
                if (parts.length < 1) continue;
                
                String command = parts[0];

                switch (command) {
                    case "CON": // Connection Request [cite: 21, 107]
                        this.playerName = parts[2];
                        this.playerId = new Random().nextInt(1000); 
                        // Send back ACK with assigned ID and game status [cite: 34, 108]
                        sendMessage("ACK#0#" + playerId + "#4#1");
                        break;

                    case "CHT": // Incoming Chat [cite: 25, 113]
                        String chatText = parts[2];
                        // Server redistributes as MSG broadcast [cite: 28]
                        server.broadcast("MSG#" + playerId + "#" + chatText);
                        break;

                    case "ANS": // Player Answer [cite: 23, 111]
                        // Logic to check answer and broadcast result (RES) [cite: 38, 112]
                        break;
                        
                    case "DSC": // Disconnect [cite: 29, 116]
                        server.broadcast("LEA#0#" + playerId); // Notify others [cite: 36]
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Client handler error: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException e) { /* Ignored */ }
        }
    }

    // Fixed: Added the missing sendMessage method
    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }
}

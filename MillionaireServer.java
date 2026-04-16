// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MillionaireServer {
   private List<ClientHandler> clients = new ArrayList();
   private int port;
   private boolean isRunning = false;

   public MillionaireServer(int var1) {
      this.port = var1;
   }

   public void start() {
      this.isRunning = true;
      (new Thread(() -> {
         try {
            ServerSocket var1 = new ServerSocket(this.port);

            try {
               System.out.println("Server started on port: " + this.port);

               while(this.isRunning) {
                  Socket var2 = var1.accept();
                  ClientHandler var3 = new ClientHandler(var2, this);
                  this.clients.add(var3);
                  (new Thread(var3)).start();
               }
            } catch (Throwable var5) {
               try {
                  var1.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }

               throw var5;
            }

            var1.close();
         } catch (IOException var6) {
            System.err.println("Server error: " + var6.getMessage());
         }

      })).start();
   }

   public synchronized void broadcast(String var1) {
      for(ClientHandler var3 : this.clients) {
         var3.sendMessage(var1);
      }

   }

   public void sendQuestion(String var1, String var2, String var3, String var4, String var5) {
      this.broadcast("QUE#0#" + var1 + "#" + var2 + "#" + var3 + "#" + var4 + "#" + var5 + "#1");
   }
}

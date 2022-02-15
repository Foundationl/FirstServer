import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException

public class myFirstUDPServer {

  private static final int ECHOMAX = 255;  // Maximum size of echo datagram

  public static void main(String[] args) throws IOException {
  
  byte[] cap = new byte[ECHOMAX];

    if (args.length != 1)  // Test for correct argument list
      throw new IllegalArgumentException("Parameter(s): <Port>");

    int servPort = Integer.parseInt(args[0]);

    DatagramSocket socket = new DatagramSocket(servPort);
    DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
    
    
    
    for (;;) {  // Run forever, receiving and echoing datagrams
      socket.receive(packet);     // Receive packet from client
      InetAddress address = packet.getAddress();
    int port = packet.getPort();
      System.out.println("Handling client at " +
        packet.getAddress().getHostAddress() + " on port " + packet.getPort() + " message " +new String(packet.getData()).toUpperCase());
       cap = new String(packet.getData()).toUpperCase().getBytes();
       packet = new DatagramPacket(cap, ECHOMAX,address,port);
      socket.send(packet);       // Send the same packet back to client
      packet.setLength(ECHOMAX); // Reset length to avoid shrinking buffer
    }
    /* NOT REACHED */
  }
}

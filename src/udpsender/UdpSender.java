/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpsender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author user
 */
public class UdpSender {

     public static void main(String[] args) throws SocketException {
    DatagramSocket socket = null;
   // socket.setBroadcast(true);
    DatagramPacket outPacket = null;
    byte[] outBuf;
    final int PORT = 28782;
 
    try {
      socket = new DatagramSocket();
      socket.setBroadcast(true);
      
      long counter = 0;
      String msg;
 
      while (true) {
        msg = "This is UDP Sender! " + counter;
        counter++;
        outBuf = msg.getBytes();
        
        //Send to multicast IP address and port
        InetAddress address = InetAddress.getLocalHost();
        
        
        String host =  address.getHostAddress();
      //  address.getAddress();
     //   outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
        
        outPacket = new DatagramPacket(outBuf, outBuf.length, InetAddress.getByName("192.168.102.255"), PORT);
        System.out.println("Address: " +address);
        System.out.println("Address .getAddress(): " +address.getAddress());
         
         socket.send(outPacket);
        
        outPacket = new DatagramPacket(outBuf, outBuf.length);
        System.out.println("Sender is finished sending" );


        socket.receive(outPacket);
            int port = outPacket.getPort();
           //  InetAddress   anAddress = 192.168.102.237;
                System.out.println("Port = "+port);

        String received = new String(outPacket.getData());

        System.out.println("Message received from clients: " + received);

 
        System.out.println("Server sends : " + msg);
        try {
          Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
      }
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
    
  }
}



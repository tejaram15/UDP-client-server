import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//package javaapplication2;

/**
 *
 * @author raghu
 */
public class Client {
    public static byte buf[] = new byte[1024];
    public static void main(String[] args) {
        DatagramSocket cls = null;
        try {
            // TODO code application logic here
             cls = new DatagramSocket(789);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatagramPacket dp = new DatagramPacket(buf,buf.length);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Client Started Boss... Type STOP to QUIT");
        while(true)
        {
            String s = null;
            try {
                s = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            buf = s.getBytes();
            if(s.equals("STOP"))
            {
                System.out.println("Process Ended");
                try {
                    cls.send(new DatagramPacket(buf,s.length(),ia,790));
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            try {
                cls.send(new DatagramPacket(buf,s.length(),ia,790));
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cls.receive(dp);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            String str2 = new String(dp.getData(),0,dp.getLength());
            System.out.println("Server : "+str2);
        }
    }
    
}

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author raghu
 */
public class JavaApplication3 {
    public static DatagramSocket serversocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static byte buf[] = new byte[1024];
    public static int cport = 789 , sport = 790;
    public static void main(String[] args) throws Exception {
        try {
            // TODO code application logic here
            serversocket = new DatagramSocket(sport);
        } catch (SocketException ex) {
            Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
        }
        dp = new DatagramPacket(buf,buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server Started....");
        while(true)
        {
            try {
                serversocket.receive(dp);
            } catch (IOException ex) {
                Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
            }
            String str = new String(dp.getData(),0,dp.getLength());
            if(str.equalsIgnoreCase("STOP"))
            {
                System.out.println("Server Ended");
                break;
            }
            System.out.println("Client : "+str);
            String str1 = dis.readLine();
            buf = str1.getBytes();
            serversocket.send(new DatagramPacket(buf,str1.length(),ia,cport));
        }
    }
    
}

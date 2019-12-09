/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
import java.io.*;
import java.net.Socket;
/**
 *
 * @author mostafa
 */
public class Fileserver_distributed  {
 public static Socket s;
    public static DataInputStream dis;
    public static DataOutputStream dos;
    public static String srvr_msg;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            s =  new Socket("127.0.0.1", 1234);
             dis  = new DataInputStream(s.getInputStream());
             dos  = new DataOutputStream(s.getOutputStream());
            new menu().setVisible(true);
        }
         catch(IOException ex){
            System.out.println("some thing happened during connection with server");

                }
    }
    
}

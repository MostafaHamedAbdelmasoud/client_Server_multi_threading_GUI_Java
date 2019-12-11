/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
//import static fileserver_distributed.Fileserver_distributed.dis;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mostafa
 */

class ClientHandler implements Runnable
{
            DataInputStream dis ;
            DataOutputStream dos;
            Socket s;

    public ClientHandler(Socket s )
    {
        this.s = s;
    }

    @Override
    public void run()
    {
        try
        {
            //3.create I/O streams
              dis  = new DataInputStream(s.getInputStream());
                dos  = new DataOutputStream(s.getOutputStream());
                System.out.println("new client");
             String g = dis.readUTF();
           
             if (g.equals("login")) {
                System.out.println("this is wirtten here "+g);
                    new login(s,dis,dos).setVisible(false);
            }
             else{
                    System.out.println("this is wirtten here "+g);
                    new Register(s,dis,dos).setVisible(false);
             }

            //5.close connection
            dis.close();
            dos.close();
            s.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}


public class Fileserver_distributed {
    public static ServerSocket sv;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try{
             sv = new ServerSocket(1234);
              System.out.println("Client Accepted...");
              while (true)
            {
                //2.accept connection
                Socket s = sv.accept();
                System.out.println("Client Accepted...");
                
                //3. open thread for this client (s)
                ClientHandler ch = new ClientHandler(s);
                Thread t = new Thread(ch);
                t.start();

            }

             
        }
        catch(IOException ex){
//            System.out.println("some thing happened during connection with server");
             System.out.println(ex.getMessage());
                }
        
//        new Register().setVisible(false);
            
    }

};

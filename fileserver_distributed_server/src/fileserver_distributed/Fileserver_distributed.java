/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mostafa
 */
public class Fileserver_distributed {
    public static ServerSocket sv;
    public static Socket s ;
    public static DataInputStream dis;
    public static DataOutputStream dos ;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try{
             
            sv = new ServerSocket(1234);
              s = sv.accept();
             dis  = new DataInputStream(s.getInputStream());
             dos  = new DataOutputStream(s.getOutputStream());
             System.out.println("yfy");
             String g = dis.readUTF();
             if (g.equals("login")) {
                System.out.println("this is wirtten here "+g);
                    new login().setVisible(false);
            }
             else{
                    System.out.println("this is wirtten here "+g);
                    new Register().setVisible(false);
             }
        }
        catch(IOException ex){
            System.out.println("some thing happened during connection with server");

                }
        
//        new Register().setVisible(false);
            
    }

};

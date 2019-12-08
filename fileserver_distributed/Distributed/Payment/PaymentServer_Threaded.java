package paymentserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class clientHandler implements Runnable
{

    Socket c;

    public clientHandler(Socket c)
    {
        this.c = c;
    }

    @Override
    public void run()
    {
        try
        {
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());

            //4.perform IO with client
            while (true)
            {
                dos.writeUTF("Hello enter account num:");
                String accnum = dis.readUTF();
                //check using accnum in DB
                dos.writeUTF("Account num :" + accnum + "is valid"
                        + "enter password ");
                String pass = dis.readUTF();
                //check using accnum and pass in DB
                dos.writeUTF("Account num :" + accnum + "and pass:"
                        + pass + "are valid enter payment ");
                String payment = dis.readUTF();
                //check if balance is enough
                dos.writeUTF("Payment = " + payment + "is successfull"
                        + "do you want to perform another y/n?");

                String userChoice = dis.readUTF();
                if (userChoice.equalsIgnoreCase("N"))
                {
                    break;
                }

            }

            //5. close comm with client
            dos.close();
            dis.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Something went wrong ");
        }

    }

}

public class PaymentServer
{

    public static void main(String[] args)
    {
        try
        {
            //1.Listen 
            //2.accept
            //3.create socket (I/O) with client
            ServerSocket s = new ServerSocket(1234);
            while (true)
            {
                
                Socket c = s.accept();
                
                System.out.println("Client Arrived");
                clientHandler ch = new clientHandler(c);
                //ch.run();//wait till run finish its code

                //handle client in parrallel
                Thread t = new Thread(ch);
                t.start();
                //create new light weight process 
                //and run in parallel and the main thread
                //continues

            }

            //s.close();
        } catch (IOException ex)
        {
            System.out.println("Something went wrong");
        }

    }

}

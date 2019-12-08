package paymentclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PaymentClient
{

    public static void main(String[] args)
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            //1.connect
            //2. create socket
            Socket c = new Socket("127.0.0.1", 1234);
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());

            //3. perform IO
            while (true)
            {
                String servermsg = dis.readUTF();
                System.out.println(servermsg);
                String userresp = sc.nextLine();
                dos.writeUTF(userresp);
                if(userresp.equalsIgnoreCase("N"))
                    break;
            }

            //4.close comm
            dos.close();
            dis.close();
            c.close();
        } catch (IOException ex)
        {

        }
    }

}

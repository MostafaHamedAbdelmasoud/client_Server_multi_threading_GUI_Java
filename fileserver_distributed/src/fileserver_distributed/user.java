/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.*;
import javax.swing.JOptionPane;
/**
 *
 * @author mostafa
 */
public class user implements Serializable {
    private int Id;
    private static final  AtomicInteger count = new AtomicInteger(0);
    private static final long serialVersionUID = 6529685098267757690L;
    private String email;
    private String Password;
//    private static final Set<String> mails = new HashSet<String>();
    private ArrayList<user> users;
        

    
       public void populateArrayList(){
        try {
            FileInputStream file = new FileInputStream("users.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            boolean endOfFile = false;
            while (!endOfFile) {                
                try {
                    users.add((user)inputFile.readObject());
//                    mails.add(users)
                } catch (EOFException e) {
                    endOfFile = true;
                }
                catch (Exception f) {
//                    JOptionPane.showMessageDialog(null, f.getMessage());
                    JOptionPane.showMessageDialog(null, f.getMessage());
                }
            }
            inputFile.close();
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    public user(String email, String Password) {
        users = new ArrayList<user>();
        populateArrayList();
            this.Id = users.size();
//         int []indx = new int [200];
//        Arrays.fill(indx, 0);
         
         try{
             this.email = email;
             this.Password = Password;

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
//            new register().setVisible(true);
        }
         
         
//        this.email = email;
       
    }

    public int getId() {
        return this.Id;
    }

//    public void setId(int Id) {
//        this.Id = Id;
//    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
      try{
//            if (mails.contains(email)){
//             JOptionPane.showMessageDialog(null,"this email is used before");
//            }
//            else{
                this.email = email;
//            }
      }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    
}

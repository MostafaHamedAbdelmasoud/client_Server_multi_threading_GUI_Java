/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.text.*;
import java.util.Arrays;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author mostafa
 */
public class Register extends javax.swing.JFrame {

    ArrayList<user> users;
   private ServerSocket sv = Fileserver_distributed.sv;
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos ;

    /**
     * Creates new form Register
     */
    public Register(Socket s,DataInputStream dis,DataOutputStream dos) {
        System.out.println("server Side");

        try {
            this.s=s;
            this.dis=dis;
            this.dos=dos;
            initComponents();
            users = new ArrayList<user>();
            populateArrayList();

            checkForRegister();
            System.out.println(users.size());
        } catch (Exception ex) {
            System.out.println("some thing happened during connection with server");

        }

    }

    public void populateArrayList() {
        try {
            FileInputStream file = new FileInputStream("users.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    users.add((user) inputFile.readObject());
                } catch (EOFException e) {
                    endOfFile = true;
                } catch (Exception f) {
//                    JOptionPane.showMessageDialog(null, f.getMessage());
//                    JOptionPane.showMessageDialog(null, f.getMessage());
//                    dos.writeUTF("error during fetching users!");
//                    dos.flush();
                }
            }
            inputFile.close();

        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            JOptionPane.showMessageDialog(null, "oops3!");
        }
    }

    public void saveUsersToFile() {
        try {
            FileOutputStream file = new FileOutputStream("users.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for (int i = 0; i < users.size(); i++) {
                outputFile.writeObject(users.get(i));
            }
            outputFile.close();
//            JOptionPane.showMessageDialog(null, "succefuly Registered!");
            dos.writeUTF("succefuly Registered!");
            dos.flush();

            dos.writeUTF(users.size() + "");
            dos.flush();

//            s.close();
//            sv.close();
            System.out.println("ready to close");
//            System.out.println(dis.read());
            
            new Home(Integer.toString(users.size()) , s,dis,dos);
//            new Home(Integer.toString(users.size())).setVisible(true);

            this.dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "hello t");
//            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "hellos t");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void createDirectory(String pathName) {
        File files = new File(pathName);
        if (!files.exists()) { //if the file folder does not exist
            if (files.mkdirs()) { //if you want to create sub folder. you can change mkdir to mkdirs
                System.out.println("Directory Created in" + files.getAbsolutePath());
            } else {
                System.out.println("Problem Occured creating directory");
            }
        } else {
            System.out.println("Directory " + files.getAbsolutePath() + " already exist !");
        }
    }

    public void checkForRegister() {
        try {
               
            dos.writeUTF("please enter your email!");
            dos.flush();
            String email = dis.readUTF();
            System.out.println(email);
            dos.writeUTF("please enter your password!");
            dos.flush();
            
            String password = dis.readUTF();
            System.out.println(password);
            System.out.println(email + " this is password also: " + password);
            boolean AlreadyReg = false;
            for (int i = 0; i < users.size(); i++) {
                if (email.equals(users.get(i).getEmail())) {
//                            JOptionPane.showMessageDialog(null, "you are already registered!");
                    dos.writeUTF("true");
                    dos.flush();
                    AlreadyReg = true;
                    break;
                }
            }
            if (AlreadyReg) {
//                this.dispose();
//                dis.close();
//                dos.close();
//                s.close();
                new login(this.s,this.dis,this.dos).setVisible(true);
                this.dispose();
            } else {

                try {
                    user newUser = new user((String) email, (String) password);
                    users.add(newUser);
//                                       new folder(newUser.getId())
//                                       createDirectory("home/mostafa/NetBeansProjects/fileserver_distributed_server/"+users.size()+"/home");
                    createDirectory("/home/mostafa/NetBeansProjects/fileserver_distributed_server/" + users.size() + "/home");
//                                         dis.close();
//                                         dos.close();
                    saveUsersToFile();
//                                          dis.close();
//                                        dos.close();
                    // s.close();
                } catch (Exception e) {
//                            JOptionPane.showMessageDialog(null, e.getMessage());
                    JOptionPane.showMessageDialog(null, "oops!");

                }
            }

//                   dis.close();
//                                    dos.close();
//                    s.close();
        } //                    new Home().setVisible(true);
        //                    this.dispose();
        catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
            JOptionPane.showMessageDialog(null, "oops2!");

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.pink);

        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText("Register");

        jLabel1.setText("Email:");

        jLabel2.setText("Password:");

        jLabel3.setText("Rewrite Password:");

        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Register(s,dis,dos).setVisible(false);

//               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
import fileserver_distributed.Fileserver_distributed;
import fileserver_distributed.Data;
import fileserver_distributed.filterImages;
import java.awt.image.ImageFilter;

//package CustomFileVisitor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.text.*;
import java.util.Arrays;

import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;
import javax.swing.JFileChooser;


import javax.swing.filechooser.*;
   





/**
 *
 * @author mostafa
 */
public class Home extends javax.swing.JFrame {
    private static String PathNow ;
    private ArrayList<String> cdLists;
    private String pathForOperation , ContinuedPathForOperation;
    private Socket s = Fileserver_distributed.s;
    private DataInputStream dis= Fileserver_distributed.dis;
    private DataOutputStream dos= Fileserver_distributed.dos ;
    private String srvr_msg ;  
    /**
     * Creates new form Home
     */
    private String [] Stats = {"Image","File"};
    
   
  
    
    public Home(){
    
}
    
    public Home(String path) {
//        initComponents();
//      initComponents();
        try{
            this.PathNow = path;
           initComponents();
           setSize(752,505);
           setLocation(100,100);
           setResizable(false);
           
//            srvr_msg = dis.readUTF();  
            System.out.println("this is from home!");
              String [] collapeCommands = {"cd","mkdir","ls","rmdir","pwd","cp","rnm","rm","mv","download"};
//              String [] Stats = {"Image","File"};
              
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(collapeCommands));
            jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(Stats));
        }
        catch(Exception ex){
             JOptionPane.showMessageDialog(null,"problem occured during connecting to server: "+ex.getMessage());
        }
    }
    
    
//    public void acceptDownload(){
//        try{
//            int filesize=1022386; 
//            int bytesRead; 
//            int currentTot = 0; 
//    //        Socket socket = new Socket("127.0.0.1",15123); 
//            byte [] bytearray = new byte [filesize]; 
//    //        InputStream is = socket.getInputStream(); 
//            FileOutputStream fos = new FileOutputStream("copy.doc"); 
//            BufferedOutputStream bos = new BufferedOutputStream(fos); 
//            bytesRead = dis.read(bytearray,0,bytearray.length); 
//            currentTot = bytesRead; 
//            do { 
//                bytesRead = dis.read(bytearray, currentTot, (bytearray.length-currentTot)); 
//                if(bytesRead >= 0) currentTot += bytesRead; 
//            } while(bytesRead > -1); 
//            bos.write(bytearray, 0 , currentTot);
//            bos.flush(); 
//
//            bos.close(); 
//    //        socket.close();
//        }
//        catch(Exception ex){
//           JOptionPane.showMessageDialog(null, ex.getMessage());
//
//        }
//    //Read more: http://mrbool.com/file-transfer-between-2-computers-with-java/24516#ixzz67cpqJzdX
//    }
//    
    private ObjectOutputStream out;
    public void acceptDownload(String FileUploaded){
        try {
            
            out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject("mostafa");
            out.flush();
            JFileChooser ch = new JFileChooser();
            int c = ch.showOpenDialog(this);
            if (c == JFileChooser.APPROVE_OPTION) {
                File f = ch.getSelectedFile();
                FileInputStream in = new FileInputStream(f);
                byte b[] = new byte[in.available()];
                in.read(b);
                Data data = new Data();
                data.setStatus(jComboBox2.getSelectedItem() + "");
                data.setName(FileUploaded);
                data.setFile(b);
                out.writeObject(data);
                out.flush();
//                txt.append("send 1 file ../n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(231, 231, 232));
        getContentPane().setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(86, 54, 148, 36);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(628, 53, 90, 36);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(260, 54, 295, 36);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(86, 108, 469, 251);

        jPanel1.setBackground(new java.awt.Color(158, 178, 240));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Your Panel");

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane1.setViewportView(jTextArea3);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Image" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton3.setText("Upload");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 750, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
      
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        String TypedName2 = jTextField1.getText().trim();
        try{
             String commandName = jComboBox1.getSelectedItem().toString();
            if(commandName.equals("mkdir")){
                String TypedName = jTextField2.getText();
                
                    if(TypedName.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    else{

//                         createDirectory(addPAth(TypedName).concat(""));
                           dos.writeUTF("mkdir");
                            dos.flush();
                            dos.writeUTF(TypedName);
                            dos.flush();
                            String pathReturned = dis.readUTF();
                            JOptionPane.showMessageDialog(null,pathReturned );
                            String common = dis.readUTF();
                            System.out.println(common);
                            jTextArea1.setText(common);
         //                  listDirectories(false);
                    }
                   
            }
            else if(commandName.equals("ls")){
                    jTextField2.setText("");
                    dos.writeUTF("ls");
                    dos.flush();
                    String common = dis.readUTF();
//                    System.out.println(getPathNow());
                     jTextArea1.setText(common);
//                   listDirectories(false);
            }
            else if(commandName.equals("rmdir")){
                String removedDir = jTextField2.getText();
//                listDirectories(true);
                 if(removedDir.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    else{
                        dos.writeUTF("rmdir");
                        dos.flush();
                        dos.writeUTF(removedDir);
                        dos.flush();
                        String common = dis.readUTF();
//                    System.out.println(getPathNow());
                     jTextArea1.setText(common);
//                         OkToRemove(removedDir,true);

                    }
//                listDirectories(false);
            }
            else if(commandName.equals("cd")){
                String PathTyped = jTextField2.getText();
//                listDirectories(true);
//            JOptionPane.showMessageDialog(null, "note: make sure to put '/' with "+commandName +" in the end of your input!");
                 if(PathTyped.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    else{
//                     separateInputsFromUser(PathTyped,true,"none");
//                         OkToRemove(removedDir);
                        dos.writeUTF("cd");
                        dos.flush();
                        dos.writeUTF(PathTyped);
                        dos.flush();
                        String common = dis.readUTF();
                        jTextArea1.setText(common);

                    }
                
            }
            else if(commandName.equals("pwd")){
//                String PathTyped = jTextField2.getText();
//                listDirectories(true);
//                 if(! PathTyped.trim().isEmpty()){
//                       JOptionPane.showMessageDialog(null, "please enter all fields");
//                   }
                    try{
                        dos.writeUTF("pwd");
                        dos.flush();
//                                jTextArea1.setText(cdAhlam);
                                    String common = dis.readUTF();
                                jTextArea1.setText(common);
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                
            }
            else if(commandName.equals("cp")){
                
                String folderTyped = jTextField2.getText();
//                listDirectories(true);
                 if(folderTyped.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    try{
                        dos.writeUTF("cp");
                        dos.flush();
                        dos.writeUTF(folderTyped);
                        dos.flush();
//                       String ho =  separateInputsFromUser(folderTyped,false,"cp");
                        String common = dis.readUTF();
                       if(common.equals("failed")){
                           JOptionPane.showMessageDialog(null, "check path or name ,try again");
                       }
                       else{
                            
                           JOptionPane.showMessageDialog(null, "successful operation.");
//                                jTextArea1.setText(cdAhlam);
                       }
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                
            }
            
            
            else if(commandName.equals("rnm")){
                dos.writeUTF("rnm");
                dos.flush();
                String folderTyped = jTextField2.getText();
//                listDirectories(true);
                 if(folderTyped.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    try{
//                       String ho =  separateInputsFromUser(folderTyped,false,"rnm");
                           dos.writeUTF(folderTyped);
                            dos.flush();
                            String common = dis.readUTF();
                       if(common.equals("failed")){
                           JOptionPane.showMessageDialog(null, "check path/name ,try again");
                       }
                       else{
                            
                           JOptionPane.showMessageDialog(null, "successful operation.");
//                                jTextArea1.setText(cdAhlam);
                       }
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                
            }
            
            else if(commandName.equals("rm")){
                String folderTyped = jTextField2.getText();
//                listDirectories(true);
                 if(folderTyped.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    try{
                        dos.writeUTF("rm");
                        dos.flush();
                        dos.writeUTF(folderTyped);
                        dos.flush();
                         String common = dis.readUTF();
                       if(common.equals("failed")){
                           JOptionPane.showMessageDialog(null, "file name is not exist!");
                       }
                       else{
                           JOptionPane.showMessageDialog(null, "successful operation.");
                       }
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                
            }
            
             else if(commandName.equals("mv")){
                String folderTyped = jTextField2.getText();
//                listDirectories(true);
                 if(folderTyped.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                    try{
                         dos.writeUTF("mv");
                        dos.flush();
                        dos.writeUTF(folderTyped);
                        dos.flush();
                        String common = dis.readUTF();
                       if(common.equals("failed")){
                           JOptionPane.showMessageDialog(null, "file name is not exist!");
                       }
                       else{
                           JOptionPane.showMessageDialog(null, "successful operation.");
                       }
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                
            }
             
             else if(commandName.equals("download")){
                String folderTyped = jTextField2.getText();
//                listDirectories(true);
                 if(folderTyped.trim().isEmpty()){
                       JOptionPane.showMessageDialog(null, "please enter all fields");
                   }
                 String sp = "",prefix="";
                 int indx = 0;
                    for (int i = 0; i < folderTyped.length(); i++) {
                            if(folderTyped.charAt(i) == '.' && (indx <1) ){
                                prefix = sp;
                                indx=1;
                                sp="";
                            }
                            sp += folderTyped.charAt(i);
                        }
                    String postfix = sp;
                    try{
                         dos.writeUTF("download");
                        dos.flush();
//                        DateFormat df = new SimpleDateFormat("HH:mm:ss");
                        long df = System.currentTimeMillis();
                        dos.writeUTF(folderTyped);
                        dos.flush();
                        String ho = dis.readUTF();
                        File file = new File("");
                        System.out.println(file.getAbsoluteFile());
                         int max = 10; 
                    int min = 1; 
                    int range = max - min + 1; 
                        if(ho.equals("Found"))
                        {
                            byte [] b=new byte[5000];
                            InputStream is = s.getInputStream();
//                            dis.read(b,0,b.length);
//                            File file = new File("");
                          System.out.println(df);
                            FileOutputStream fr = new FileOutputStream(file.getAbsolutePath()+"/"+prefix+(int)(Math.random() * range) + min+"_"+df+postfix);
                            is.read(b,0,b.length);
                            fr.write(b,0,b.length);
//                            fr.flush();
                            JOptionPane.showMessageDialog(null, "successfuly downloaded!");
//                            is.close();
//                            fr.close();
//                            this.dispose();
//                            new Home(this.PathNow).setVisible(true);
//                        new Home(this.PathNow).setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "file name not found!");

                        }
                        
                        
                    }
                    catch(Exception e){
//                        JOptionPane.showMessageDialog(null, e.getMessage());
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                
            }
             
                     
            
          
         jTextField2.setText("");
//            trimPaths(PathNow);
            String pathTrimed = dis.readUTF();
            
         jTextArea3.setText(pathTrimed);
        }
       catch(Exception e){
//           System.out.println(getPathNow());
//           JOptionPane.showMessageDialog(null, e.getMessage());
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        while(true){
        String folderTyped = jTextField2.getText();
//                listDirectories(true);
     
           if(folderTyped.trim().isEmpty()){
               JOptionPane.showMessageDialog(null, "please enter a name for youe uploades first");
          }
           else{
        
                try {
                    dos.writeUTF("upload");
                    dos.flush();
                    out = new ObjectOutputStream(s.getOutputStream());
                    FileInputStream in;
        //            out.writeObject("mostafa");
        //            out.flush();
                    JFileChooser ch = new JFileChooser();
                    String stst = jComboBox2.getSelectedItem().toString();
                    
                    if(stst.equals("Image")){
                            ch.addChoosableFileFilter(new filterImages());
                             ch.setAcceptAllFileFilterUsed(false);
                    }
                   
                    int c = ch.showOpenDialog(this);
                    if (c == JFileChooser.APPROVE_OPTION) {
                        File f = ch.getSelectedFile();
                         in = new FileInputStream(f);
                        byte b[] = new byte[in.available()];
                        in.read(b);
                        Data data = new Data();
                        data.setStatus(jComboBox2.getSelectedItem().toString());
                        
                        
                        data.setName(folderTyped);
                        data.setFile(b);
                        System.out.println(data.getFile());
//                        this.dispose();
                    ch.setVisible(false);
//                 new Home(this.PathNow).setVisible(true);
                        out.writeObject(data);
                        out.flush();
        //                txt.append("send 1 file ../n");
                          
                    }
//                    out.close();
//                    in.close(); 
//out.close();
//                this.dispose();
//                ch.setVisible(false);
//                 new Home(this.PathNow).setVisible(true);
                    jTextField2.setText("");
        //            trimPaths(PathNow);
        ch.setVisible(false);
                    String pathTrimed = dis.readUTF();
                       
                 jTextArea3.setText(pathTrimed);
                 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
                 }
//           }
    }//GEN-LAST:event_jButton3ActionPerformed
   
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(PathNow).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}

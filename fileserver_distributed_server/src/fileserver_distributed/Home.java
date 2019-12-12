/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
import fileserver_distributed.Fileserver_distributed;
import java.awt.HeadlessException;
//package CustomFileVisitor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.ServerSocket;
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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;


    
   


/**
 *
 * @author mostafa
 */
public class Home extends javax.swing.JFrame {
    private static  String PathNow;
    private String allPaths;
    private String PublicPathNow;
    public String cdAhlam;
    private ArrayList<String> cdLists;
    private String pathForOperation , ContinuedPathForOperation;
    private ServerSocket sv = Fileserver_distributed.sv;
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private static String srvr_msg;

    
    
    /**
     * Creates new form Home
     */
    
    
    
    public static void delete(File file, boolean isDir)
    	throws IOException{
 
    	if(file.isDirectory() && isDir){
 
    		//directory is empty, then delete it
    		if(file.list().length==0 ){
    			
    		   file.delete();
    		   System.out.println("Directory is deleted : " 
                                                 + file.getAbsolutePath());
    			
    		}else{
    			
    		   //list all the directory contents
        	   String files[] = file.list();
     
        	   for (String temp : files) {
        	      //construct the file structure
        	      File fileDelete = new File(file, temp);
        		 
        	      //recursive delete
        	     delete(fileDelete,true);
        	   }
        		
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==0){
           	     file.delete();
        	     System.out.println("Directory is deleted : " 
                                                  + file.getAbsolutePath());
        	   }
    		}
    		
    	}
        else{
    		//if file, then delete it
                if(file.isFile() && !isDir){
                    file.delete();
                    System.out.println("File is deleted : " + file.getAbsolutePath());
                }
                else{
                    System.out.println("it is a directory, not a file!");
                }
    	}
    }
    
    public void trimPaths(String path){
//        ArrayList<String> slashes = new ArrayList<>();
        String s = "";
        this.pathForOperation = "";
        this.ContinuedPathForOperation = "";
        int h = 0;
        for (int i = 0; i < this.cdAhlam.length(); i++) {
            if(this.cdAhlam.charAt(i) == '/'){
//                slashes.add(s);
                if(s.equals(path)){
                    h = i;
                    break;
                }
                s="";
            }
            else
                s += this.cdAhlam.charAt(i);
        }
        for (int i = 0; i < h; i++) 
            this.pathForOperation += this.cdAhlam.charAt(i);
        for (int i = h; i < this.cdAhlam.length(); i++) 
            this.ContinuedPathForOperation += this.cdAhlam.charAt(i);
          // there is / in begining of continued path
          System.out.println(this.pathForOperation);
          System.out.println(this.ContinuedPathForOperation);
         
    }
    
    
    public Home(){
    
}
    
    public Home(String path,Socket s ,DataInputStream dis , DataOutputStream dos) {
        try
        {
            
            this.dis = dis;
            this.dos=dos;
            this.s=s;
            initComponents();
                setSize(750,505);
               setLocation(100,100);
               setResizable(false);
              this.PathNow = path;
              this.allPaths = "";
                System.out.println("this is  home!");
       //          JOptionPane.showMessageDialog(null,getCwd()+"/"+path+"/home");
//                 JOptionPane.showMessageDialog(null,getCwd());
                 this.cdAhlam= getCwd();
       //          JOptionPane.showMessageDialog(null,getPathNow());
                 String [] collapeCommands = {"cd","mkdir","ls","rmdir","pwd","cp","rnm","rm","mv"};
//               jList1.sett(new javax.swing.DefaultComboBoxModel<>(collapeCommands));
               trimPaths(path);
               while(true){
               String assal = dis.readUTF();
               System.out.println("succesfuuly received "+assal);
               if(assal.equals("mkdir"))
               {
                   assal = dis.readUTF();
                   createDirectory(addPAth(assal).concat(""));
                   try{
                    dos.writeUTF(returnlistdirectoriesasstring());
                    dos.flush();
                        System.out.println("data sent");
                    }
            catch(Exception ex)
                    {
                        System.out.println("error in making directory");
                    }  
                   
               }
               else if(assal.equals("ls"))
               {
                   
                    try{
                    dos.writeUTF(returnlistdirectoriesasstring());
                    dos.flush();
                        System.out.println("data sent");
                    }
            catch(Exception ex)
                    {
                        System.out.println("error in listing directory");
                    }      
                   
               }
               else if(assal.equals("cd")){
                  assal =  dis.readUTF();
                   separateInputsFromUser(assal, true, "none");
                    try{
                    dos.writeUTF(returnlistdirectoriesasstring());
                    dos.flush();
                        System.out.println("data sent");
                    }
            catch(Exception ex)
                    {
                        System.out.println("error in listing directory");
                    } 
               }
               
               else if(assal.equals("rmdir")){
                  assal =  dis.readUTF();
                   OkToRemove(assal,true);
                    try{
                    dos.writeUTF(returnlistdirectoriesasstring());
                    dos.flush();
                        System.out.println("data sent");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("error in removing directory");
                    } 
               }
                   else if(assal.equals("pwd")){
                        trimPaths(PathNow);
                       try{
                            dos.writeUTF(ContinuedPathForOperation);
                            dos.flush();
                            System.out.println("data sent");
                       }
                        catch(Exception ex){
                           System.out.println("error in removing directory");
                       } 
                    }
                   
                    else if(assal.equals("cp")){
                       listDirectories(true);
                           try{
                               assal = dis.readUTF();
                              String ho =  separateInputsFromUser(assal,false,"cp");
//                               String common = dis.readUTF();
                              if(ho.equals("failed")){
//                                  JOptionPane.showMessageDialog(null, "try again");
                                  dos.writeUTF("failed");
                                  dos.flush();
                              }
                              else{
                                   dos.writeUTF("successed");
                                  dos.flush();
//                                  JOptionPane.showMessageDialog(null, "successful operation.");
       //                                jTextArea1.setText(cdAhlam);
                              }
                           }
                           catch(Exception e){
                               JOptionPane.showMessageDialog(null, e.getMessage());
                           }
                    }
                else if(assal.equals("rnm")){
                       listDirectories(true);
                           try{
                               assal = dis.readUTF();
                              String ho =  separateInputsFromUser(assal,false,"rnm");
//                               String common = dis.readUTF();
                              if(ho.equals("failed")){
//                                  JOptionPane.showMessageDialog(null, "try again");
                                  dos.writeUTF("failed");
                                  dos.flush();
                              }
                              else{
                                   dos.writeUTF("successed");
                                  dos.flush();
//                                  JOptionPane.showMessageDialog(null, "successful operation.");
       //                                jTextArea1.setText(cdAhlam);
                              }
                           }
                           catch(Exception e){
                               JOptionPane.showMessageDialog(null, e.getMessage());
                           }
                    }
                
                        else if(assal.equals("rm")){
                            listDirectories(true);
                                try{
                                    assal = dis.readUTF();
                                   boolean ho =  OkToRemove(assal,false);
                                    if(!ho){
//                                        JOptionPane.showMessageDialog(null, "file name is not exist!");
                                         dos.writeUTF("failed");
                                        dos.flush();
                                    }
                                    else{
//                                        JOptionPane.showMessageDialog(null, "successful operation.");
                                            dos.writeUTF("successed");
                                            dos.flush();
             //                                jTextArea1.setText(cdAhlam);
                                    }
                                }
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                        }
                        
                         else if(assal.equals("mv")){
                            listDirectories(true);
                                try{
                                    assal = dis.readUTF();
                                   String ho =  separateInputsFromUser(assal,false,"mv");
                                    if(ho.equals("failed")){
                                        dos.writeUTF("failed");
                                        dos.flush();
//                                        JOptionPane.showMessageDialog(null, "try again");
                                    }
                                    else{
                                            dos.writeUTF("successed");
                                            dos.flush();
//                                        JOptionPane.showMessageDialog(null, "successful operation.");
             //                                jTextArea1.setText(cdAhlam);
                                    }
                                }
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                        }
                         else if(assal.equals("download")){
//                            listDirectories(true);
                           System.out.println("hello from download!");
                            boolean found = false;
                            String [] filess= listDirectoriesOnFilesOnly();
                           FileInputStream fis  = null;
                                try{
                                      assal = dis.readUTF();
                                     for (int i = 0; i < filess.length; i++) {
                                        String files = filess[i];
                                        if(files.equals(assal)){
                                            found = true;
                                            break;
                                        }
                                    }
                                    
                                    if(!found){
                                        dos.writeUTF("notFound");
                                        dos.flush();
                                    }
                                    try{
                                        if(found){
                                             dos.writeUTF("Found");
                                            dos.flush();
                                              File file = new File(cdAhlam+"/"+assal);
                                         fis = new FileInputStream(file);
                                         byte b []=new byte [5000];
                                         fis.read(b,0,b.length);
                                         OutputStream os = s.getOutputStream();
                                         os.write(b, 0, b.length);
                                         os.flush();
//                                         if(fis!=null)
//                                            fis.close();
                                        }
                                    }
                                    catch(IOException ex){
//                                      JOptionPane.showMessageDialog(null, ex.getMessage());
                                        JOptionPane.showMessageDialog(null, "kdkd");

                                      }
                                    finally {
                                        try { if (fis == null) fis.close(); } catch(Exception e){

                                        }
                                        

                                    }
                                }
                                catch(Exception e){
                                    JOptionPane.showMessageDialog(null, "kdk");
//                                    JOptionPane.showMessageDialog(null, e.getMessage());
                                }
                        }
                         else if(assal.equals("upload")){
                             System.out.println("hrl");
                            String  jfram = dis.readUTF();
//                            System.out.println(jfram);
                            if(jfram.equals("choose")){
                                    new saveForm(cdAhlam,s,dis,dos).setVisible(false);
                                   System.out.println("hello from upload!");
        //                            boolean found = false;
                                     try{
//                                         new Home().setVisible(false);
                                            System.out.println("save form");

        //                                    this.dispose();
                                      }
                                     catch(Exception e){
//                                         new Home().setVisible(false);
                                            JOptionPane.showMessageDialog(null, e.getMessage());
                                      }
                            }
//                             new Home().setVisible(false);
                                
                        }
               
               
               else{
                   System.out.println("not defined command!");
               }
                trimPaths(this.PathNow);
                   System.out.println(ContinuedPathForOperation);
                dos.writeUTF(ContinuedPathForOperation);
                dos.flush();
               }
            

        }
        catch(IOException ex)
        {
            
           JOptionPane.showMessageDialog(null,"confirmed: successfuly closing your session socket");

        }
        
    }
//     Home H = new Home();
    private  void createDirectory(String pathName){
        File files = new File(pathName);
        if(!files.exists()){ //if the file folder does not exist
            if(files.mkdirs()){ //if you want to create sub folder. you can change mkdir to mkdirs
               try{
                dos.writeUTF("Directory Created in"+ContinuedPathForOperation+"");
                dos.flush();
               }
               catch (IOException ex){
                  JOptionPane.showMessageDialog(null,ex.getMessage());

               }
//                JOptionPane.showMessageDialog(null,"Directory Created in"+ContinuedPathForOperation);

                
            }
            else{
                 JOptionPane.showMessageDialog(null,"Problem Occured During Creating Directory, check space or if it exists already!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Directory "+files.getAbsolutePath()+" already exist !");

        }
    }
    
     public String getPathNowInsideList(String PathNowUpdated) {
        String path= getCwdUpdated(PathNowUpdated);
        try{
             System.out.println(path);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return path;
    }
    
   public String getPathNow(){
       return this.PublicPathNow;
   }
   
    public String addPAth(String pathTyped) {
        this.allPaths += pathTyped;
        return getPathNow()+"/"+pathTyped;
        
    }
    public String deletePathNow(String name) {
        
        return getPathNow()+"/"+name;
    }
    public String getCwd() {
        File file = new File("/home/mostafa/NetBeansProjects/fileserver_distributed_server/"+this.PathNow+"/home/");
        String path = file.getAbsolutePath();
           this.PublicPathNow= path;
        return path;
    }
    public String setCwdUpdated(String inputFromUser) {
        
        File file = new File(inputFromUser);
        String path = file.getAbsolutePath();
        
        this.PublicPathNow = path;
        return path;
    }
    public String getCwdUpdated(String path) {
        
        return path;
    }
    
    
    
    
   private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}
    public String returnlistdirectoriesasstring(){
        String dirs [] = listDirectories(false);
        String items = "";
        for(int i=0;i< dirs.length;i++)
        {
            items += dirs[i];
            items += '\n';
        }
        
        return items;
         
        
        
    }
    
    public String downLoadFiles(String fileDownloaded){
//        ServerSocket serverSocket = new ServerSocket(15123); 
//        Socket socket = serverSocket.accept(); 
        try{
            System.out.println("Accepted connection : " + s); 
            File transferFile = new File (fileDownloaded+""); 
            byte [] bytearray = new byte [(int)transferFile.length()]; 
    //        FileInputStream fin = new FileInputStream(transferFile); 
            BufferedInputStream bin = new BufferedInputStream(dis); 
            bin.read(bytearray,0,bytearray.length); 
    //        OutputStream os = socket.getOutputStream(); 
            System.out.println("Sending Files..."); 
            dos.write(bytearray,0,bytearray.length); 
            dos.flush(); 
    //        socket.close(); 
            System.out.println("File transfer complete");
            return "success";
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return "failed";
        }
        //Read more: http://mrbool.com/file-transfer-between-2-computers-with-java/24516#ixzz67coV1DAn
    }
    
    
    public boolean checkONNameAndMakeOperation(ArrayList<String> ArrayLst,String operationType){
        try{
         boolean found =false,firstFolderFounded = false, isPath =false,isDirect = false;
            String [] Filess = listDirectoriesOnFilesOnly();
            String [] Dirs = listDirectories(true);
            
                for (int j = 0; j < Filess.length; j++) {
                    if(ArrayLst.get(0).equals(Filess[j])){
                        
                        firstFolderFounded = true;
                    }
                    
                 }
                if(firstFolderFounded){
                    boolean secondFolderFounded = false;
                        for (int j = 0; j < Filess.length; j++) {
                            if(ArrayLst.get(1).equals(Filess[j])){
                                secondFolderFounded = true;

                            }
                        }
                    
                    
                    if (secondFolderFounded == true) {
                        JOptionPane.showMessageDialog(null,"this folder/file is already exist!");
                        return false;
                    }
                    
                    
                    if(operationType.equals("cp")){
                          String kkf = ArrayLst.get(1);
                          String lol=kkf;
                          String finalString = ArrayLst.get(1);
                          char hs = kkf.charAt(0);
                            if(hs=='/'){
                                isDirect = true;
                                
                            }
                            if(isDirect){
                                String finalString1 = lol.substring(1,lol.length());
                                 char fs = finalString1.charAt(finalString1.length() - 1);
                                   if(fs!='/')
                                       finalString1 += "/";
                                finalString=finalString1;
                            }
                            
                       System.out.println(cdAhlam+"/"+finalString);

                        try{
                            File sourse = new File(cdAhlam+"/"+ArrayLst.get(0).concat(""));
                            File target;
                            
                            if(isDirect){
                                 target = new File(this.pathForOperation+"/"+finalString+ArrayLst.get(0).concat(""));
                        }
                            else
                                 target = new File(cdAhlam+"/"+ArrayLst.get(1).concat(""));
                            
                            copyFileUsingStream(sourse , target);
                             return true;
                        }
                        catch (Exception e){
                            JOptionPane.showMessageDialog(null,"can't find this path!");
                            return false;
                        }
                      
                      
                       
                 }
                 if(operationType.equals("rnm")) {
                        Path sourceLocation= Paths.get(cdAhlam+"/"+ArrayLst.get(0).concat(""));
                        Path targetLocation =Paths.get(cdAhlam+"/"+ArrayLst.get(1).concat(""));

                        CustomFileVisitor fileVisitor = new CustomFileVisitor(sourceLocation, targetLocation);
                        Files.walkFileTree(sourceLocation, fileVisitor);

                        OkToRemove(ArrayLst.get(0),false);
                        return true;
                    
                 }
                 if(operationType.equals("mv")) {
                     
                     
                     
                     
                      String kkf = ArrayLst.get(1);
                          String lol=kkf;
                          String finalString = ArrayLst.get(1);
                          char hs = kkf.charAt(0);
                            if(hs=='/'){
                                isDirect = true;
                                
                            }
                            if(isDirect){
                                String finalString1 = lol.substring(1,lol.length());
                                 char fs = finalString1.charAt(finalString1.length() - 1);
                                   if(fs!='/')
                                       finalString1 += "/";
                                finalString=finalString1;
                            }
                        
                        
                        try{
                           File sourse = new File(cdAhlam+"/"+ArrayLst.get(0).concat(""));
                            File target;
                            
                            if(isDirect){
                                 target = new File(this.pathForOperation+"/"+finalString+ArrayLst.get(0).concat(""));
                                   System.out.println(this.pathForOperation+"/"+finalString+ArrayLst.get(0).concat(""));
                            } else
                                 target = new File(cdAhlam+"/"+ArrayLst.get(1).concat(""));
                            
                            copyFileUsingStream(sourse , target);

                            OkToRemove(ArrayLst.get(0),false);
                            return true;
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(null,"can't find this path!");
                            return false;
                        }
                    
                 }
                    
                     
                     return true;
                }
                
                
                else{
                    JOptionPane.showMessageDialog(null,"this file is not exist!");

                    return false;
                }
             
        
        }
        catch(HeadlessException | IOException e){
             JOptionPane.showMessageDialog(null,e.getMessage());
        }
         
        return true;
    }
    
    
    
    public String checkOnLs(ArrayList<String> ArrayLst){
        String cdTemp=this.PublicPathNow;
         cdAhlam=cdTemp;
         int h =  cdAhlam.length();
        for (int i = 0; i < ArrayLst.size(); i++) {
            boolean found =false;
            String [] Dirs = listDirectories(true);
            if(! ArrayLst.get(i).equals("..")  ){
                for (int j = 0; j < Dirs.length; j++) {
                    if(ArrayLst.get(i).equals(Dirs[j]))
                    {
                        cdAhlam += ("/"+Dirs[j]);
                        found = true;
                        break;
                    }
                    
                    }

                }
            else{
                    
                    String gg ="",ggReversed = "",s="";
                    
                      for (int j = h-1; j >= 0; j--) {
                      if(cdAhlam.charAt(h-1) == '/') continue;
                        if(cdAhlam.charAt(j) == '/'){
                            h=j;
                            break;
                        }
                          gg +=cdAhlam.charAt(j);
                     }
                      StringBuilder sb = new StringBuilder(gg);
                      ggReversed = sb.reverse().toString();
                  if(ggReversed.equals("home")){
                      JOptionPane.showMessageDialog(null,"cannot back");
                      found = true;
                      break;
                  }
                  found = true;
                  // i want to get the full path 0 to h
                  s += cdAhlam.substring(0,h);
                  cdAhlam = s;
                  System.out.println(s);
                  
                }
            if(!found){
                 JOptionPane.showMessageDialog(null,"invalid path");
                 cdAhlam=cdTemp;
                 break;
            }
        }
        listDirectories(false);
        return cdAhlam;
    }
    
    
    public String separateInputsFromUser(String inputs, boolean cd,String TypeofOperationTyped){
        cdLists =new ArrayList<>();
        
        String cdStrings ="";
        if(cd){
            if(inputs.charAt(inputs.length()-1) != '/'){
                inputs+='/';
            }
            for (int i = 0; i < inputs.length(); i++) {

                if((inputs.charAt(i)) ==('/')){
                    cdLists.add(cdStrings);
                    cdStrings="";
                }
                else
                    cdStrings+=inputs.charAt(i);
            }
            if(cdLists.size()==0){
                JOptionPane.showMessageDialog(null, "note: make sure to put '/' with 'cd Command' in the end of your input!");

            }
        }
        else{
            int cnt =0,indx=0;
                for (int i = 0; i < inputs.length(); i++) {
                    
                if((inputs.charAt(i)) ==(' ') && indx <1){
                    cdLists.add(cdStrings);
//                    cnt++;
                    indx=1;
                    cdStrings="";
                }
                else
                    cdStrings+=inputs.charAt(i);
                
//                if(cnt>2){
////                    break;
//                    JOptionPane.showMessageDialog(null,"there are more than 2 spaces, please make sure that you only have a space between two names and another space in the end of your input");
//                    return "failed";
//                }
            }
                cdLists.add(cdStrings); 
            
        }
        if(cd){
            String lol= checkOnLs(cdLists);
            setCwdUpdated(lol);
            return lol;
        }
        else{
            if(cdLists.size() == 1){
              JOptionPane.showMessageDialog(null,"please make sure that you only have a space between two names and another space in the end of your input");
              return "failed";
            }
              System.out.println(cdLists.get(0));
              System.out.println(cdLists.get(1));
              
            boolean succes = checkONNameAndMakeOperation(cdLists,TypeofOperationTyped);
            if(succes)
                return "successfuly";
            else
                return "failed";
        }
        
    }
    
    
    public static long getFolderSize(File dir) {
    long size = 0;
    for (File file : dir.listFiles()) {
        if (file.isFile()) {
            System.out.println(file.getName() + " " + file.length());
            size += file.length();
        }
        else
            size += getFolderSize(file);
    }
    return size;
}
    
    
    public boolean OkToRemove(String input,boolean TypeOfRemoved){
         String[] directories;
        if(TypeOfRemoved == true)
            directories=listDirectories(true);
        else{
            directories=listDirectoriesOnFilesOnly();
        }
        boolean found = false;
        for (int i = 0; i < directories.length; i++) {
            if(input.equals(directories[i])){
                File file = new File(deletePathNow(input).concat(""));
                try{
                    if(TypeOfRemoved)
                        delete(file,true);
                    else 
                        delete(file,false);
                     found=true;
                     break;
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        }
        if(found){
            return true;
        }
        return false;
    }
    
    
    
    public String [] listDirectoriesOnFilesOnly( ){
        
                 File file = new File(cdAhlam.concat(""));
                String[] directories = file.list(new FilenameFilter() {
                  @Override
                  public boolean accept(File current, String name) {
                    
                           return new File(current, name).isFile();
                  }
                });
                
                System.out.println(Arrays.toString(directories));
                String textareainput = "";
                for (int i = 0; i < directories.length; i++) {
                 textareainput += directories[i];
                 textareainput += '\n';
                         
                    }
//                jTextArea1.setText(textareainput);
                
                return directories;
    }
    
    
    
    public String [] listDirectories(boolean removed  ){
        
                 File file = new File(cdAhlam.concat(""));
                String[] directories = file.list(new FilenameFilter() {
                  @Override
                  public boolean accept(File current, String name) {
                      if(removed)
                    return new File(current, name).isDirectory();
                      else
                           return new File(current, name).isDirectory()||new File(current, name).isFile();
                  }
                });
                
                System.out.println(Arrays.toString(directories));
                String textareainput = "";
                for (int i = 0; i < directories.length; i++) {
                 textareainput += directories[i];
                 textareainput += '\n';
                         
                    }
//                jTextArea1.setText(textareainput);
                return directories;


    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(231, 231, 232));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(158, 178, 240));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("Server Panel");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        list.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "jj" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(list);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton2.setText("Open");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(248, 248, 248))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 750, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_listMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
                new Home(PathNow,s,dis,dos ).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<String> list;
    // End of variables declaration//GEN-END:variables

}

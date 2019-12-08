/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver_distributed;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.text.*;

/**
 *
 * @author mostafa
 */

public class adminPanel implements Serializable {
    private int AdminPanelId;
    private ArrayList<String> files;
    private ArrayList<String> folders;

    public adminPanel(int AdminPanelId, ArrayList<String> files, ArrayList<String> folders) {
        this.AdminPanelId = AdminPanelId;
        this.files = files;
        this.folders = folders;
    }

    public int getAdminPanelId() {
        return AdminPanelId;
    }

    public void setAdminPanelId(int AdminPanelId) {
        this.AdminPanelId = AdminPanelId;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public ArrayList<String> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<String> folders) {
        this.folders = folders;
    }
    
    
    
}

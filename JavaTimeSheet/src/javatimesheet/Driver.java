/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatimesheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author brandan
 */
public class Driver{
    public Driver() throws FileNotFoundException, IOException, ClassNotFoundException{
        activeProfile = null;
    }
    
    public boolean checkPassword(String name, String password) throws ClassNotFoundException, FileNotFoundException, IOException{
        activeProfile = new UserProfile(name);
        activeProfile.load();
        if(password.equals(activeProfile.getPassword())){
            activeProfile = null;
            return true;
        }
        activeProfile = null;
        return false;
    }
    
    public String[] getUsers() throws FileNotFoundException{
        String[] names = null;
        ArrayList<String> n = new ArrayList<>();
        File filein = new File(Menu.USERS_PATH);
        Scanner fileInputScanner;
        if(filein.exists() && filein.canRead()){
            try{
                fileInputScanner = new Scanner(filein);
                while(fileInputScanner.hasNextLine())
                {
                    n.add(fileInputScanner.nextLine());
                }
                fileInputScanner.close();
            }
            catch(IOException e){
                System.out.println("Error: read");
            }
        }
        names = new String[n.size()];
        names = n.toArray(names);
        Arrays.sort(names);
        return names;
    }
    
    public void setUser(String name) throws IOException, ClassNotFoundException{
        activeProfile = new UserProfile(name);
        activeProfile.load();
    }

    private UserProfile activeProfile;
}

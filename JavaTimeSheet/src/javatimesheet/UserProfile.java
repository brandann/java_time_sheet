/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatimesheet;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author brandan
 */
public class UserProfile implements Serializable, Comparable{
    public UserProfile(String name){
        setName(name);
        dates = new String[0];
        jobs = new String[0];
        notes = new String[0];
        times = new Double[0];
        position = "";
        rate = "";
        index = 0;
    }
    
    private String[] add(String[] array, String item){
        String[] temp = null;
        if(!(array == null) || (array.length > 0)){
            temp = new String[array.length + 1];
            for(int i = 0;i<array.length;i++)
                temp[i] = array[i];
            temp[temp.length-1] = item;
        }
        else{
            temp = new String[1];
            temp[0] = item;
        }
        return temp;
    }
    
    private Double[] add(Double[] array, Double item){
        Double[] temp = null;
        if(!(array == null) || (array.length > 0)){
            temp = new Double[array.length + 1];
            for(int i = 0;i<array.length;i++)
                temp[i] = array[i];
            temp[temp.length-1] = item;
        }
        else{
            temp = new Double[1];
            temp[0] = item;
        }
        return temp;
    }
    
    @Override
    public int compareTo(Object o) {
        UserProfile b = (UserProfile) o;
        String other = b.getName();
        String mine = name;
        return other.compareTo(mine);
    }
    
    public String[] getJob(String date){
        String[] job = null;
        
        return job;
    }
    
    public String getJob(int indx){
        String job = "";
                
        return job;
    }
    
    public String[] getJobArray(){return jobs;}
    
    public String getName() {return name;}
    
    public String getPassword(){return password;}

    public String getPosition(){return position;}
    
    public String getRate(){return rate;}
    
    public boolean load() throws ClassNotFoundException, FileNotFoundException, IOException{
        ObjectInputStream in;
        File inFile = new File(Menu.PATH + name + ".txt");
        if(inFile.exists() && inFile.canRead()){
            in = new ObjectInputStream(new FileInputStream(inFile));
            try{
                password = (String) in.readObject();
                dates = (String[]) in.readObject();
                jobs = (String[]) in.readObject();
                notes = (String[]) in.readObject();
                times = (Double[]) in.readObject();
                position = (String) in.readObject();
                rate = (String) in.readObject();
                index = (int) in.readObject();
                in.close();
            }catch(IOException e){
                in.close();
            }
        }
        return false;
    }
    
    public void print(){
        System.out.println(name);
        for(int i = 0; i<dates.length;i++){
            System.out.println(dates[i]);
                System.out.println("\t" + jobs[i]);
                System.out.println("\t" + notes[i]);
                System.out.println("\t" + times[i]);
        }
        System.out.println(position);
        System.out.println(rate);
    }
    
    public boolean save() throws IOException{
        ObjectOutputStream out;
        File file = new File(Menu.PATH + name + ".txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        out = new ObjectOutputStream(new FileOutputStream(file));
        try{
            out.writeObject(password);
            out.writeObject(dates);
            out.writeObject(jobs);
            out.writeObject(notes);
            out.writeObject(times);
            out.writeObject(position);
            out.writeObject(rate);
            out.writeObject(index);
            out.close();
            return true;
        }catch(IOException e){
            out.close();
        }
        return false;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setNewJob(String date, String job, String note, Double time){
        dates = add(dates, date);
        jobs = add(jobs, job);
        notes = add(notes, note);
        times = add(times, time);
        index++;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setPosition(String p){
        position = p;
    }
    
    public void setRate(String r){
       rate = r;
    }

    private String name;
    private String password;
    private String[] jobs;
    private String[] dates;
    private Double[] times;
    private String[] notes;
    private int index;
    private String position;
    private String rate;
}

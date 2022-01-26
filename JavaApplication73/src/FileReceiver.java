/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;


/**
 *
 * @author Shwartskopff
 */
public class FileReceiver extends Thread {
    
    public static final int SERVER_PORT = 3456;
    public static final int BUFFER_SIZE = 4096;
    private static final String SUFFIX = "-copy";
    
    public static void main(String[] args) {
        System.out.println("Файловый приемник запущен...");
        new FileReceiver().run();
        System.out.println("Файловый приемник закрыт...");  
    }
    
    @Override
    public void run(){
        try(ServerSocket ss = new ServerSocket(SERVER_PORT);
               Socket s = ss.accept();
                InputStream ins = s.getInputStream();
                OutputStream outs = s.getOutputStream()){
            byte[] buff = new byte[BUFFER_SIZE];
            int ns = ins.read(buff);
            File file  = createFile(new String(buff, 0, ns));
            try(FileOutputStream fos = new FileOutputStream(file)){
                while(true){
                    ns = ins.read(buff);
                    if(ns<0){
                        break;
                    }
                    fos.write(buff, 0, ns);
                }
            }
            outs.write("Передача файла завершена...".getBytes());
        } catch (IOException ex) {
            System.err.println("Error#1: " + ex.getMessage());
        }
               
}

    private File createFile(String fileName) {
        fileName = fileName.trim();
        if(fileName.isEmpty()){
            fileName = "Test";
        }
        return new File(fileName + SUFFIX);
    }
}

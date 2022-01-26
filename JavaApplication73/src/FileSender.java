/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.*;
import java.net.Socket;


/**
 *
 * @author Shwartskopff
 */
public class FileSender extends Thread{
    
    public static final int SERVER_PORT = 3456;
    public static final int BUFFER_SIZE = 4096;
    
    public static void main(String[] args) {
        FileSender sender = new FileSender();
        sender.sendFile(new File(""));
    }
    
    @Override
    public void run(){
        
    }

    private void sendFile(File file) {
        try(Socket socket = new Socket("localhost", SERVER_PORT);
               OutputStream outs = socket.getOutputStream();
                InputStream ins = socket.getInputStream()){
            FileInputStream fis = new FileInputStream(file);
            byte[] buff = new byte[BUFFER_SIZE];
            int ns;
            while((ns = fis.read(buff))>1){
                outs.write(buff, 0, ns);
                outs.flush();
            }
              
    }   catch (IOException ex) {
           System.err.println("Error#2: " + ex.getMessage());
        }
    }
    
}


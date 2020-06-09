package com.corban.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(int i=0; i<5;i++){
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 10743);
            ois = new ObjectInputStream(socket.getInputStream());

            while(true){
                String message = ois.readObject().toString();
                System.out.println("Message: " + message);
            }

            //close resources
//            ois.close();
//            oos.close();
//            Thread.sleep(100);
        }
    }
}

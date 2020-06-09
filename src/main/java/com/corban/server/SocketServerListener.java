package com.corban.server;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketServerListener implements Runnable {

    private int port;
    private Thread thread;
    private long id;
    private static ServerSocket server;
    private boolean running = false;
    private long time;

    SocketServerListener(int port){
        this.port = port;
        System.out.println("Creating listneer thread on port "+ port);

    }

    public void start(){
        System.out.println("Starting listener thread on port "+ port);
        if(thread == null){
            thread = new Thread(this);
            id = thread.getId();
            thread.start();
        }
    }

    public void stop(){
        System.out.println("Attempting to stop listener on port "+ port);
        running = false;
    }

    public void run(){
        try {
            System.out.println("Listener running on port "+ port);
            server = new ServerSocket(port);
            running = true;
            time = new Date().getTime();

            Socket socket = server.accept();
            ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while(running){
                //write object to Socket

                if(new Date().getTime() - time > 1000){
                    time = new Date().getTime();
                    socketOutputStream.writeObject(time);
                    System.out.println(time);
                }

            }
            socketOutputStream.close();
            socket.close();
            System.out.println("stopped running on port " + port);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

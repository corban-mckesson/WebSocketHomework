package com.corban.server;

import java.io.*;

public class SocketServer {
    private static int port = 10743;

    public static void main(String args[]) throws IOException{
        InputStream consoleInputStream = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(consoleInputStream));

        SocketServerListener listener = new SocketServerListener(port);
        listener.start();

        while (!reader.readLine().equalsIgnoreCase("$")){
        }

        listener.stop();

        System.out.println("Shutting down Socket server!!");
    }
}

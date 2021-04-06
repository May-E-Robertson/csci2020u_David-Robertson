package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ServerClient extends Frame {
    private static ObservableList<String> fileListServer = FXCollections.observableArrayList();
    private static ObservableList<String> fileListClient = FXCollections.observableArrayList();

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter networkOut = null;
    private BufferedReader networkIn = null;

    //we can read this from the user too
    public  static String SERVER_ADDRESS = "localhost";
    public  static int    SERVER_PORT = 16789;

    public ServerClient(String cmd, String message, String username) {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "+SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("IOEXception while connecting to server: "+SERVER_ADDRESS);
        }
        if (socket == null) {
            System.err.println("socket is null");
        }
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }

        processUserInput(cmd, message, username);
    }



    //Every time a button is pushed this method is called
    //The appropriate if statement duns based off of what cmd is
    //then the socket is closed
    protected void processUserInput(String cmd, String message, String username) {
        String input = null;


        if(cmd == null){
            System.out.println("Click \"Send\" or \"Exit");

            //refresh
        } else if(cmd == "send"){


            System.out.println("sending...");



            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //download
        } else if(cmd == "exit"){
            System.out.println("exiting...\n");

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //upload
        }
    }

    public static void main(String[] args) {
        //generic file object
        File file = new File(System.getProperty("user.dir"));
        ServerClient client = new ServerClient("cmd", "msg", "uname");
    }
}

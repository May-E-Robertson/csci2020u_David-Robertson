package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;

import static java.nio.file.StandardCopyOption.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    //String serverPath = "/Users/davidrobertson/Desktop/SERVERASSG/Server/src/resources";

    public String msg = "default";

    @FXML private TextField message;
    @FXML private TextField username;

    @FXML Button exitButton;


    public void send(ActionEvent e) throws IOException {
        String cmd = "send";

        String uname = username.getText();
        String msg = message.getText();

        System.out.println(uname+": "+msg);

        ServerClient refreshclient = new ServerClient(cmd, msg, uname);
    }



    @FXML
    public void exit(ActionEvent e) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void initialize(){

    }
}

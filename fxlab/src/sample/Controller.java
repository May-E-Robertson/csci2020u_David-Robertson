package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;



public class Controller {
    @FXML private TextField Username;
    @FXML private TextField FullName;
    @FXML private TextField Email;
    @FXML private TextField PhoneNum;
    @FXML private DatePicker DateofBirth;

    public void regButtonClick(ActionEvent e){
        String uname = Username.getText();
        String fullname = FullName.getText();
        String email = Email.getText();
        String phonenum = PhoneNum.getText();
        String date = DateofBirth.toString();

        System.out.println("Username: "+uname);
        System.out.println("Full Name: "+fullname);
        System.out.println("E-mail: "+email);
        System.out.println("Phone Number: "+phonenum);
        System.out.println("Date of Birth: "+DateofBirth.getValue());
    }
}

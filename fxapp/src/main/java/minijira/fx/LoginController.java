package minijira.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import minijira.ws.DbCRUD;
import minijira.ws.WsWorker;
import java.security.MessageDigest;

public class LoginController {
    @FXML Parent parent;
    @FXML private TextField loginFld;
    @FXML private PasswordField passwordFld;
    @FXML private Label statusLbl;

    DbCRUD crud = WsWorker.getCrud();

    @FXML
    protected void handleTestBtnAction(ActionEvent event) {
        statusLbl.setText("Please wait...");
        String helloString = crud.sayHello(loginFld.getText());
        statusLbl.setText(helloString);
    }

    @FXML
    protected void handleSubmitBtnAction(ActionEvent event) {

        try {

            String plainPassword = passwordFld.getText();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainPassword.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            String password = sb.toString();

            System.out.println("Digest(in hex format):: " + password);

            String answer = crud.login(loginFld.getText(), password);

            if (answer.equals("OK")) {
                statusLbl.setText("Login is OK!");
            } else {
                statusLbl.setText("Login FAILED!");
            }

            gotoMainWindow();

        } catch (Exception ex) {
            ex.printStackTrace();
            statusLbl.setText("Exception during login.");
        }

    }

    private void gotoMainWindow() {
        try {
            parent = statusLbl.getScene().getRoot();
            Parent page = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));

            System.out.println("parent : " + parent);

            Stage stage = (Stage) parent.getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(page);
            stage.setMinWidth(600);
            stage.setMinHeight(400);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package minijira.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import minijira.ws.WsWorker;


public class Controller {
    @FXML private TextField loginFld;
    @FXML private Label statusLbl;

    @FXML
    protected void handleTestBtnAction(ActionEvent event) {
        statusLbl.setText("Please wait...");
        String helloString = WsWorker.getCrud().sayHello(loginFld.getText());
        statusLbl.setText(helloString);
    }

    @FXML
    protected void handleLoginBtnAction(ActionEvent event) {
        statusLbl.setText("Please wait...");
        String helloString = WsWorker.getCrud().sayHello(loginFld.getText());
        statusLbl.setText(helloString);
    }

}

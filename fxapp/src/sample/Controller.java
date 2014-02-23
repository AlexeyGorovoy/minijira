package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import minijira.ws.WsWorker;


public class Controller {
    @FXML private Text text;
    @FXML private TextField name;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        text.setText(WsWorker.getCrud().sayHello(name.getText()));
    }

}

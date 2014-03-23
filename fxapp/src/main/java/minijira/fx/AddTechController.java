package minijira.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import minijira.ws.DbCRUD;
import minijira.ws.Tech;
import minijira.ws.WsWorker;

/**
 * Created by Алексей on 23.03.14
 */
public class AddTechController {
    @FXML Label statusLabel;
    @FXML TextField titleField;
    @FXML TextArea descriptionArea;

    DbCRUD crud = WsWorker.getCrud();

    @FXML
    protected void addTech(ActionEvent event) {
        if (titleField.getText().isEmpty()) {
            statusLabel.setText("Title cannot be empty!");
            return;
        }
        if (descriptionArea.getText().isEmpty()) {
            statusLabel.setText("Description cannot be empty!");
            return;
        }
        statusLabel.setText("");

        Tech newTech = new Tech();
        newTech.setTitle(titleField.getText());
        newTech.setDescription(descriptionArea.getText());

        crud.mergeTech(newTech);

        cancelAdd(event);

    }

    @FXML
    protected void cancelAdd(ActionEvent event) {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}

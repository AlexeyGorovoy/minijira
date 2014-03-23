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
public class EditTechController {
    @FXML Label statusLabel;
    @FXML TextField titleField;
    @FXML TextArea descriptionArea;

    DbCRUD crud = WsWorker.getCrud();

    Tech editableTech;

    @FXML
    protected void applyEdit(ActionEvent event) {
        if (titleField.getText().isEmpty()) {
            statusLabel.setText("Title cannot be empty!");
            return;
        }
        if (descriptionArea.getText().isEmpty()) {
            statusLabel.setText("Description cannot be empty!");
            return;
        }
        statusLabel.setText("");

        editableTech.setTitle(titleField.getText());
        editableTech.setDescription(descriptionArea.getText());

        crud.mergeTech(editableTech);

        cancelEdit(event);

    }

    @FXML
    protected void cancelEdit(ActionEvent event) {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    public void setEditableTech(Tech editableTech) {
        this.editableTech = editableTech;
        titleField.setText(editableTech.getTitle());
        descriptionArea.setText(editableTech.getDescription());
    }
}

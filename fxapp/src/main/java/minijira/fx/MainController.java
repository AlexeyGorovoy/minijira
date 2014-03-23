package minijira.fx;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import minijira.ws.DbCRUD;
import minijira.ws.Tech;
import minijira.ws.WsWorker;

/**
 * Created by Алексей on 23.03.14
 */
public class MainController {

    private DbCRUD crud = WsWorker.getCrud();

    private ObservableList<Tech> techs = FXCollections.observableArrayList();

    @FXML private TableView<Tech> techsTable;
    @FXML private TableColumn<Tech, Integer> idColumn;
    @FXML private TableColumn<Tech, String> titleColumn;
    @FXML private TableColumn<Tech, String> descriptionColumn;

    @FXML
    protected void handleInitMenuAction(ActionEvent event) {
        initialize();
    }

    private void initialize() {

        techs = new ObservableListWrapper<Tech>(crud.getTechsList());

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Tech, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Tech, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Tech, String>("description"));

        // заполняем таблицу данными
        techsTable.setItems(techs);
    }

    @FXML
    protected void openAddTechWindow(ActionEvent event) {
        System.out.println("TODO - open add dialog");
    }

    @FXML
    protected void  deleteTechWindow(ActionEvent event) {
        Tech techToDelete = getSelectedTech();

        System.out.println("TODO - delete tech: " + techToDelete.getId() + " - " + techToDelete.getTitle());
    }

    private Tech getSelectedTech() {
        TableView.TableViewSelectionModel selectionModel = techsTable.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        int row = tablePosition.getRow();
        return techs.get(row);
    }
}

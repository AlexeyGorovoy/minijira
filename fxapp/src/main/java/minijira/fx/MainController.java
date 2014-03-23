package minijira.fx;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    @FXML
    private void initialize() {
        System.out.println("Initialize called!");

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
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/addTech.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Add new technology");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(techsTable.getScene().getWindow());
            stage.show();
            stage.setResizable(false);
            stage.setIconified(false);
            stage.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    if (event.getEventType().toString().equals("WINDOW_HIDING"))
                        initialize();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    protected void openEditTechWindow(ActionEvent event) {
        Tech selected = getSelectedTech();
        if (selected == null)
            return;
        System.out.println("TODO - edit tech: " + selected.getId() + " - " + selected.getTitle());
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/editTech.fxml"
                    ));
            Parent root = (Parent) loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit technology, id: " + selected.getId());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(techsTable.getScene().getWindow());

            EditTechController controller =
                    loader.<EditTechController>getController();
            controller.setEditableTech(selected);

            stage.show();
            stage.setResizable(false);
            stage.setIconified(false);
            stage.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
                    if (event.getEventType().toString().equals("WINDOW_HIDING"))
                        initialize();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void showAbout(ActionEvent event) {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("About Minijira FX Client");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(techsTable.getScene().getWindow());
            stage.show();
            stage.setResizable(false);
            stage.setIconified(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void  deleteTech(ActionEvent event) {
        Tech selected = getSelectedTech();
        if (selected == null)
            return;
        crud.removeTech(selected);
        initialize();
        System.out.println("TODO - delete tech: " + selected.getId() + " - " + selected.getTitle());
    }

    private Tech getSelectedTech() {
        TableView.TableViewSelectionModel selectionModel = techsTable.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        int row = tablePosition.getRow();
        if (row == 0 || row > techs.size() )
            return null;
        return techs.get(row);
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            Parent parent = techsTable.getScene().getRoot();
            Parent page = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

            System.out.println("parent : " + parent);

            Stage stage = (Stage) parent.getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(page);
            stage.setMinWidth(340);
            stage.setMinHeight(240);
            stage.setMaxWidth(340);
            stage.setMaxHeight(240);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

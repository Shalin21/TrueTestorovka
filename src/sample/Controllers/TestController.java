package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Interfaces.Impl.CollectionErrorList;
import sample.Interfaces.Impl.CollectionTestList;
import sample.Objects.MyError;
import sample.Objects.MyTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * Created by admin on 30.03.17.
 */
public class TestController {

    private Stage stage;
    private Parent root;
    private TestController testController;
    private FXMLLoader loader = new FXMLLoader();
    private Stage mainStage;
    public void setMainStage(Stage mainStage){this.mainStage=mainStage;}

    @FXML
    private Label labelTest;

    @FXML
    private TableView<MyError> tableView;

    @FXML
    private TableColumn<MyError, Integer> tableColumnId;

    @FXML
    private TableColumn<MyError, String> tableColumnScenary;

    @FXML
    private TableColumn<MyError, String> tableColumnResult;

    @FXML
    private TableColumn<MyError, String> tableColumnComment;

    @FXML
    private Label labelData;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnAdd;
    public MyTest test;
    MainController mainController;
    ErrorWindowController errorWindowController;
    public void initialize(){
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableColumnId.setCellValueFactory(new PropertyValueFactory<MyError, Integer>("Id"));
        tableColumnScenary.setCellValueFactory(new PropertyValueFactory<MyError, String>("Scenary"));
        tableColumnResult.setCellValueFactory(new PropertyValueFactory<MyError, String>("Result"));
        tableColumnComment.setCellValueFactory(new PropertyValueFactory<MyError, String>("Comment"));

        try {
            loader.setLocation(getClass().getResource("../Views/errorWindow.fxml"));
            root = loader.load();
            errorWindowController = loader.getController();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }


    public MyTest getTest() {
        return test;
    }

    public void setTest(MyTest test) {
        this.test = test;
        labelTest.setText(test.getTestName());
        labelData.setText("DateTime:"+test.getLocalDateTime().toString()+" // Creator:"+test.getCreator().toString());
        tableView.setItems(test.getCollectionErrorList().getCollection());

    }

    @FXML
    void onBtnAction(ActionEvent event) {
        Object source = event.getSource();
        if(!(source instanceof Button))
        {return;}

        Button clicked = (Button) source;

        Window parent = ((Node)event.getSource()).getScene().getWindow();
       // clicked.setEffect(new DropShadow());

        switch (clicked.getId()){

            case "btnOK":{
                Node source1 = (Node)event.getSource();
                Stage stage = (Stage)source1.getScene().getWindow();
                stage.hide();
                break;
            }
            case "btnDelete":{
                test.getCollectionErrorList().delete((MyError)tableView.getSelectionModel().getSelectedItem());
                break;
            }
            case "btnAdd": {
                int index = 1;
                if (test.getCollectionErrorList().getCollection().size() != 0) {
                    index = Integer.parseInt(test.getCollectionErrorList().getCollection().get(test.getCollectionErrorList().getCollection().size() - 1).getId()) + 1;
                }
                errorWindowController.setError(new MyError());
                errorWindowController.setId(index);
                showDialog();
                if(((MyError)errorWindowController.getError()).getScenary().length()>0)
                {
                    test.getCollectionErrorList().getCollection().add((MyError)errorWindowController.getError());
                }
                errorWindowController.makeNull();
                break;
            }

        }
    }

    @FXML
    void tableViewClick(MouseEvent event) {

    }

    private void showDialog()
    {

        if(stage==null)
        {
            stage=new Stage();
            stage.setTitle("Add error");
            stage.setMinHeight(423);
            stage.setMinWidth(337);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(tableView.getScene().getWindow());
        }
        stage.showAndWait();
    }
}

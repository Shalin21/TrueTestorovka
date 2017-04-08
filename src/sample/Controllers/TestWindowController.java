package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Interfaces.Impl.CollectionErrorList;
import sample.Objects.MyTest;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

/**
 * Created by admin on 31.03.17.
 */
public class TestWindowController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCreator;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;
    private MyTest test;
    @FXML
    void onBtnAction(ActionEvent event) throws Exception {
        if(txtName.getText().length()>0 && txtCreator.getText().length()>0) {
            try {
                test.setTestName(txtName.getText());
                test.setCreator(txtCreator.getText());
                LocalDateTime time = LocalDateTime.now();
                test.setLocalDateTime(time.toString());
                CollectionErrorList list = new CollectionErrorList();
                test.setCollectionErrorList(list);
            }
            catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){}
        }
        else
        {
            test=null;
        }
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
        makeNull();
    }
    @FXML
    void onBtnActionCancel(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
        makeNull();
    }
    public void makeNull(){
        txtName.setText("");
        txtCreator.setText("");
    }
    public MyTest getTest() {
        return this.test;
    }

    public void setTest(MyTest test) {
        this.test = test;
    }
}

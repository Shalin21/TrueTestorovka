package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Objects.MyError;

/**
 * Created by admin on 31.03.17.
 */
public class ErrorWindowController {
    FXMLLoader loader;
    TestController testController;
    @FXML
    private TextArea txtScenary;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextArea txtComment;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnAdd;
    MyError error;
    public void setId(int id){error.setId(Integer.toString(id));}

    @FXML
    void onBtnAction(ActionEvent event) {
        if(txtScenary.getText().length()>0 && txtResult.getText().length()>0) {
            error.setComment(txtComment.getText());
            error.setResult(txtResult.getText());
            error.setScenary(txtScenary.getText());
        }
        else
        {
            error=null;
        }
    Node source = (Node)event.getSource();
    Stage stage = (Stage)source.getScene().getWindow();
    stage.hide();
    makeNull();
    }
    public MyError getError(){return this.error;}

    public void makeNull(){//this.error=null;
    txtComment.setText("");
    txtResult.setText("");
    txtScenary.setText("");
    }
    @FXML
    void onBtnActionCancel(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.hide();
        makeNull();
    }
    public void setError(MyError ere){this.error=ere;}

}

package sample.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import sample.Interfaces.Impl.CollectionErrorList;
import sample.Interfaces.Impl.CollectionTestList;
import sample.Objects.MyError;
import sample.Objects.MyTest;


import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class MainController {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<MyTest> tableView;

    @FXML
    private TableColumn<MyTest, String> columnName;

    @FXML
    private TableColumn<MyTest, String> columnCreator;

    @FXML
    private TableColumn<MyTest, String> columnDate;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem menuLoad;

    @FXML
    private MenuItem menuSave;

    @FXML
    private Menu menuEdit;

    @FXML
    private MenuItem menuAdd;

    @FXML
    private MenuItem menuDelete;

//    @FXML
//    private ListView<MyTest> listView;
    private Stage stage;
    private Stage stage1;
    private Parent root;
    private Parent root1;
    private TestController testController;
    private TestWindowController testWindowController;
    private FXMLLoader loader = new FXMLLoader();
    private FXMLLoader loader1 = new FXMLLoader();
    private Stage mainStage;
    public void setMainStage(Stage mainStage){this.mainStage=mainStage;}

    CollectionTestList collectionTestList = new CollectionTestList();
    public void initialize(){

        collectionTestList.fillTestData();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnName.setCellValueFactory(new PropertyValueFactory<MyTest, String>("testName"));
        columnDate.setCellValueFactory(new PropertyValueFactory<MyTest, String>("localDateTime"));
        columnCreator.setCellValueFactory(new PropertyValueFactory<MyTest, String>("Creator"));
        //tableView.setItems(collectionTestList.getCollection());
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getButton().toString());
                if(event.getClickCount()==2 && event.getButton().toString()=="PRIMARY"){
                    testController.setTest((MyTest)tableView.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });

        FilteredList<MyTest> filteredData = new FilteredList<>(collectionTestList.getCollection(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getTestName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getCreator().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<MyTest> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);

        try {
            loader.setLocation(getClass().getResource("../Views/testWindow.fxml"));
            root = loader.load();
            testController = loader.getController();

            loader1.setLocation(getClass().getResource("../Views/addTest.fxml"));
            root1=loader1.load();
            testWindowController = loader1.getController();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }
    private void showDialog()
    {
        if(stage==null)
        {
            stage=new Stage();
            stage.setTitle("Test errors");
            stage.setMinHeight(423);
            stage.setMinWidth(337);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);
            //stage.getScene().getStylesheets().add("../Views/green.css");
        }
        stage.showAndWait();
    }
    private void showTestDialog()
    {
        if(stage1==null)
        {
            //Scene scene;
            stage1=new Stage();
            stage1.setTitle("Add Test");
            stage1.setMinHeight(83);
            stage1.setMinWidth(394);
            stage1.setResizable(false);
            stage1.setScene(new Scene(root1));
            stage1.initModality(Modality.WINDOW_MODAL);
            stage1.initOwner(mainStage);
            stage1.getScene().getStylesheets().add(getClass().getResource("../Views/green.css").toExternalForm());
          //  Scene sc = new Scene(root1);
           // sc.getStylesheets().add("../Views/green.css");
            //stage1.getScene().getStylesheets().add("../Views/green.css");
        }
        stage1.showAndWait();
    }
    @FXML
    void onMenuAction(ActionEvent event) {
        Object source = event.getSource();
        if(!(source instanceof MenuItem))
        {return;}

        MenuItem clicked = (MenuItem) source;

       // Window parent = ((Node)event.getSource()).getScene().getWindow();


        switch (clicked.getId()){

            case "menuLoad":{
                loadFromFile();
                break;
            }
            case "menuSave":{
                saveToFile();
                break;
            }
            case "menuAdd":{
                testWindowController.setTest(new MyTest());
                showTestDialog();

                if(((MyTest)testWindowController.getTest()).getTestName().length()>0)
                {
                    collectionTestList.add((MyTest)testWindowController.getTest());
                }
                testWindowController.makeNull();
                break;
            }
            case "menuDelete":{
                collectionTestList.delete(tableView.getSelectionModel().getSelectedItem());
                break;
            }
        }
    }

    private void loadFromFile() {
        try{
        Stage stage = (Stage) menuBar.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        JAXBContext jc = JAXBContext.newInstance(CollectionTestList.class);
        File serializedFile = fileChooser.showOpenDialog(stage);
        //UNMARSHALLING
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        StreamSource xml = new StreamSource(serializedFile);
        collectionTestList = (CollectionTestList) unmarshaller.unmarshal(xml, CollectionTestList.class).getValue();
        tableView.setItems(collectionTestList.getCollection());
         }
         catch (JAXBException e){
        System.out.println(e.toString());
         }
    }

    private void saveToFile() {
        try {
            Stage stage = (Stage)menuBar.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");

            JAXBContext jc = JAXBContext.newInstance(CollectionTestList.class);
            File serializedFile = fileChooser.showOpenDialog(stage);
            if (serializedFile.exists() == false)
                serializedFile.createNewFile();

            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            JAXBElement<CollectionTestList> jaxbElement = new JAXBElement<>(
                    new QName("List"), CollectionTestList.class, collectionTestList);
            m.marshal(jaxbElement, serializedFile);
        }
        catch (JAXBException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.toString());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}

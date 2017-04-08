package sample.Interfaces.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import sample.Interfaces.TestList;
import sample.Objects.MyTest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Created by admin on 30.03.17.
 */
@XmlRootElement(name = "Collection")
public class CollectionTestList implements TestList {

    private ObservableList<MyTest> testCollection = FXCollections.observableArrayList();
    @Override
    public void add(MyTest test){testCollection.add(test);}
    @Override
    public void delete(MyTest test){testCollection.remove(test);}

    public void setCollection(ObservableList<MyTest> testCollection){this.testCollection=testCollection;}
    @XmlElement(name = "Tests")
    public ObservableList<MyTest> getCollection(){return testCollection;}
    public CollectionErrorList getMyTest(int id){
        ObservableList<MyTest> tempTest = FXCollections.observableArrayList();
        tempTest = testCollection;
       return tempTest.get(id).getCollectionErrorList();


    }
    public void fillTestData(){
        CollectionErrorList errorList1 = new CollectionErrorList();
        errorList1.fillTestData();
        CollectionErrorList errorList2 = new CollectionErrorList();
        errorList2.fillTestData();
        CollectionErrorList errorList3 = new CollectionErrorList();
        errorList3.fillTestData();
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time.toString());
        this.testCollection.add(new MyTest(errorList1, "Test1",time.toString(),"User1"));
        this.testCollection.add(new MyTest(errorList2, "Test2",time.toString(),"User2"));
        this.testCollection.add(new MyTest(errorList3, "Test3",time.toString(),"User3"));
    }


}

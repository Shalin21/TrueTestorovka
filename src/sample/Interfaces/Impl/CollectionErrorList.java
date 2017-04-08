package sample.Interfaces.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Interfaces.ErrorList;
import sample.Objects.MyError;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by Alex on 26.03.2017.
 */
@XmlRootElement(name = "Collection")
public class CollectionErrorList implements ErrorList {

    private ObservableList<MyError> errorCollection = FXCollections.observableArrayList();

    @Override
    public void add(MyError error) {
        errorCollection.add(error);
    }

    @Override
    public void delete(MyError error) {
        errorCollection.remove(error);
    }

    @Override
    public void update(MyError error) {

    }
    @XmlElement(name = "Error")
    public ObservableList<MyError> getCollection() {
        return errorCollection;
    }
//    public MyError getError(int i){
//        errorCollection.get()
//    }
    public void setCollection(ObservableList<MyError> collection) {
        this.errorCollection = collection;
    }

    public void fillTestData(){
        this.errorCollection.add(new MyError("1","Scenary1", "Result1", "Coment1"));
        this.errorCollection.add(new MyError("2","Scenary2", "Result2", "Coment2"));
        this.errorCollection.add(new MyError("3","Scenary3", "Result3", "Coment3"));
        this.errorCollection.add(new MyError("4","Scenary4", "Result4", "Coment4"));
        this.errorCollection.add(new MyError("5","Scenary5", "Result5", "Coment5"));
        this.errorCollection.add(new MyError("6","Scenary6", "Result6", "Coment6"));
        this.errorCollection.add(new MyError("7","Scenary7", "Result7", "Coment7"));


    }
    public void printCollection(){

        int numbers=0;
        for (MyError temp: errorCollection) {
            numbers++;
            System.out.println(numbers+" "+temp.getId()+" "+temp.getScenary()+" "+temp.getResult()+" "+temp.getComment());
        }
    }
}

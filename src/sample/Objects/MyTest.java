package sample.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.Interfaces.Impl.CollectionErrorList;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;

/**
 * Created by admin on 30.03.17.
 */
public class MyTest {
    private CollectionErrorList collectionErrorList;
    private SimpleStringProperty testName;
    private SimpleStringProperty localDateTime;
    private SimpleStringProperty Creator;

    public MyTest(){}

    public MyTest(CollectionErrorList collectionErrorList, String name, String localDateTime, String creator) {
        this.setCollectionErrorList(collectionErrorList);
        this.setLocalDateTime(localDateTime);
        this.setCreator(creator);
        this.setTestName(name);
    }

    @XmlElement(name = "Errors")
    public CollectionErrorList getCollectionErrorList() {return collectionErrorList;}
    public void setCollectionErrorList(CollectionErrorList collectionErrorList) {
        this.collectionErrorList = collectionErrorList;
    }
    @XmlElement(name = "Name")
    public String getTestName() {
        return this.testName.get();
    }
    public void setTestName(String test) {
        testName = new SimpleStringProperty(test);
    }
    public StringProperty testNameProperty(){return testName;}
    @XmlElement(name = "Date")
    public String getLocalDateTimeString(){return localDateTime.toString();}
    public String getLocalDateTime() {
        return this.localDateTime.get();
    }
    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = new SimpleStringProperty(localDateTime);
    }
    @XmlElement(name = "Creator")
    public String getCreator() {
        return this.Creator.get();
    }
    public void setCreator(String creator) {
        Creator = new SimpleStringProperty(creator);
    }
    public StringProperty CreatorProperty(){return Creator;}

    public String toString(){
        return "Test:"+this.getTestName()+" | DateTime:"+this.localDateTime.get()+" | Creator:"+this.getCreator();
    }
}

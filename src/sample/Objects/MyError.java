package sample.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.soap.Text;

/**
 * Created by Alex on 26.03.2017.
 */


public class MyError {
    private SimpleStringProperty Id = new SimpleStringProperty();
    private SimpleStringProperty Scenary = new SimpleStringProperty();
    private SimpleStringProperty Result= new SimpleStringProperty();
    private SimpleStringProperty Comment= new SimpleStringProperty();


    public MyError(String id, String version, String crit, String platform) {
        Id = new SimpleStringProperty(id);
        Scenary = new SimpleStringProperty(version);
        Result = new SimpleStringProperty(crit);
        Comment = new SimpleStringProperty(platform);

    }

    public MyError() {
    }


    @XmlElement(name = "Id")
    public String getId() {
        return Id.get();
    }
    public void setId(String  id) {
        this.Id .set(id);
    }
    public StringProperty IdProperty() { return Id; }

    @XmlElement(name = "Scenary")
    public String getScenary() {
        return Scenary.get();
    }
    public void setScenary(String version) {
        this.Scenary.set(version);
    }
    public StringProperty ScenaryProperty() { return Scenary; }

    @XmlElement(name = "Result")
    public String getResult() {return Result.get();}
    public void setResult(String  crit) {this.Result.set(crit);}
    public StringProperty ResultProperty() { return Result; }

    @XmlElement(name = "Comment")
    public  String getComment() {return Comment.get();}
    public void setComment(String platform) {this.Comment.set(platform);}
    public StringProperty CommentProperty() { return Comment; }



}

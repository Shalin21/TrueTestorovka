package sample.Interfaces;

import sample.Objects.MyError;

/**
 * Created by admin on 30.03.17.
 */
public interface ErrorList {

    public void add(MyError error);


    public void update(MyError error);


    public void delete(MyError error);
}

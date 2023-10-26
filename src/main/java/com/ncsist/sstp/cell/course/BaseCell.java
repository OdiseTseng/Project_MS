package com.ncsist.sstp.cell.course;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class BaseCell<T> extends ListCell<T> implements Callback<Class<?>, Object> {

    private URL fxmlURL;

//    public BaseCell(){
//        this.fxmlURL = fxmlURL;
//    }
    public BaseCell(URL fxmlURL){
        this.fxmlURL = fxmlURL;
    }


    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null) {
            setText(null);
            setGraphic(null);
        }else if(getGraphic() == null){
            if(fxmlURL == null){
                setText(item.toString());
            }else{
                try {
                    setGraphic(FXMLLoader.load(fxmlURL, null, null, this, Charset.forName(FXMLLoader.DEFAULT_CHARSET_NAME)));
                }catch (IOException e){
                    System.out.println("IOException : " + e.getMessage());
                }
            }
            bindData(item);
        }else{
            bindData(item);
        }

    }

    public abstract void bindData(T item);

    @Override
    public Object call(Class<?> aClass) {
        return this;
    }

}

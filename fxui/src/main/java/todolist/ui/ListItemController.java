package todolist.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ListItemController {
    private Controller listViewController;

    @FXML public AnchorPane listItem;

    @FXML private TextField textField;

    @FXML private CheckBox checkBox;

    @FXML
    void destroy(){
        System.out.println("--- Deleted list item ---");
        listViewController.removeListItem(this);
    }

    public void checkBoxClicked(){
        if (checkBox.isSelected()){
            listViewController.moveItemToDone(this);
        } else {
            listViewController.moveItemToToDo(this);
        }
    }

    public boolean getSelected(){
        return checkBox.isSelected();
    }
    public void setSelected(boolean selected){
        checkBox.setSelected(selected);
    }
    public String getTextField(){return textField.getText();}
    public void setTextField(String text){
        textField.setText(text);
    }
    public void setListViewController(Controller ctrl){
        listViewController = ctrl;
    }
}

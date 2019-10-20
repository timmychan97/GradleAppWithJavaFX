package todolist.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ListLogic listLogic;

    private static List<ListItemController> listItems = new ArrayList<>();

    public Controller(){
        listLogic = new ListLogic(this, listItems);
    }
    @FXML
    Pane boxToDo;

    @FXML
    Pane boxDone;

    @FXML
    public void buttonAddClicked(){
        addListItem();
    }

    @FXML
    public void buttonSaveClicked(){
        System.out.println("Saving...");
        listLogic.saveState();
        System.out.println("Saved");
    }

    @FXML
    public void buttonLoadClicked(){
        System.out.println("Loading...");
        parseLoadFile();
        System.out.println("Loaded");
    }

    public ListItemController addListItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/todolist/ui/ListItem.fxml"));
        try
        {
            Pane listItem = fxmlLoader.load();
            ListItemController listItemController = fxmlLoader.getController();
            listItemController.setListViewController(this);

            listItems.add(listItemController);
            boxToDo.getChildren().add(listItem);

            System.out.println("--- Added list item ---");
            return listItemController;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void removeListItem(ListItemController item){
        removeListItemFromScene(item);
        listItems.remove(item);
    }
    private void removeListItemFromScene(ListItemController item){
        boxToDo.getChildren().remove(item.listItem);
        boxDone.getChildren().remove(item.listItem);
    }

    public void moveItemToDone(ListItemController item){
        boxToDo.getChildren().remove(item.listItem);
        boxDone.getChildren().add(item.listItem);
    }
    public void moveItemToToDo(ListItemController item){
        boxDone.getChildren().remove(item.listItem);
        boxToDo.getChildren().add(item.listItem);
    }

    private void parseLoadFile(){
        List<String> lines = listLogic.loadState();
        for (String line : lines) {
            ListItemController listItem = addListItem();
            String[] lineArray = line.split("#");
            if (lineArray[0].equals("true")){
                listItem.setSelected(true);
                moveItemToDone(listItem);
            } else if (lineArray[0].equals("false")) {
                listItem.setSelected(false);
            }
            if (lineArray.length > 1)
                listItem.setTextField(lineArray[1]);
        }
    }
}

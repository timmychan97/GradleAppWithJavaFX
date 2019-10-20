package todolist.ui;


import java.util.ArrayList;
import java.util.List;

public class ListLogic {

    Controller controller;

    List<ListItemController> listItems;

    private SaveLoad saveLoadFunction = new SaveLoad();

    public ListLogic(Controller parent, List<ListItemController> parentListItems){
        controller = parent;
        listItems = parentListItems;
    }

    public void saveState(){

        List<String> notes = new ArrayList<>();
        listItems.forEach(listItem -> {
            notes.add(listItem.getSelected()+"#"+listItem.getTextField());
        });
        String result = String.join("\n", notes);
        System.out.println(result);
        saveLoadFunction.saveToFile(result);

    }

    public List<String> loadState(){
        List<String> lines = saveLoadFunction.loadFile();
        return lines;
    }

}

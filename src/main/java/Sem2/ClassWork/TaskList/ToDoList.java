package Sem2.ClassWork.TaskList;

import java.util.ArrayList;
import java.util.List;


public class ToDoList {
    ArrayList<Item> items = new ArrayList<>();
    int count = 0;


    int add (String task){
        int id = count++;
        items.add(new Item(id,task));
        return id;
    }

    void delete (int id){
        for (Item item : items) {
            if (item.id == id){
                items.remove(item);
                break;
            }
        }
    }


    List<Item> view(){
        return items;
    }

}

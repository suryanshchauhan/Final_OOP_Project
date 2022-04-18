import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private String name;
    private String description;
    private ArrayList <Item> items;
    private HashMap <String, Location> connections;

    public Location(String name, String description){
        this.name = name;
        this.description = description;
        items =  new ArrayList<Item>();
        connections = new HashMap<>();
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setName(String newName){
        this.name = newName;
    }
 

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void addItem(Item newItem){
        items.add(newItem);
    }

    public boolean hasItem(String itemName){
        boolean hasItem = false;
        for(int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(itemName)){
                hasItem = true;
                break;
            }     
        }
        return hasItem;
    }

    public Item getItem(String itemName){
        for(int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(itemName)){
                return items.get(i);
            }
        }
        return null;
    }
    
    public Item getItem(int indexItem){
        if(indexItem<items.size()){
            return items.get(indexItem);
        }
        else{
            return null;
        }
        
    }

    public int numItems(){
        int numItems = items.size();
        return numItems;
    }

    public Item removeItem(String itemName){
        Item removedItem = null;
        for(int i = 0; i<items.size(); i++){
            if (items.get(i).getName().equalsIgnoreCase(itemName)){
                removedItem = items.remove(i);
                break;
            }
        }
        if(removedItem == null){
            System.out.println("Item wasn't found");
        }
        return removedItem;
    }

    public void connect(String direction, Location target){
        String dir = direction.toLowerCase();
        this.connections.put(dir, target);
    }

    public boolean canMove(String direction){
        String dir = direction.toLowerCase();
        if(connections.containsKey(dir)){
            return true;
        }
        else{
            return false;
        }
    }
    public Location getLocation(String direction){
        if(canMove(direction)){
            String dir = direction.toLowerCase();
            return connections.get(dir);
        }
        else{
            return null;
        }
    }


 
}





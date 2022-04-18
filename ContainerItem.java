import java.util.ArrayList;

public class ContainerItem extends Item {
    
    private ArrayList <Item> items;
    
    public ContainerItem(String name, String type, String description){
        super(name, type, description);
        items = new ArrayList<Item>();
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
    
    @Override
    public String toString(){
        String info = this.getName() + " [ " + this.getType() + " ] :  " + this.getDescription() + " that contains:\n";
        String itemList = "";
        for (int i = 0; i < items.size(); i++)
        {
            itemList += "+ " + items.get(i).getName() + "\n";
        }    
        return info + itemList;
    }
}

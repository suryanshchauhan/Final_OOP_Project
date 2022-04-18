public class Item{

    private String name;
    private String type;
    private String description;
    
    public Item(String name, String type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }
    public String getName(){
        return this.name;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
    
    public void setType(String newType){
        this.type = newType;
    }
    
    public void setDescription(String newDescription){
        this.name = newDescription;
    }
    
    public String toString(){
        String info = this.name + " [" + this.type + "] :  " + this.description;
        return info;
    }
    
    }
    
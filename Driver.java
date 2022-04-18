import java.util.Scanner;

public class Driver {

    private static Location currLocation;
    private static ContainerItem myInventory = new ContainerItem("Inventory", "bag", "A bag to store items that are picked up by the player");
    private static final int MAX_ITEMS = 4;
    
    public static void main(String[] args){

    createWorld();

    Scanner sc = new Scanner(System.in);
    int i = 0;
    myInventory.addItem(new Item("Mask", "clothing", "Please wear a mask when you go out"));
    System.out.println("Welcome to the game. Enter command 'help' for instructions");
    while(i>-1){
        
        System.out.print("Enter command: ");
        String command = sc.nextLine();
        command = command.toLowerCase();
        System.out.println();

        String[] splitCommand = new String[MAX_ITEMS];
            if (command.contains(" ")){
                splitCommand = command.split(" "); 
            }
            else{
                splitCommand[0] = command;
            }

        switch(splitCommand[0]){
            case "quit": 
                i = -2;
                System.out.println();
                sc.close();
                break;
            case "look":
                System.out.println(currLocation.getName() + " - " + currLocation.getDescription() + " It has the following items:");
                for (int k = 0; k < currLocation.numItems(); k++){
                    System.out.println("+ " + currLocation.getItem(k).getName());
                }
                break;
            case "examine":               
                    if(splitCommand[1]!= null){
                        if(currLocation.hasItem(splitCommand[1])){
                            System.out.println(currLocation.getItem(splitCommand[1]).toString());
                        }
                        else{
                            System.out.println("Cannot find that item");
                        }
                    }
                    else{
                        System.out.println("You must add an item with the command examine");
                    }
                    break;
            case "go":
                    if(splitCommand[1]!= null){
                        String direction = splitCommand[1];
                        if(direction.equals("north") || direction.equals("south") || direction.equals("east") || direction.equals("west")){    
                            if(currLocation.canMove(direction)){
                                currLocation = currLocation.getLocation(direction);
                                System.out.println("You are in a new location now. Use command 'look' to look around the place");
                            }
                            else{
                                    System.out.println("There is nothing here. Please choose another direction");
                                }
                        }
                        else{
                            System.out.println("Please enter a valid direction, which are north, south, east, west");
                        }
                    }
                    else{
                        System.out.println("You must add a direction with the command go");
                    }
                    break;
            case "inventory":
                    System.out.println(myInventory);
                    break;
            case "take":
                    if(splitCommand.length == 1){
                        System.out.println("Please add item name with the command take");
                    }
                    else if(splitCommand.length == 2){
                        if(currLocation.hasItem(splitCommand[1])){                                
                            myInventory.addItem(currLocation.getItem(splitCommand[1]));
                            currLocation.removeItem(splitCommand[1]);
                            System.out.println("The item has been taken");
                        }
                        else{
                            System.out.println("Cannot find that item here");
                        }
                    }
                    else if(splitCommand.length == 3){
                        if(splitCommand[2].equals("from")){
                            System.out.println("Please mention the container item (in the command) from which the pre-mentioned item should be taken.");
                        }
                    }
                    else if(splitCommand.length == MAX_ITEMS && splitCommand[2].equals("from")){
                        if(currLocation.hasItem(splitCommand[3])){                
                            if(currLocation.getItem(splitCommand[3]) instanceof ContainerItem){
                                ContainerItem curr = (ContainerItem) currLocation.getItem(splitCommand[3]);
                                if(curr.hasItem(splitCommand[1])){
                                    Item takenItem = curr.removeItem(splitCommand[1]);
                                    myInventory.addItem(takenItem);
                                    System.out.println("The item has been taken");
                                }
                                else{
                                    System.out.println("This item does not exist in " + splitCommand[3]);
                                }
                            }
                            else{
                                System.out.println("Please choose an item from which the items contained in it are allowed to be taken");
                            }
                        }    
                    }
                    else{
                        System.out.println("Invalid command. Type 'help' for instructions");
                   }
                    break;
            case "put": 
                if(splitCommand.length != MAX_ITEMS){
                    System.out.println("Invalid command. Type 'help' for instructions");
                }
                else if(splitCommand.length == MAX_ITEMS && splitCommand[2].equals("in")){
                    if(myInventory.hasItem(splitCommand[1])){
                        if(currLocation.getItem(splitCommand[3]) instanceof ContainerItem){
                            ContainerItem currItem = (ContainerItem)currLocation.getItem(splitCommand[3]);
                            Item removedItem = myInventory.removeItem(splitCommand[1]);
                            currItem.addItem(removedItem);
                            System.out.println("The item has been added to " + splitCommand[3]);
                        }
                        else{
                            System.out.println("Please choose a valid container item");
                        } 
                    }
                    else{
                        System.out.println("Your inventory does not have this item");
                    }
                }
                break;
            case "drop":
                if(splitCommand[1]!=null){
                    if(myInventory.hasItem(splitCommand[1])){
                        Item removed = myInventory.removeItem(splitCommand[1]);
                        currLocation.addItem(removed);   
                        System.out.println("The item has been dropped");    
                    }
                    else{
                        System.out.println("Cannot find that item in the inventory");
                    }        
                } 
                else{
                    System.out.println("You must add what item to drop from the inventory");
                }  
                break; 
            case "help":
                System.out.println("Useful commands:\n The 'quit' command finishes the game. \n The 'inventory' command lists the objects in your possession. A mask already exists in your inventory at the beginning of the game. \n The 'look' command prints a description of your surroundings. \n Actions for picking up or dropping items: take, drop \n To move to a direction use command 'go' with a direction (north, south, east, west). \n To examine an item, type 'examine' with the item name. \n There might be items that can contain other items. These items are called container items. Use 'take' command with 'from' command to take an item from a container item. \n Use 'put' command with 'in' command to store an item in a container item.");
                break;
            default: 
                    System.out.println("I don't know how to do that");
                    break;           
        }
    } 
}
    public static void createWorld(){
        Location office = new Location("office", "You are in the home's office right now. You see a bookshelf and some office items.");
        Location bedroom = new Location("bedroom", "You are in the bedroom now. You can a see a bed and some furniture around.");
        Location hallway = new Location("hallway", "There is an old ancient book lying on the floor of the hallway.");
        Location road = new Location("road", "This road is near a house.");
        office.connect("south", hallway);
        office.connect("west", bedroom);
        bedroom.connect("south", road);
        bedroom.connect("east", office);
        hallway.connect("north", office);
        hallway.connect("west", road);
        road.connect("north", bedroom);
        road.connect("east", hallway);
        office.addItem(new Item("Twilight", "book", "There is a book present. It is written by Stephanie Meyer"));
        office.addItem(new Item("iMac", "computer", "There is an iMac lying beside you"));
        office.addItem(new Item("Notepad", "stationery", "There is a notebook with some notes written on it"));
        ContainerItem table = new ContainerItem("Box", "furniture", "There is a box in the middle of the location. It is made of wood");
        table.addItem(new Item("Pen", "stationery", "It is a red inked fountain pen"));
        table.addItem(new Item("Calculator", "stationery", "It is a graphing calculator"));
        office.addItem(table);
        bedroom.addItem(new Item("Lamp", "decor", "The lamp is yellow, and a little worn out"));
        bedroom.addItem(new Item("Bed", "furniture", "There is a bed covered in blood stains"));
        bedroom.addItem(new Item("Chair", "furniture", "There is a rocking chair. The chair is broken and still rocking"));
        bedroom.addItem(new Item("Shirt", "clothing", "There is a blood stained shirt in the location"));
        hallway.addItem(new Item("Hammer", "tool", "There is a hammer lying in front of you"));
        hallway.addItem(new Item("Boots", "clothing", "These are mud stained leather boots"));
        hallway.addItem(new Item("Vase", "decor", "There is a broken vase"));
        hallway.addItem(new Item("Bottle", "utensil", "There is a bottle filled with water"));
        ContainerItem car = new ContainerItem("Car", "vehicle", "You can see a car which is unlocked");
        car.addItem(new Item("Overcoat", "clothing", "It is a limited edition Ralph Lauren overcoat"));
        car.addItem(new Item("Sunglasses", "accessories", "There is a pair of brown sunglasses"));
        road.addItem(car);    
        road.addItem(new Item("Banyan", "tree", "There is a banyan tree in the corner of the location"));
        currLocation = road;  
    }
}

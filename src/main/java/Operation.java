import java.util.Vector ;

public class Operation {
    Vector<Item> itemList;
    String state;
    float closeValue;

    Operation(String state){
        itemList = new Vector<Item>();
        this.state = state;
    }

    public float calculateValue(){
        if(state.equals("closed")){
            return closeValue;
        }
        return 1;
    }

    public void close(){
        closeValue = calculateValue();
        state = "closed";
    }

}

interface State{
    public float getValue(Vector<Item> ItemList);
}

public class ClosedOperation implements State{
    public float getValue(Vector<Item> ItemList) {
        return 0;
    }
}

package dds.tp0;

import java.util.ArrayList;
import java.util.Optional;

public class Operation {
    ArrayList<Item> itemList;
    OperationState state;

    Operation(){
        itemList = new ArrayList<>();
        state = new OpenedOperation(itemList);
    }

    public void addItem(Item i) throws RuntimeException{
        state.addItem(i);
    }

    public void deleteItem(Item i) throws RuntimeException{
        state.deleteItem(i);
    }

    public float getValue(){
        return state.calculateValue();
    }

    public void close() throws RuntimeException{
        if(!state.canBeClosed()){
            throw new RuntimeException("The operation can not be closed");
        }
        float closeValue = state.calculateValue();
        state = new ClosedOperation(closeValue);
    }

    public Optional<String> generateRefer(){
        String ret = null;
        if(allAreArticles()){
            ret = "This is a refer";
        }
        return Optional.ofNullable(ret);
    }

    private boolean allAreArticles(){
        return itemList.stream().allMatch(Item::isArticle);
    }
}

interface OperationState{
    float calculateValue();
    void addItem(Item i) throws RuntimeException;
    void deleteItem(Item i) throws RuntimeException;
    boolean canBeClosed();
}

class OpenedOperation implements OperationState{
    ArrayList<Item> itemList;

    OpenedOperation(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    public float calculateValue(){
        return itemList.stream().map(Item::getValue).reduce(0.0f, Float::sum);
    }

    public void addItem(Item i){
        itemList.add(i);
    }

    public void deleteItem(Item i){
        itemList.remove(i);
    }

    public boolean canBeClosed() {
        return true;
    }
}

class ClosedOperation implements OperationState{
    float closeValue;

    ClosedOperation(float closeValue){
        this.closeValue = closeValue;
    }

    public float calculateValue(){
        return closeValue;
    }

    public void addItem(Item i) throws RuntimeException {
        throw new RuntimeException("A closed operation can not be modified");
    }

    public void deleteItem(Item i) throws RuntimeException {
        throw new RuntimeException("A closed operation can not be modified");
    }

    public boolean canBeClosed() {
        return false;
    }
}
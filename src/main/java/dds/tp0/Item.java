package dds.tp0;

public class Item {
    String type;
    float value;

    Item(String type, float value){
        this.type = type;
        this.value = value;
    }

    public void setValue(float newValue){
        value = newValue;
    }

    public float getValue(){
        return value;
    }

    public boolean isArticle(){
        return type.equals("Article");
    }
}

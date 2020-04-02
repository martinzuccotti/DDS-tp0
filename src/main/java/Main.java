public class Main {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        Operation op1 = new Operation("Opened" );
        Item i1 = new Item("Service", 30.0f );
        op1.addItem(i1);
        System.out.println(op1.getValue());
        i1.setValue(41.5f);
        System.out.println(op1.getValue());
        op1.close();
        i1.setValue(441.5f);
        System.out.println(op1.getValue());
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Operation op;
    Item i1, i2;

    @BeforeEach
    void setUp() {
        op = new Operation("Opened" );
        i1 = new Item("Service", 30.0f );
        i2 = new Item("Service", 15.5f );
        op.addItem(i1);
        op.addItem(i2);
    }

    @Test
    void valueCalculate(){
        assertEquals(45.5f, op.getValue());
    }

    @Test
    void valueModified(){
        i1.setValue(40.0f);
        assertEquals(55.5f, op.getValue());
    }

    @Test
    void valueModifiedAfterClose(){
        op.close();
        i1.setValue(40.0f);
        assertEquals(45.5f, op.getValue(),
                     "No se debe modificar el valor de la operación una vez cerrada");
    }
}
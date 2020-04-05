package dds.tp0;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    Operation operation;
    Item item1, item2;

    @BeforeEach
    void setUp() {
        operation = new Operation();
        item1 = new Item("Article", 30.0f );
        item2 = new Item("Article", 45.0f );
        operation.addItem(item1);
        operation.addItem(item2);
    }

    @Test
    void testOperationValueOk(){
        assertEquals(75.0f, operation.getValue(),
                     "The expected operation value is $75.0");
        Item item3 = new Item("Service", 150.45f );
        operation.addItem(item3);
        assertEquals(225.45f, operation.getValue(),
                     "Now, the expected operation value is $225.45");
    }

    @Test
    void testChangeValues(){
        item1.setValue(40.0f);
        assertEquals(85.0f, operation.getValue(),
                     "The operation value should have changed");
        operation.close();
        item2.setValue(100.0f);
        assertEquals(85.0f, operation.getValue(),
                     "After the close, the operation should not have changed");
    }

    @Test
    void testClosedOperationCannotBeModified(){
        operation.close();
        Item item = new Item("Article", 20.0f );
        assertThrows( RuntimeException.class, ()-> operation.addItem(item),
                     "After the close, the operation can not be changed");
    }

    @Test
    void testGenerateRefer(){
        assertTrue( operation.generateRefer().isPresent(),
                   "Refer expected");
        Item item3 = new Item("Service", 150.45f );
        operation.addItem(item3);
        assertFalse(operation.generateRefer().isPresent(),
                "Refer not expected");
    }
}
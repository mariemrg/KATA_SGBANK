package bank.Model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTest {

    @Test
    public void OperationTestWithInvalidParamertes() {
        assertThrows(Exception.class, () -> new Operation(null, null, null));
    }
}

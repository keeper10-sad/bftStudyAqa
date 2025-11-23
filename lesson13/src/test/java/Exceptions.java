import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class Exceptions {

    @Test
    public void exception() throws AssertionError {
        System.out.println("поймали исключение");
        Assertions.fail();
    }
}

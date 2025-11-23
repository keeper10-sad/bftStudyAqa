import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NewExceptions {

    @Test
    public void exception() {
        try{
            Assertions.assertTrue(false);
            Assertions.fail("Ожидаем исключение");
        } catch (AssertionError e){
            System.out.println("Поймали исключение" + e);
        }
    }
}

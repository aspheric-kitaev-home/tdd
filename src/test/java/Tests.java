import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.niioep.tdd.money.Dollar;
import ru.niioep.tdd.money.Franc;

public class Tests {
    @Test
    void testMultiplication() {
        Dollar five = new Dollar(5);
        Assertions.assertEquals(new Dollar(10), five.times(2));
        Assertions.assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    void testEquality() {
        Assertions.assertTrue(new Dollar(5).equals(new Dollar(5)));
        Assertions.assertFalse(new Dollar(5).equals(new Dollar(6)));
        Assertions.assertTrue(new Franc(5).equals(new Franc(5)));
        Assertions.assertFalse(new Franc(5).equals(new Franc(6)));
    }

    @Test
    void testFrancMultiplication() {
        Franc five = new Franc(5);
        Assertions.assertEquals(new Franc(10), five.times(2));
        Assertions.assertEquals(new Franc(15), five.times(3));
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.niioep.tdd.money.Bank;
import ru.niioep.tdd.money.Expression;
import ru.niioep.tdd.money.Money;
import ru.niioep.tdd.money.Sum;

public class Tests {
    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        Assertions.assertEquals(Money.dollar(10), five.times(2));
        Assertions.assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    void testEquality() {
        Assertions.assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        Assertions.assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        Assertions.assertFalse(Money.dollar(5).equals(Money.franc(5)));
    }

    @Test
    void testCurrency() {
        Assertions.assertEquals("USD", Money.dollar(1).currency());
        Assertions.assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        Assertions.assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        Assertions.assertEquals(five, sum.augend);
        Assertions.assertEquals(five, sum.addend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(4), Money.dollar(3));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        Assertions.assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        Assertions.assertEquals(Money.dollar(1), result);
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        Assertions.assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        Assertions.assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    void testMixedAddition() {
        Money fiveBucks = Money.dollar(5);
        Money tenFranks = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFranks), "USD");
        Assertions.assertEquals(Money.dollar(10), result);
    }
}

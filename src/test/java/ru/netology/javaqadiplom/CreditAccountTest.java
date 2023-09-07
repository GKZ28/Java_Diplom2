package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {

    @Test
    public void initialBalanceBelowZeroAngGreaterThanLimit() {  // Баланс ниже 0 и больше кредитного лимита( Не должен проходить )
        assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-10_000, 5_000, 10);
        });
    }

    @Test
    public void initialBalanceBelowZeroAndLimit() {  // Баланс ниже 0 и меньше кредитного лимита( Должен проходить )
        assertDoesNotThrow(() ->  {
            CreditAccount account = new CreditAccount(-2_000, 5_000, 10);
            assertNotNull(account);
        });
    }

    @Test
    public void initialBalanceBelowZeroAndEqualLimit() {  // Баланс ниже 0 и равен кредитному лимиту( Должен проходить )
        assertDoesNotThrow(() ->  {
            CreditAccount account = new CreditAccount(-5_000, 5_000, 10);
            assertNotNull(account);
        });
    }

    @Test
    public void initialBalanceEqualToZero() {  // Баланс равен 0 ( Должен проходить )
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(0, 5_000, 10);
            assertNotNull(account);
        });
    }

    @Test
    public void initialBalanceGreaterThanZero() {  // Баланс выше 0 ( Должен проходить )
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(10_000, 5_000, 10);
            assertNotNull(account);
        });
    }

    @Test
    public void creditLimitBelowZero() {  // Кредитный лимит ниже 0 ( Не должен проходить )
        assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(0, -5_000, 10);
        });
    }

    @Test
    public void creditLimitEqualToZero() {  // Кредитный лимит равен 0 ( Должен проходить )
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(0, 0, 10);
            assertNotNull(account);
        });
    }

    @Test
    public void creditLimitGreaterThanZero() {  // Кредитный лимит выше 0 ( Должен проходить )
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(0, 5_000, 10);
            assertNotNull(account);
        });
    }

    @Test
    public void rateBelowZero() {  // Ставка ниже 0 ( Не должна проходить )
        assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(0, 5_000, -2);
        });
    }

    @Test
    public void rateEqualToZero() {  // Ставка равна 0 ( Должна проходить )
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(0, 5_000, 0);
            assertNotNull(account);
        });
    }

    @Test
    public void rateGreaterThanZero() {  // Ставка выше 0 ( Должна проходить )
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(0, 5_000, 2);
            assertNotNull(account);
        });
    }

    @Test
    public void payBelowZero () {  // Оплата отрицательной суммой
        CreditAccount account = new CreditAccount(10_000,5_000,5);
        assertFalse(account.pay(-50));
        assertEquals(10_000,account.getBalance());
    }

    @Test
    public void payEqualZero () {  // Оплата нулем
        CreditAccount account = new CreditAccount(10_000,5_000,5);
        assertFalse(account.pay(0));
        assertEquals(10_000,account.getBalance());
    }

    @Test
    public void payBelowLimit () {  // Оплата меньше лимита
        CreditAccount account = new CreditAccount(10_000,5_000,5);
        assertTrue(account.pay(10_000));
        assertEquals(0,account.getBalance());
    }

    @Test
    public void payEqualLimit () {  // Оплата равна лимиту
        CreditAccount account = new CreditAccount(10_000,5_000,5);
        assertTrue(account.pay(15_000));
        assertEquals(-5_000,account.getBalance());
    }

    @Test
    public void payGreaterLimit () {  // Оплата выше лимита
        CreditAccount account = new CreditAccount(10_000,5_000,5);
        assertFalse(account.pay(20_000));
        assertEquals(10_000,account.getBalance());
    }

    @Test
    public void addGreaterZero() { // Добавдение положительной суммы
        CreditAccount account = new CreditAccount(1_000,5_000,15);
        assertTrue(account.add(3000));
        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void addEqualZero() { // Добавление 0
        CreditAccount account = new CreditAccount(1_000,5_000,15);
        assertFalse(account.add(0));
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void addBelowZero() { // Добавление отрицательной суммы
        CreditAccount account = new CreditAccount(1_000,5_000,15);
        assertFalse(account.add(-1_000));
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void yearChangerWhenBalanceGreaterZero() { // Проценты при положительном балансе
        CreditAccount account = new CreditAccount(1_000,5_000,15);
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void yearChangerWhenBalanceEqualZero() { // Проценты при нулевом балансе
        CreditAccount account = new CreditAccount(0,5_000,15);
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void yearChangerWhenBalanceBelowZero() { // Проценты при отрицательном балансе
        CreditAccount account = new CreditAccount(-1_000,5_000,15);
        Assertions.assertEquals(-150, account.yearChange());
    }

}

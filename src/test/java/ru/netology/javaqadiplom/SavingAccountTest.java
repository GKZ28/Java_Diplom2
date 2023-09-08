package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void testPay() {
        SavingAccount account = new SavingAccount(2000, 1000, 10_000, 5);
        account.pay(-1_000);

        Assertions.assertEquals(2000 - 1000, account.getBalance());
    }

    @Test
    public void testPayMoreThanMinBalance() {
        SavingAccount account = new SavingAccount(2000, 1000, 10_000, 5);
        account.pay(2000);

        Assertions.assertEquals(1000, account.getMinBalance());
    }

    @Test
    public void testAddZero() {
        SavingAccount account = new SavingAccount(2000, 1000, 10_000, 5);
        account.add(0);

        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void testAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(2000, 1000,10_000, 5);
        account.add(11_000);

        Assertions.assertEquals(10_000, account.getMaxBalance());
    }

    @Test
    public void testYearChange() {
        SavingAccount account = new SavingAccount(2000, 1000, 10_000, 5);
        account.yearChange();

        Assertions.assertEquals(100, account.yearChange());
    }

    @Test
    public void testYearChangeWithNegativeBalance() {
        SavingAccount account = new SavingAccount(-2000, 1000, 10_000, 5);
        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }
}

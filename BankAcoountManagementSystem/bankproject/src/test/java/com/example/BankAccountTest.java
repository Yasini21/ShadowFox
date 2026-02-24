package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("TestUser", 1000);
        account.deposit(500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount("TestUser", 1000);
        account.withdraw(300);
        assertEquals(700, account.getBalance());
    }

    @Test
    void testInsufficientBalance() {
        BankAccount account = new BankAccount("TestUser", 1000);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(2000)
        );
        assertEquals("Insufficient balance", exception.getMessage());
    }

    @Test
    void testNegativeDeposit() {
        BankAccount account = new BankAccount("TestUser", 1000);
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-100));
    }
}
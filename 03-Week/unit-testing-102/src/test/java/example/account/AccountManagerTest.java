package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    AccountManager accountManager = new AccountManagerImpl();

    @Test
    void givenCustomerWithEnoughBalance_whenWithdraw_thenSucceed(){
        // arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        // act
        String result = accountManager.withdraw(customer, 30);

        // assert
        Assertions.assertEquals(70, customer.getBalance());
        Assertions.assertEquals("success", result);
    }

    @Test
    void givenCustomerWithCreditAllowed_whenWithdraw_thenSucceed(){
        Customer customer = new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        // act
        String result = accountManager.withdraw(customer, 200);

        // assert
        Assertions.assertEquals(-100, customer.getBalance());
        Assertions.assertEquals("success", result);
    }


    @Test
    void givenCustomerWithCreditNotAllowed_whenWithdraw_thenFail(){
        Customer customer = new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(false);
        // act
        String result = accountManager.withdraw(customer, 200);

        // assert
        Assertions.assertEquals(100, customer.getBalance());
        Assertions.assertEquals("insufficient account balance", result);
    }


    @Test
    void givenCustomerIsVip_whenWithdraw_thenSucceed(){
        Customer customer = new Customer();
        customer.setBalance(0);
        customer.setCreditAllowed(true);
        customer.setVip(true);
        // act
        String result = accountManager.withdraw(customer, 1100);

        // assert
        Assertions.assertEquals(-1100, customer.getBalance());
        Assertions.assertEquals("success", result);
    }


    @Test
    void givenCustomerIsNotVip_whenWithdraw_thenFail(){
        Customer customer = new Customer();
        customer.setBalance(0);
        customer.setCreditAllowed(true);
        customer.setVip(false);
        // act
        String result = accountManager.withdraw(customer, 1100);
        // assert
        Assertions.assertEquals(0, customer.getBalance());
        Assertions.assertEquals("maximum credit exceeded", result);
    }



}

package example.store.store;

import example.account.AccountManager;
import example.account.AccountManagerImpl;
import example.account.Customer;
import example.store.Product;
import example.store.Store;
import example.store.StoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    @Test
    void givenProductWithSufficientQuantity_whenBuy_thenSucceed(){
        //arrange
        AccountManager accountManager = new AccountManagerImpl();
        Product product = new Product();
        product.setQuantity(5);

        Customer customer = new Customer();
        Store store = new StoreImpl(accountManager);
        //act

        store.buy(product, customer);
        //assert
        Assertions.assertEquals(4, product.getQuantity());
    }

    public static class AlwaysSuccessAccountManager implements AccountManager{

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }
}

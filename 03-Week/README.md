## Issue 

```java

 public String withdraw(Customer customer, int amount) {
        int expectedBalance = customer.getBalance() - amount;

        if (expectedBalance < 0) {
            if (!customer.isCreditAllowed()) {
                return "insufficient account balance";
            } else if ( expectedBalance > MAX_CREDIT && !customer.isVip()) {
                return "maximum credit exceeded";
            } 
        }
        customer.setBalance(expectedBalance);
        return "success";
    }
}
```

 - The issue was in `expectedBalance` when it is a negative value ; it is always been less than 
   `zero` which makes it always less than the `MAX_CREDIT`

### FIX
 - add Math.abs to expectedBalance in the condition 

``` java
  public String withdraw(Customer customer, int amount) {
        int expectedBalance = customer.getBalance() - amount;

        if (expectedBalance < 0) {
            if (!customer.isCreditAllowed()) {
                return "insufficient account balance";
            } else if (Math.abs(expectedBalance) > MAX_CREDIT && !customer.isVip()) {
                return "maximum credit exceeded";
            } 
        }
        customer.setBalance(expectedBalance);
        return "success";
    }
```

   
   

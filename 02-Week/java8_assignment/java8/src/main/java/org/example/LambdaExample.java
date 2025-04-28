package org.example;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaExample {

   static Predicate<User> getUsersWithAgeGreaterThanNum(int age){
       return new Predicate<User>() {
           @Override
           public boolean test(User user) {
               return user.age > age;
           }
       };
    }

    public static void main(final String[] args) {

        final UsersRepository repository = new UsersRepository();

        banner("Listing all users");
        // SOLVED all users
        repository.select(null, null);

        banner("Listing all active users");
        // SOLVED With functional interfaces declared
        Predicate<User> activeUserPredicate = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.active;
            }
        };
        repository.select(activeUserPredicate, null);

        banner("Listing all active users - lambda");
        // SOLVED With functional interfaces used directly
        repository.select(user -> user.active, null);


        banner("Listing users with age > 5 sorted by name");

//        Predicate<User> greaterThanFiveUsers = new Predicate<User>() {
//            @Override
//            public boolean test(User user) {
//                return user.age > 5;
//            }
//        };

        Comparator<User> sortedByNameUsers = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.name.compareTo(user2.name);
            }
        };

        repository.select(getUsersWithAgeGreaterThanNum(5), sortedByNameUsers);


        banner("Listing users with age > 5 sorted by name - lambda");

        repository.select(user -> user.age>5, Comparator.comparing(user -> user.name));

        banner("Listing users with age < 10 sorted by age");

        Predicate<User> lessThanTenUsers = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age < 10;
            }
        };

        Comparator<User> sortedByAgeUsers = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                if(user1.age > user2.age){
                    return 1;
                }else{
                    return -1;
                }
            }
        };

        repository.select(lessThanTenUsers, sortedByAgeUsers);

        banner("Listing users with age < 10 sorted by age - lambda");
        repository.select(user -> user.age < 10, Comparator.comparingInt(user -> user.age));

        banner("Listing active users sorted by name");
        repository.select(activeUserPredicate, sortedByNameUsers);

        banner("Listing active users sorted by name - lambda");
        repository.select(user -> user.active, Comparator.comparing(user -> user.name));

        banner("Listing active users with age > 8 sorted by name");
        repository.select(getUsersWithAgeGreaterThanNum(8), sortedByNameUsers);

        banner("Listing active users with age > 8 sorted by name - lambda");
        repository.select(user-> user.age > 8, Comparator.comparing(user -> user.name));

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
    
}

class UsersRepository {
    List<User> users;

    UsersRepository() {
        users = Arrays.asList(
            new User("Seven", 7, true),
            new User("Four", 4, false),
            new User("Eleven", 11, true),
            new User("Three", 3, true),
            new User("Nine", 9, false),
            new User("One", 1, true),
            new User("Twelve", 12, true));
    }

    void select(final Predicate<User> filter, final Comparator<User> order) {
        Stream<User> usersStream = users.stream();

        if (filter != null) {
            usersStream = usersStream.filter(filter);
        }
        if (order != null) {
            usersStream = usersStream.sorted(order);
        }
        usersStream.forEach(System.out::println);
    }
}

class User {
    String name;
    int age;
    boolean active;

    User(final String name, final int age, boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "\t| " + age;
    }
}
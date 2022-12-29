package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Lada", 115)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Opel", 575)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 7547)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("ZAZ", 446)));

      List<User> users1 = userService.listUsers();
      users1.forEach(System.out::println);

      userService.getUserByCar("Lada", 115).forEach(System.out::println);

      context.close();
   }
}
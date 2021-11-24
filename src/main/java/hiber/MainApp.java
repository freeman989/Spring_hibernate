package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = //Создать контекст.
            new AnnotationConfigApplicationContext(AppConfig.class); //Искать бины в AppConfig.class

      UserService userService = context.getBean(UserService.class);

      //Cоздать пользователей.
      User user1 = new User("Dominic", "Toretto", "toretto@gm.com");
      User user2 = new User("Daniel", "Morales", "morales@gm.com");
      User user3 = new User("Frank", "Martin", "martin@gm.com");
      User user4 = new User("Max", "Rokatansky", "rokatansky@gm.com");
      //Вручить им автомобили.
      user1.setCar(new Car("Mers", 600));
      user2.setCar(new Car("Porsh", 911));
      user3.setCar(new Car("Folc", 412));
      user4.setCar(new Car("Audi", 100));

      //Добавить их в БД.
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      //Вывести на экран.
      for (User user : userService.listUsers()) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Сar = "+user.getCar());
         System.out.println();
      }
      //Найти водителя по его автомобилю.
      User foundUser = userService.hisCar("Mers", 600);

      System.out.println("Найденный пользователь:");
      System.out.println(foundUser.getId()+" "+foundUser.getFirstName()+" "+foundUser.getLastName()
              +" "+foundUser.getEmail()+" "+foundUser.getCar());

      context.close();
   }
}

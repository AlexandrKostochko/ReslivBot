package by.resliv;

import org.telegram.telegrambots.ApiContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class BotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(BotApplication.class, args);
    }

}

package com.papita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main class, where project starts in.
 *
 * @author Listratenko Stanislav
 * @version 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
public class Mafia {

    /**
     * Main method for starting application. Runs spring application.
     *
     * @param args arguments of application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Mafia.class, args);
    }
}

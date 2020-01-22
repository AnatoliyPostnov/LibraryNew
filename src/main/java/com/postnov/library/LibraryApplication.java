package com.postnov.library;

import com.postnov.library.service.OtherService.CountIdService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LibraryApplication {

    public static CountIdService countIdService;

    public LibraryApplication(CountIdService countIdService) {
        this.countIdService = countIdService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
        countIdService.countId();
    }
}

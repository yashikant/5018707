package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Test Constructor Injection
        BookService bookServiceConstructor = (BookService) context.getBean("bookService");
        bookServiceConstructor.performService();

        // Test Setter Injection
        BookService bookServiceSetter = (BookService) context.getBean("bookServiceSetter");
        bookServiceSetter.performService();
    }
}

package com.example.objspringrestdatajpa.services;

import com.example.objspringrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calcularPriceTest() {
        //configuración de la prueba
        Book b = new Book("x","x",1000,49.99, LocalDate.of(1998,6,10),true);
        BookPriceCalculator calculator = new BookPriceCalculator();
        //se ejecuta el comportamiento a testear
        double price = calculator.calcularPrice(b);
        //comprobaciones aserciones
        assertTrue(price > 0);
        assertEquals(52.980000000000004, price);
    }
}
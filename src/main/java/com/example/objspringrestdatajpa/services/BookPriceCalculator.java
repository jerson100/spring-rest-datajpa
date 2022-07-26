package com.example.objspringrestdatajpa.services;

import com.example.objspringrestdatajpa.entities.Book;

public class BookPriceCalculator {

    public double calcularPrice(Book book){
        double price = book.getPrice();
        if(book.getPages() > 1000){
            price += 5;
        }
        price += 2.99;
        return price;
    }

}

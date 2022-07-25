package com.example.objspringrestdatajpa;

import com.example.objspringrestdatajpa.entities.Book;
import com.example.objspringrestdatajpa.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObjSpringRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObjSpringRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);
		//CRUD
		//Crear libro
		Book newBook1 = new Book(null, "Homo Deus", "Yuval Noa", 450, 29.99, LocalDate.of(2018, 12, 11), true);
		Book newBook2 = new Book(null, "Homo Deus", "Yuval Noa", 450, 19.99, LocalDate.of(2013, 12, 11), true);

		//Almacenar los libros
		repository.save(newBook1);
		repository.save(newBook2);

		//Recuperar todos los libros
		System.out.println(repository.findAll().toString());

		//Borrar un libro
		repository.deleteById(1L);

		System.out.println(repository.findAll().toString());
	}

}

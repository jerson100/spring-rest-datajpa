package com.example.objspringrestdatajpa.controllers;

import com.example.objspringrestdatajpa.entities.Book;
import com.example.objspringrestdatajpa.repositories.BookRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookRepository bookRepository;

    /*
    * Gracias a la inyecci{on de dependencias el contenedor de beans
    * se encargar{a de inyectarlo por el constructor la instancia necesaria
    * */
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //Buscar todos los libros
    @GetMapping(path = "/api/v1/books")
    public List<Book> findAll (){
        return bookRepository.findAll();
    }

    //Buscar un solo libro en la bd
//    @GetMapping(path = "/api/v1/books/{idBook}")
//    public Book getBookById(@PathVariable(name = "idBook") Long id){
//        //se envuelve en una clase para trabajar con el valor null
//        Optional<Book> opb = bookRepository.findById(id);
//        //Primera forma de hacerlo
////        if(opb.isPresent()){
////            return opb.get();
////        }else{
////            return null;
////        }
//        //gracias al Optional podemos manejar el valor null
//        //si no es nulo devuelve el objeto libro, de lo contrario null
//        return  opb.orElse(null);
//    }

    @GetMapping(path = "/api/v1/books/{idBook}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "idBook") Long id){
        Optional<Book> opb = bookRepository.findById(id);
        if(opb.isPresent()){
            return ResponseEntity.ok(opb.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Crear un nuevo libro en la base de datos
    //el objeto header tiene info de la cabecera de la solicitud :)
    @PostMapping(path = "/api/v1/books")
    public Book createBook(@RequestBody Book newBook, @RequestHeader HttpHeaders header){
        return bookRepository.save(newBook);
    }
    //Actualizar un libro de la base de datos
    //Eliminar un libro de la base de datos


}

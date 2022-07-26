package com.example.objspringrestdatajpa.controllers;

import com.example.objspringrestdatajpa.entities.Book;
import com.example.objspringrestdatajpa.repositories.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "api/v1/")
public class BookController {

    //Mostrar mensajes de error, etc.
    private final Logger log = LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    /*
    * Gracias a la inyecci{on de dependencias el contenedor de beans
    * se encargar{a de inyectarlo por el constructor la instancia necesaria
    * */
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //Buscar todos los libros
    @Operation(summary = "Obtener todos los libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontró el libro",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "401", description = "No está autorizado para ver este recurso",
                    content = @Content) ,
            @ApiResponse(responseCode = "403", description = "No tiene privilegios para ver este recurso",
                    content = @Content) })
    @GetMapping(path = "/books")
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

    @GetMapping(path = "/books/{idBook}")
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
    @PostMapping(path = "/books")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook, @RequestHeader HttpHeaders header){
        if(newBook.getId() != null){
            log.warn("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookRepository.save(newBook));
    }

    //Actualizar un libro de la base de datos
    @PutMapping(path = "/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        if(book.getId()==null){
            log.warn("trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookRepository.save(book));
    }

    //Eliminar un libro de la base de datos
    @DeleteMapping(path = "/books/{idBook}")
    public ResponseEntity<Book> deleteBookById(@PathVariable long idBook){
        if(!bookRepository.existsById(idBook)){
            log.warn("trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(idBook);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/books")
    public ResponseEntity<Book> deleteBooks(){
        log.info("Deleting all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}

package com.example.objspringrestdatajpa.controllers;

import com.example.objspringrestdatajpa.entities.Book;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;
    //para que spring lo inyecte
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    //para que spring inyecte el port
    @LocalServerPort
    private int port;

    //antes de cada método
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        //hace una petición GET
//        testRestTemplate.getForEntity()
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/v1/books", Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Book> books = Arrays.asList(response.getBody());
        assertEquals(books.size(), 0);
    }

    @Test
    void hello() {
        //hace una petición GET
        ResponseEntity<String> response = testRestTemplate.getForEntity("/helloworld",String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World replicando.",response.getBody());
    }

    @Test
    void getBookById() {
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/v1/books/1", Book.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createBook() {

        HttpHeaders headers = new HttpHeaders();
        //el tipo que estoy enviando al servidor
        headers.setContentType(MediaType.APPLICATION_JSON);
        //el tipo que voy a recibir como respuesta
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                    {
                        "title": "Example11",
                        "author": "Example11",
                        "pages": 401,
                        "price": 19,
                        "releaseDate": "2013-12-10",
                        "online": false
                    }
                """;

        //realizamos una petición desde JAVA
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Book> response = testRestTemplate.exchange("/api/v1/books", HttpMethod.POST, request, Book.class);

        Book book = response.getBody();

        assertEquals(1, book.getId());
        assertEquals("Example11", book.getTitle());

    }
}
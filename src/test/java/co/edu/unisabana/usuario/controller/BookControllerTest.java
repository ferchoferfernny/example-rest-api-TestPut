package co.edu.unisabana.usuario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import co.edu.unisabana.usuario.AbstractTest;
import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.dto.BookReponse;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;

public class BookControllerTest extends AbstractTest{

    @Autowired
  private TestRestTemplate restTemplate;

  private static final String PATH_RERGIS = "/book/register";
  private static final String PATH_LOOKFOR = "/book/search";

/**
   * Given: Dado que (Precondicones)
   * When: Que voy hacer/probar
   * Then: Cual debería ser el resultado
   */

 
   @Test
    public void Given_register_only_new_book_When_registerBook_Then_bookResponse_Add_book (){
        BookDto dto = new BookDto("El Resplandor", 1972, "Stephen King", "Comercial", "suave");

        ResponseEntity<BookReponse> result = restTemplate.postForEntity(PATH_RERGIS,dto, BookReponse.class);
        assertEquals("Nuevo libro registrado",result.getBody().getData());
    }

    @Test
    public void Given_register_only_new_book_When_registerBook_Then_bookResponse_ataulziado (){
        BookDto dto = new BookDto("El Resplandor", 1972, "Stephen King", "Comercial", "suave");

        restTemplate.postForEntity(PATH_RERGIS,dto, BookReponse.class);
        ResponseEntity<BookReponse> result = restTemplate.postForEntity(PATH_RERGIS,dto, BookReponse.class);
        assertEquals("Actualizada cantidad",result.getBody().getData());
    }

    @Test
    public void Given_search_route_When_registerBook_Then_retrun_hola (){

        ResponseEntity<String> result = restTemplate.getForEntity(PATH_LOOKFOR,
        String.class);

        assertEquals("hola",result.getBody());
    }
    
    
}

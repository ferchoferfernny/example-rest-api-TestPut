package co.edu.unisabana.usuario.controller;

import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.dto.BookReponse;
import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.service.library.LookUp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final RegisterBookLibrary registerBookLibrary;
    private final LookUp lkLib;


    public BookController(RegisterBookLibrary registerBookLibrary) {
        this.registerBookLibrary = registerBookLibrary;
    }

    // Pendiente validar uso de exception handler
    // explicar camel case
    @PostMapping("/register")
    @ResponseBody
    public BookReponse registerBook(@RequestBody BookDto bookDto) {
        int result = registerBookLibrary.registerBook(bookDto.toModel());
        if (result == 1) {
            return new BookReponse("Actualizada cantidad");
        }
        if (result == 3){
            return new BookReponse("No se tiene mas capacidad para es libro");
        }


        return new BookReponse("Nuevo libro registrado");
    }

    @GetMapping("/search")
    public String registerBook() {
        return "hola";
    }

    @GetMapping("/search/{authorName}")
    public String authorBook(@PathVariable String authorName) {

        return lkLib.searchAutorBooks(authorName).toString();
    }
}

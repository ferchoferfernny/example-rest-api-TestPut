package co.edu.unisabana.usuario.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unisabana.usuario.delegate.CallService;
import co.edu.unisabana.usuario.presentation.dto.BookDto;
import co.edu.unisabana.usuario.presentation.dto.BookReponse;
import co.edu.unisabana.usuario.service.library.LookUp;
import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;

@RestController
@RequestMapping("/book")
public class BookController {

    private final CallService cs;

    

    public BookController(CallService cs) {
        this.cs = cs;
    }

    // Pendiente validar uso de exception handler
    // explicar camel case
    @PostMapping("/register")
    @ResponseBody
    public BookReponse registerBook(@RequestBody BookDto bookDto) {
        return cs.registerBook(bookDto);
    }

    @GetMapping("/search")
    public String registerBook() {
        return "hola";
    }

    @GetMapping("/search/{authorName}")
    public String authorBook(@PathVariable String authorName) {

        return cs.authorBook(authorName);
    }
}
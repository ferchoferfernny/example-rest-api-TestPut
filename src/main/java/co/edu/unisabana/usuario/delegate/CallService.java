package co.edu.unisabana.usuario.delegate;

import co.edu.unisabana.usuario.presentation.dto.BookDto;
import co.edu.unisabana.usuario.presentation.dto.BookReponse;
import co.edu.unisabana.usuario.service.library.LookUp;
import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;

public class CallService {

    private final RegisterBookLibrary registerBookLibrary;
    private final LookUp lkLib;

    public CallService(RegisterBookLibrary registerBookLibrary, LookUp lkLib) {
        this.registerBookLibrary = registerBookLibrary;
        this.lkLib = lkLib;
    }

    
    public BookReponse registerBook (BookDto bookDto)
    {
        int result = registerBookLibrary.registerBook(bookDto.toModel());
        if (result == 1) {
            return new BookReponse("Actualizada cantidad");
        }
        if (result == 3){
            return new BookReponse("No se tiene mas capacidad para es libro");
        }


        return new BookReponse("Nuevo libro registrado");
    }
    
    public String authorBook(String authorName) {

        return lkLib.searchAutorBooks(authorName).toString();
    }
    
}

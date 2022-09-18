package co.edu.unisabana.usuario.data.app;

import co.edu.unisabana.usuario.service.library.model.Book;

public interface RegisterBookPort {

    void registerBook(Book newBook);

    void eliminateQuantity();
}


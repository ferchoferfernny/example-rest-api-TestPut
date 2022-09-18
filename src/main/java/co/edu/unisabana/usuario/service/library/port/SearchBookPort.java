package co.edu.unisabana.usuario.service.library.port;

import java.util.ArrayList;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;


public interface SearchBookPort {

    boolean validateExistsBook(String nameBook);

    int validateQuantity(String nameBook);

    ArrayList <BookEntity> searchbBookByAutor(String authorName);


}

package co.edu.unisabana.usuario.service.library.port;

import java.util.ArrayList;


public interface SearchBookPort {

    boolean validateExistsBook(String nameBook);

    int validateQuantity(String nameBook);

    ArrayList searchbBookByAutor(String authorName);


}

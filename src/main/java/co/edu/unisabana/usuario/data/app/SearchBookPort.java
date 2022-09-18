package co.edu.unisabana.usuario.data.app;

import java.util.ArrayList;


public interface SearchBookPort {

    boolean validateExistsBook(String nameBook);

    int validateQuantity(String nameBook);

    ArrayList searchbBookByAutor(String authorName);


}

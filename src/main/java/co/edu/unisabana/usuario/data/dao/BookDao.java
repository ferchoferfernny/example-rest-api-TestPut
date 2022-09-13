package co.edu.unisabana.usuario.repository.dao;

import co.edu.unisabana.usuario.data.app.AddBookPort;
import co.edu.unisabana.usuario.data.app.RegisterBookPort;
import co.edu.unisabana.usuario.data.app.SearchBookPort;
import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.model.Book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class BookDao implements SearchBookPort, RegisterBookPort, AddBookPort {

    public static List<BookEntity> listBooks = new ArrayList<>();

    @Override
    public boolean validateExistsBook(String nameBook) {
        AtomicBoolean exists = new AtomicBoolean(false);
        listBooks.forEach(book -> {
            if (book.getName().equals(nameBook)) {
                exists.set(true);
            }
        });
        return exists.get();
    }


    @Override
    public void registerBook(Book newBook) {
        BookEntity bookEntity = BookEntity.fromModel(newBook);
        bookEntity.setId(listBooks.size() + 1);
        listBooks.add(bookEntity);
    }

    @Override
    public boolean addBook(String name) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(name)) {
                book.setQuantity(book.getQuantity() + 1);
                return true;
            }
        }
        throw new IllegalArgumentException("No existe libre para actualizar");
    }


    @Override
    public int validateQuantity(String nameBook) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(nameBook)) {
                return book.getQuantity();
            }
        }
        throw new IllegalArgumentException("No existe el libro ");
    }


    
    @Override
    public ArrayList searchbBookByAutor(String authorName) {
        ArrayList <BookEntity> lookupBookDto = new ArrayList <>();
        listBooks.forEach(book -> {
            if (book.getAuthor().equals(authorName))
            {
             lookupBookDto.add(book);
            }
        });
        return lookupBookDto;
    }


    @Override
    public void eliminateQuantity() {
        //Este metodo elimina cualquier cantidad que exista dentro todos los libros
        listBooks.forEach(book ->
        {
            book.setQuantity(0);
        });
    }
}

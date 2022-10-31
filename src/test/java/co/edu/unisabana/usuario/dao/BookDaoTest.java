package co.edu.unisabana.usuario.dao;

import co.edu.unisabana.usuario.repository.dao.BookDao;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BookDaoTest {
    private final BookDao bookDao = new BookDao();

    @Test
    public void Given_nameBook_When_validateExistsBook_and_exists_Then_Return_True(){
        Book newBook = new Book("Dracula", 1897, "Bram Stoker", false, CategoryBook.SOFT_COVER);
        bookDao.registerBook(newBook);
        assertTrue(bookDao.validateExistsBook(newBook.getName()));
    }

    @Test
    public void Given_nameBook_When_validateExistsBook_and_not_exists_Then_Return_False(){
        String nameBook = "Orgullo y prejuicio";
        assertFalse(bookDao.validateExistsBook(nameBook));
    }

    @Test
    public void Given_newBook_When_registerBook_Then_Add_book_in_listBooks(){
        Book newbook = new Book("Dracula", 1897, "Bram Stoker", false, CategoryBook.SOFT_COVER);
        int listBooksSize = BookDao.listBooks.size();
        bookDao.registerBook(newbook);
        assertEquals(listBooksSize+1, BookDao.listBooks.size());
    }
    
    @Test
    public void Given_nameBook_When_addBook_and_exists_in_listBooks_Then_return_True(){
        Book newBook = new Book("Dracula", 1897, "Bram Stoker", false, CategoryBook.SOFT_COVER);
        bookDao.registerBook(newBook);
        assertTrue(bookDao.addBook(newBook.getName()));
    }

    @Test
    public void Given_nameBook_When_addBook_and_not_exists_in_listBooks_Then_throw_IllegalArgumentException(){
        String nameBook = "Orgullo y prejuicio";
        assertThrows(IllegalArgumentException.class,()->{
            bookDao.addBook(nameBook);});
    }

    @Test
    public void Given_nameBook_When_validateQuantity_and_exists_in_listBooks_Then_return_True(){
        Book newBook = new Book("Dracula", 1897, "Bram Stoker", false, CategoryBook.SOFT_COVER);
        bookDao.registerBook(newBook);
        int quantity = bookDao.validateQuantity(newBook.getName());
        assertEquals(1, quantity);
    }

    @Test
    public void Given_nameBook_When_validateQuantity_and_not_exists_in_listBooks_Then_throw_IllegalArgumentException(){
        String nameBook = "Orgullo y prejuicio";
        assertThrows(IllegalArgumentException.class,()->{
            bookDao.validateQuantity(nameBook);});
    }

    @Test
    public void Given_authorName_When_searchBookByAutor_and_exists_Then_find_book_and_return_True(){
        Book newBook = new Book("Dracula", 1897, "Bram Stoker", false, CategoryBook.SOFT_COVER);
        bookDao.registerBook(newBook);
        ArrayList book = bookDao.searchbBookByAutor(newBook.getAuthor());
        boolean bookExist = false;
        if(book.size() > 0){
            bookExist = true;
        }
        assertTrue(bookExist);
    }

    @Test
    public void Given_authorName_When_searchBookByAutor_and_not_exists_Then_return_True(){
        String authorName ="Jane Austen";
        ArrayList book = bookDao.searchbBookByAutor(authorName);
        boolean bookNotExist = false;
        if(book.size() == 0){
            bookNotExist = true;
        }
        assertTrue(bookNotExist);
    }

    @Test
    public void Given_allBooks_When_eliminateQuantity_and_eliminated_Then_return_False(){
        boolean quantityMoreThan0 = true;
        Book newBook = new Book("Dracula", 1897, "Bram Stoker", false, CategoryBook.SOFT_COVER);
        bookDao.registerBook(newBook);
        bookDao.eliminateQuantity();
        int quantity = bookDao.validateQuantity(newBook.getName());
        if(quantity == 0){
            quantityMoreThan0 = false;
        }
        assertFalse(quantityMoreThan0);

    }
}

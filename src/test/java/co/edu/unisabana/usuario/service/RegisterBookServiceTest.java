package co.edu.unisabana.usuario.service;


import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.LookUp;
import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class RegisterBookServiceTest
{

    @InjectMocks
    private RegisterBookLibrary service;

    @Mock
    private AddBookPort addBookPort;
    private RegisterBookPort registerBookPort;
    private SearchBookPort searchBookPort;


    @Test
    public void Given_nameExists_When_RegisterBook_Then_addBook()
    {
        Book book = new Book("Alicia en el Pais ", 1999, "Julio Berne", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);
        int exists =service.registerBook(book);
        Mockito.verify(searchBookPort).validateExistsBook(book.getName());
        assertEquals(1,exists);
    }

    public void Given_nameNotExists_When_RegisterBook_Then_registerBook() {
        Book book = new Book("Alicia en el Pais ", 1999, "Julio Berne", false, CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(false);
        int exists =service.registerBook(book);
        Mockito.verify(searchBookPort).validateExistsBook(book.getName());
        assertEquals(2,exists);
    }

    @Test
    public void Given_SendCorrectIinformantion_When_AddBook_Then_returnFalse()
    {
        Book book = new Book("Alicia en el Pais ", 1999, "Julio Berne", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);
        int exists = service.registerBook(book);
        Mockito.verify(searchBookPort).validateExistsBook(book.getName());
    }

    @Test
    public void Given_SendCorrectIinformantion_When_RegisterBook_Then_returnFalse()
    {
        Book book = new Book("Alicia en el Pais ", 1999, "Julio Berne", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateExistsBook(book.getName())).thenReturn(true);
        int exists = service.registerBook(book);
        Mockito.verify(searchBookPort).validateExistsBook(book.getName());
    
    }
    /* 
    @Test
    public void Give_dontSendCorrectIinformantion_When_AddBook_Then_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addBook(new Book());
        });
    }
   
    @Test
    public void Give_dontSendCorrectIinformantion_When_RegisterBook_Then_throwIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            service.registerBook(new Book());
        });
    }
    */
    @Test
    public void Given_Book_When_RegisterBook_Then_ValidateQuantityAlowed()
    {
        Book book = new Book("Hush Hush ", 2009, "Becca Fitzpatrick", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateQuantity(book.getName())).thenReturn(1);
        int qty =service.registerBook(book);
        Mockito.verify(searchBookPort).validateQuantity(book.getName());
        assertEquals(1,qty);
    }

    @Test
    public void Given_Book_When_RegisterBook_Then_QuantityIsNotAllowed()
    {
        Book book = new Book("La chica del tren ", 2015, "Paula Hawkins", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateQuantity(book.getName())).thenReturn(3);
        service.registerBook(book);
        service.registerBook(book);
        int qty =service.registerBook(book);
        Mockito.verify(searchBookPort).validateQuantity(book.getName());
        assertEquals(3,qty);
    }
    /* 
    @Test
    public void Given_AuthorName_When_SearchAuthorBooks_Then_FindAuthorBooks()
    {
        BookEntity book = new BookEntity("La chica del tren ", 2015, "Paula Hawkins", false, "suave", 1, 2);
        LookUp author =new LookUp (searchBookPort, registerBookPort);
        Mockito.when(author.searchbBookByAutor("Paula Hawkins"));
        ArrayList <BookEntity> authorName =service.registerBook("Paula Hawkins");
        Mockito.verify(searchBookPort).searchbBookByAutor(book.getAuthor());
        assertEquals(1, authorName.size());
    }
    
    @Test
    public void Given_Book_When_EliminateQuantity_Then_DeleteBooks()
    {
        Mockito.when(eliminateQuantity());
        Mockito.verify(searchBookPort).eliminateQuantity(book.getQuantity());
        assertEquals(0);
    }
    */
}

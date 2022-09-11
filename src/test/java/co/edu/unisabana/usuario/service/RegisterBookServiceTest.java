package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.service.library.RegisterBookLibrary;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RegisterBookServiceTest
{

    @InjectMocks
    private RegisterBookLibrary service;

    @Mock
    private AddBookPort addBookPort;
    private RegisterBookPort registerBookPort;


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
        Book book = new Book();
        book.setName("Alicia en el País de las Maravillas");
        Mockito.when(addBookPort.validateExistsBook(book.getName)).thenReturn(true);
        int exists = service.addBook(book);
        Mockito.verify(addBookPort).validateExistsBook(book.getName);
        // assertTrue(result);
    }

    @Test
    public void Given_SendCorrectIinformantion_When_RegisterBook_Then_returnFalse()
    {
        Book book = new Book();
        book.setName("Alicia en el País de las Maravillas");
        Mockito.when(registerBookPort.validateExistsBook(book.getName)).thenReturn(true);
        int exists = service.registerBook(book);
        Mockito.verify(registerBookPort).validateExistsBook(book.getName);
        // assertTrue(result);
    }

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

    @Test
    public void Given_Book_When_RegisterBook_Then_ValidateQuantityAlowed()
    {
        Book book = new Book("Hush Hush ", 2009, "Becca Fitzpatrick", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateQuantity(book.getName())).thenReturn(true);
        int qty =service.registerBook(book);
        Mockito.verify(searchBookPort).validateQuantity(book.getName());
        assertEquals(1,qty);
    }

    @Test
    public void Given_Book_When_RegisterBook_Then_QuantityIsNotAllowed()
    {
        Book book = new Book("La chica del tren ", 2015, "Paula Hawkins", false,CategoryBook.SOFT_COVER);
        Mockito.when(searchBookPort.validateQuantity(book.getName())).thenReturn(false);
        int qty =service.registerBook(book);
        Mockito.verify(searchBookPort).validateQuantity(book.getName());
        assertEquals(3,qty);
    }

    @Test
    public void Given_AuthorName_When_SearchAuthorBooks_Then_FindAuthorBooks()
    {
        LookUp author =new LookUp ("Paula Hawkins");
        Mockito.when(searchBookPort.searchbBookByAutor(Author));
        String authorName =service.registerBook(Author);
        Mockito.verify(searchBookPort).searchbBookByAutor(book.getAuthor());
        assertEquals("La chica del tren");
    }

    @Test
    public void Given_Book_When_EliminateQuantity_Then_DeleteBooks()
    {
        Mockito.when(eliminateQuantity());
        Mockito.verify(searchBookPort).eliminateQuantity(book.getQuantity());
        assertEquals(0);
    }

}

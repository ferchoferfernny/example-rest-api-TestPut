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

}

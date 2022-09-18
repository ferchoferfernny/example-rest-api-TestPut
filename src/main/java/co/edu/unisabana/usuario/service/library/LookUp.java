package co.edu.unisabana.usuario.service.library;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;



@Service
public class LookUp {

    private final SearchBookPort searchBookPort;
    private final RegisterBookPort registerBookPort;
    

    public LookUp (SearchBookPort searchBookPort, RegisterBookPort registerBookPort) {
        this.searchBookPort = searchBookPort;
        this.registerBookPort = registerBookPort;
    }


    public ArrayList <BookEntity> searchAutorBooks (String Autor)
    {
        return searchBookPort.searchbBookByAutor(Autor);
    }

}
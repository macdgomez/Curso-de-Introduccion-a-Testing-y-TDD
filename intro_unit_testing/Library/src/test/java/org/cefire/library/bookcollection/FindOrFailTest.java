package org.cefire.library.bookcollection;

import org.cefire.library.Book;
import org.cefire.library.BookCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindOrFailTest {
    @Test
    public void shouldGetExceptionWhenUsingFailWithANonExistintEntryV2(){
        final String titleToLocate = "un titulo inexistente";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        Assertions.assertThrows(BookCollection.ExpectedToFindAtLeastABook.class, ()->books.findOrFail(titleToLocate));


    }

    @Test
    public void shouldGetExceptionWhenUsingFailWithANonExistintEntry(){
        final String titleToLocate = "un titulo inexistente";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });
        try{
            books.findOrFail(titleToLocate);
            Assertions.fail("Se esperaba una excepción");
        }
        catch (Exception e){
            Assertions.assertInstanceOf(BookCollection.ExpectedToFindAtLeastABook.class,e);
        }

    }
}

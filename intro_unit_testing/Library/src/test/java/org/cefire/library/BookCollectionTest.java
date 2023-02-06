package org.cefire.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookCollectionTest {
    @Test

    public void shouldFindABookByISBN(){
        final String isbnToLocate = "un-isbn-2";

        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        List<Book> foundBooks = books.find(isbnToLocate);
        Assertions.assertFalse(foundBooks::isEmpty);
        foundBooks.forEach((Book book)->Assertions.assertEquals(isbnToLocate,book.getISBN()));

    }
    @Test
    public void shouldGetAnEmptyListIfNoMatchesAreFound(){
        final String isbnToLocate = "un-isbn-que-no-existe";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });
        Assertions.assertTrue(books.find(isbnToLocate).isEmpty());
    }

    @Test
    public void shouldNotFindABookByTitle(){
        final String titleToLocate = "un titulo inexistente";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        Assertions.assertTrue(books.find(titleToLocate).isEmpty());
    }

    @Test
    public void shouldFindABookByTitle(){
        final String titleToLocate = "un titulo 3";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        List<Book> foundBooks = books.find(titleToLocate);
        Assertions.assertFalse(foundBooks::isEmpty);
        foundBooks.forEach((Book book)->Assertions.assertTrue(book.getTitle().contains(titleToLocate)));
    }
    
    @Test
    public void bookCollectionEmpty(){

        final String titleToLocate = "un titulo inexistente en BookCollection vacía";
        BookCollection books = new BookCollection(new Book[]{});
        Assertions.assertTrue(books.find(titleToLocate).isEmpty());
    }

}

package org.cefire.library.bookcollection;

import org.cefire.library.Book;
import org.cefire.library.BookCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindTest {
    @Test
    public void shouldFindABookByISBN(){
        // Estructurando los métodos
        // AAA Arrange Act Assert
        // 1 - Arrange (preparación)
        final String isbnToLocate = "un-isbn-2";

        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        // 2 - Act (prueba caso de uso)
        List<Book> foundBooks = books.find(isbnToLocate);

        // 3 - Assert (comprobación)
        assertThat(foundBooks.isEmpty(), is(false) );
        foundBooks.forEach((Book book)->assertThat(isbnToLocate, is(equalTo(book.getISBN()))));

    }
    @Test
    public void shouldGetAnEmptyListIfNoMatchesAreFound(){
        final String isbnToLocate = "un-isbn-que-no-existe";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un titulo 1", "un autor 1"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });
        // JUnit
        // Assertions.assertTrue(books.find(isbnToLocate).isEmpty());

        // Con HamCrest
        boolean emptyBooks = books.find(isbnToLocate).isEmpty();

        assertThat(emptyBooks,is(true));
    }

    @Test
    public void bookCollectionEmpty(){

        // Arrange
        final String titleToLocate = "un titulo inexistente en BookCollection vacía";
        BookCollection books = new BookCollection(new Book[]{});

        // Act
        boolean emptyBook = books.find(titleToLocate).isEmpty();

        // Assert
        Assertions.assertTrue(emptyBook);
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
        assertFalse(foundBooks::isEmpty);
        foundBooks.forEach((Book book)->Assertions.assertTrue(book.getTitle().contains(titleToLocate)));
    }
}

package org.cefire.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

        final String titleToLocate = "un titulo inexistente en BookCollection vac??a";
        BookCollection books = new BookCollection(new Book[]{});
        Assertions.assertTrue(books.find(titleToLocate).isEmpty());
    }

    /**
     * Falla si no se implementa el m??todo equals en la clase Book
     * El m??todo toString de la lase Book ayuda a identificar un objeto a trav??s de texto en luagr
     * de su direcci??n de memoria
     */
    @Test
    public void shouldFindCopiesAOfBook(){
        final Book bookToBeFound = new Book("libro-que-debe-encontrase","un t??tulo 1", "un autor");

        BookCollection books = new BookCollection(new Book[]{
                new Book("libro-que-debe-encontrase","un t??tulo 1", "un autor"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("libro-que-debe-encontrase","un t??tulo 1", "un autor"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        List<Book> foundBooks = books.findCopies(bookToBeFound);

        Assertions.assertFalse(foundBooks.isEmpty());

        foundBooks.forEach((Book book)->Assertions.assertEquals(book,bookToBeFound));

    }

    /**
     * Es necesario Assertions.fail() si no se lanza la excepci??n
     */
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
            Assertions.fail("Se esperaba una excepci??n");
        }
        catch (Exception e){
            Assertions.assertInstanceOf(BookCollection.ExpectedToFindAtLeastABook.class,e);
        }

    }

    /**
     * Similar al anterior pero utilizando el m??todo assertThrows, que evita utilizar el m??todo fail
     */

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



}

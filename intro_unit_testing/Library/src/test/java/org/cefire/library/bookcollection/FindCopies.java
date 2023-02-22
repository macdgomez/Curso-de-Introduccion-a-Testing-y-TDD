package org.cefire.library.bookcollection;

import org.cefire.library.Book;
import org.cefire.library.BookCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCopies {
    @Test
    public void shouldFindCopiesAOfBook(){
        final Book bookToBeFound = new Book("libro-que-debe-encontrase","un título 1", "un autor");

        BookCollection books = new BookCollection(new Book[]{
                new Book("libro-que-debe-encontrase","un título 1", "un autor"),
                new Book("un-isbn-2","un titulo 2", "un autor 2"),
                new Book("libro-que-debe-encontrase","un título 1", "un autor"),
                new Book("un-isbn-3","un titulo 3", "un autor 3")
        });

        List<Book> foundBooks = books.findCopies(bookToBeFound);

        assertFalse(foundBooks.isEmpty());

        foundBooks.forEach((Book book)-> Assertions.assertEquals(book,bookToBeFound));

    }
}

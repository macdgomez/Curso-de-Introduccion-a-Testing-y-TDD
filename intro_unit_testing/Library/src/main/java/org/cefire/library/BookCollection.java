package org.cefire.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BookCollection {
    private  final List<Book> books;

    public BookCollection(Book[] books) {
        this.books = Arrays.asList(books);
    }

    public List<Book> find(String textToMatch){
        List<Book> foundBooks = new ArrayList<Book>();

        for (Book book: this.books){
            if(book.getISBN().equals(textToMatch) || book.getTitle().contains(textToMatch)){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findCopies(Book bookToBeFound){
        List<Book> foundBooks = new ArrayList<>();

        for(Book book: this.books){
            if(book.getISBN().equals(bookToBeFound.getISBN())
                && book.getTitle().equals(bookToBeFound.getTitle())
                && book.getAuthor().equals(bookToBeFound.getAuthor())){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> findOrFail(String textToFind){
        List<Book> foundBooks = new ArrayList<>();

        for(Book book: this.books){
            if(book.getISBN().equals(textToFind)||
                    book.getAuthor().equals(textToFind)||
                    book.getTitle().equals(textToFind)){
                foundBooks.add(book);
            }
        }
        if(foundBooks.isEmpty())
            throw new ExpectedToFindAtLeastABook();

        return foundBooks;
    }

    public static class ExpectedToFindAtLeastABook extends RuntimeException{
    }
}
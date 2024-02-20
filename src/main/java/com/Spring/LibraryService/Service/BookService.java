package com.Spring.LibraryService.Service;

import com.Spring.LibraryService.Model.Book;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class BookService {

    private Map<Long,Book> bookMap = new HashMap<>();

    public Book addBook(Book book){

        // call the database
        Long bookId = new Random().nextLong();
        book.setBookId(bookId);
        bookMap.put(bookId, book);
        return  book;
    }
    public Book getBook(Long bookId) {
        return bookMap.get(bookId);

    }
    public Book updateBook(Long bookId,Book book){
        book.setBookId(bookId);
        bookMap.put(bookId,book);
        return book;
    }

}
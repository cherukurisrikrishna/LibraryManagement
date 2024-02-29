package com.Spring.LibraryService.Controller;
import com.Spring.LibraryService.entity.LibraryBook;
//import com.Spring.LibraryService.model.Book;
import com.Spring.LibraryService.Model.Book;
import com.Spring.LibraryService.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public Book getBook(Integer bookId){

        return bookService.getBook(bookId);
    }

    // create a book
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
        // Set the memberId for the member object to be updated
        return bookService.updateBook(book);
    }

    @DeleteMapping
    public void deleteBook(Integer bookId){
        bookService.deleteBook(bookId);
    }
}
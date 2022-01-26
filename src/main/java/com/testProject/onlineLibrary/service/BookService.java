package com.testProject.onlineLibrary.service;

import com.testProject.onlineLibrary.domain.Book;
import com.testProject.onlineLibrary.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return (List<Book>)bookRepo.findAll();
    }

    public Book getBookById(Long bookId){
        //todo:?? Значение по умолчанию?
        return bookRepo.findById(bookId).orElse(null);
    }

    public void saveBookInDatabase(Book book){
        //todo: Добавить проверку на уникальность
        bookRepo.save(book);
    }

    public void deleteBookById(Long bookId){
        bookRepo.deleteById(bookId);
    }
}

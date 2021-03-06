package com.testProject.onlineLibrary.service;

import com.testProject.onlineLibrary.domain.Book;
import com.testProject.onlineLibrary.domain.Genre;
import com.testProject.onlineLibrary.repo.BookRepo;
import com.testProject.onlineLibrary.repo.GenreRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final GenreRepo genreRepo;

    public BookService(BookRepo bookRepo, GenreRepo genreRepo){

        this.bookRepo = bookRepo;
        this.genreRepo = genreRepo;
    }

    public List<Book> getAllBooks() {
        return (List<Book>)bookRepo.findAll();
    }

    public List<Genre> getAllGenres(){
        return (List<Genre>)genreRepo.findAll();
    }

    public Book getBookById(Long bookId){
        //todo:?? Значение по умолчанию?
        return bookRepo.findById(bookId).orElse(null);
    }

    public List<Book> getBooksByGenre(Long genreId){
        return bookRepo.findAllByGenre_id(genreId);
    }

    public List<Book> searchBooks(String keyword){
        List<Book> booksByName = bookRepo.findAllByNameIgnoreCaseContaining(keyword);
        List<Book> booksByWriter = bookRepo.findAllByWriterIgnoreCaseContaining(keyword);
        Set<Book> set = new LinkedHashSet<>(booksByName);
        set.addAll(booksByWriter);
        return new ArrayList<>(set);
    }

    public Genre getGenreById(Long genreId){
        //todo:?? Значение по умолчанию?
        return genreRepo.findById(genreId).orElse(null);
    }

    public void saveBookInDatabase(Book book){
        //todo: Добавить проверку на уникальность
        bookRepo.save(book);
    }

    public void deleteBookById(Long bookId){
        bookRepo.deleteById(bookId);
    }
}

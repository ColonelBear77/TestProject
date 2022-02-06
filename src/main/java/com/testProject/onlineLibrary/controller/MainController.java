package com.testProject.onlineLibrary.controller;

import com.testProject.onlineLibrary.domain.Book;
import com.testProject.onlineLibrary.domain.Genre;
import com.testProject.onlineLibrary.domain.User;
import com.testProject.onlineLibrary.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final BookService bookService;

    public MainController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String main(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        List<Genre> genres = bookService.getAllGenres();
        model.addAttribute("genres", genres);

        return "main";
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(name="genre", required=false, defaultValue= "-1") Long genre, @RequestParam("keyword") String keyword){

        ModelAndView mav = new ModelAndView("main");

        if(genre != -1){
            List<Book> booksByGenre = bookService.getBooksByGenre(genre);
            List<Book> booksByKeyword = bookService.searchBooks(keyword);
            List<Book> books = new ArrayList<>(booksByGenre.stream()
                    .distinct()
                    .filter(booksByKeyword::contains)
                    .collect(Collectors.toSet()));
            mav.addObject("books", books);
        } else if(!keyword.isEmpty()){
            List<Book> books = bookService.searchBooks(keyword);
            mav.addObject("books", books);
        } else{
            List<Book> books = bookService.getAllBooks();
            mav.addObject("books", books);
        }

        List<Genre> genres = bookService.getAllGenres();
        mav.addObject("genres", genres);

        return mav;
    }

    @GetMapping("/newbook")
    public String newBook(Model model){
        List<Genre> genres = bookService.getAllGenres();
        model.addAttribute("genres", genres);

        return "newbook";
    }

    @PostMapping("/newbook")
    public String addBook(@AuthenticationPrincipal User user,
                          @RequestParam("name") String name,
                          @RequestParam("writer") String writer,
                          @RequestParam("genre") Long genre,
                          @RequestParam("description") String description){

        Genre genreObject = bookService.getGenreById(genre);

        Book newBook = new Book(name, writer, genreObject, description, user);

        bookService.saveBookInDatabase(newBook);

        return "redirect:/";
    }

    @GetMapping("/editbook")
    public String editBook(@RequestParam(name="bookId") Long bookId,
                            Model model){

        Book editableBook = bookService.getBookById(bookId);
        model.addAttribute("book", editableBook);

        List<Genre> genres = bookService.getAllGenres();
        model.addAttribute("genres", genres);
        return "editbook";
    }

    @PostMapping("/editbook")
    public String saveEditedBook(@RequestParam("id") Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("writer") String writer,
                                 @RequestParam("genre") Long genre,
                                 @RequestParam("description") String description){

        Genre genreObject = bookService.getGenreById(genre);

        //todo: Скорее всего можно забрать модель сразу со страницы
        Book editedBook = bookService.getBookById(id);
        editedBook.setName(name);
        editedBook.setWriter(writer);
        editedBook.setGenre(genreObject);
        editedBook.setDescription(description);

        bookService.saveBookInDatabase(editedBook);

        return "redirect:/";
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable (value = "bookId") Long bookId){
        if(bookId != null){
            bookService.deleteBookById(bookId);
        }

        return "redirect:/";
    }
}

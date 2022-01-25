package com.testProject.onlineLibrary.controller;

import com.testProject.onlineLibrary.domain.Book;
import com.testProject.onlineLibrary.domain.User;
import com.testProject.onlineLibrary.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return "main";
    }

    @GetMapping("/newbook")
    public String newBook(Model model){
        return "newbook";
    }

    @PostMapping("/newbook")
    public String addBook(@AuthenticationPrincipal User user,
                          @RequestParam("name") String name,
                          @RequestParam("writer") String writer,
                          @RequestParam("description") String description){
        Book newBook = new Book(name, writer, description, user);

        bookService.saveBookInDatabase(newBook);

        return "redirect:/";
    }
}

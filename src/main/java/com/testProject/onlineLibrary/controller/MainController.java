package com.testProject.onlineLibrary.controller;

import com.testProject.onlineLibrary.domain.Book;
import com.testProject.onlineLibrary.domain.User;
import com.testProject.onlineLibrary.repo.BookRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final BookRepo bookRepo;

    public MainController(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Book> books = bookRepo.findAll();
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
        bookRepo.save(newBook);
        //todo: Добавить проверку на уникальность

        return "redirect:/";
    }
}

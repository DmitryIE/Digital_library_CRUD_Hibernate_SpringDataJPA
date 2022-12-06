package ru.egerev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egerev.springcourse.models.Book;
import ru.egerev.springcourse.models.LibraryReader;
import ru.egerev.springcourse.services.BookService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("book", bookService.index());
        return "book/index";
    }

    @GetMapping(params = {"page", "books_per_page"})
    public String index(@RequestParam("page") int page, @RequestParam("books_per_page") int booksPerPage, Model model) {
        model.addAttribute("book", bookService.index(page, booksPerPage));
        return "book/index";
    }

    @GetMapping(params = {"sort_by_year"})
    public String index(@RequestParam("sort_by_year") String sort, Model model) {
        if (sort.equals("true")) {
            model.addAttribute("book", bookService.indexSorted());
        } else {
            model.addAttribute("book", bookService.index());
        }
        return "book/index";
    }

    @GetMapping(params = {"page", "books_per_page", "sort_by_year"})
    public String index(@RequestParam("page") int page,
                        @RequestParam("books_per_page") int booksPerPage,
                        @RequestParam("sort_by_year") String sort, Model model) {
        model.addAttribute("book", bookService.indexSorted(page, booksPerPage));
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("libraryReader") LibraryReader libraryReader) {
        model.addAttribute("book", bookService.show(id));
        model.addAttribute("libraryReaders", bookService.allReaders());
        return "book/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";

        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";
        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}/free")
    public String free(@PathVariable("id") int id) {
        bookService.freeBook(id);
        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/take")
    public String take(@PathVariable("id") int id, @ModelAttribute("libraryReader") LibraryReader libraryReader) {
        bookService.takeBook(id, libraryReader);
        return "redirect:/book/{id}";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "text", required = false) String text, Model model) {
        if (text != null) {
            model.addAttribute("books", bookService.search(text));
            List<Book> list = bookService.search(text);
        }
        return "/book/search";
    }

}

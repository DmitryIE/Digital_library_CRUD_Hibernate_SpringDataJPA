package ru.egerev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egerev.springcourse.models.LibraryReader;
import ru.egerev.springcourse.services.LibraryReaderService;
import ru.egerev.springcourse.util.LibraryReaderValidator;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/library_reader")
public class LibraryReaderController {

    private final LibraryReaderService libraryReaderService;
    private final LibraryReaderValidator libraryReaderValidator;

    @Autowired
    public LibraryReaderController (LibraryReaderService libraryReaderService, LibraryReaderValidator libraryReaderValidator) {
        this.libraryReaderService = libraryReaderService;
        this.libraryReaderValidator = libraryReaderValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("libraryReader", libraryReaderService.index());
        return "library_reader/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("libraryReader", libraryReaderService.show(id));
        model.addAttribute("listOfBooks", libraryReaderService.takenBooks(id));
        System.out.println(libraryReaderService.takenBooks(id));
        return "library_reader/show";
    }

    @GetMapping("/new")
    public String newLibraryReader(@ModelAttribute("libraryReader") LibraryReader libraryReader) {
        return "library_reader/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("libraryReader") @Valid LibraryReader libraryReader,
                         BindingResult bindingResult) {
        libraryReaderValidator.validate(libraryReader, bindingResult);
        if (bindingResult.hasErrors())
            return "library_reader/new";

        libraryReaderService.save(libraryReader);
        return "redirect:/library_reader";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("libraryReader", libraryReaderService.show(id));
        return "library_reader/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("libraryReader") @Valid LibraryReader libraryReader,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "library_reader/edit";
        libraryReaderService.update(id, libraryReader);
        return "redirect:/library_reader";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        libraryReaderService.delete(id);
        return "redirect:/library_reader";
    }

}

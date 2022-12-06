package ru.egerev.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egerev.springcourse.models.LibraryReader;
import ru.egerev.springcourse.services.LibraryReaderService;

@Component
public class LibraryReaderValidator implements Validator {

    private final LibraryReaderService libraryReaderService;

    @Autowired
    public LibraryReaderValidator(LibraryReaderService libraryReaderService) {
        this.libraryReaderService = libraryReaderService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LibraryReader.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LibraryReader libraryReader = (LibraryReader) o;
        if (libraryReaderService.show(libraryReader.getName()).isPresent()) {
            errors.rejectValue("name", "", "Эта ФИО уже используется");
        }
    }
}

package ru.egerev.springcourse.services;

import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egerev.springcourse.models.Book;
import ru.egerev.springcourse.models.LibraryReader;
import ru.egerev.springcourse.repositories.LibraryReadersRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
public class LibraryReaderService {

    private LibraryReadersRepository libraryReadersRepository;

    static int maxDaysOfUsingBook = 10;


    @Autowired
    public LibraryReaderService(LibraryReadersRepository libraryReadersRepository) {
        this.libraryReadersRepository = libraryReadersRepository;
    }

    public List<LibraryReader> index() {
        return libraryReadersRepository.findAll();
    }

    public LibraryReader show(int id) {
        return libraryReadersRepository.findById(id).orElse(null);
    }

    public Optional<LibraryReader> show(String name) {
        return libraryReadersRepository.findByName(name).stream().findAny();
    }

    @Transactional
    public void save(LibraryReader libraryReader) {
        libraryReadersRepository.save(libraryReader);
    }

    @Transactional
    public void update(int id, LibraryReader updateLibraryReader) {
        updateLibraryReader.setId(id);
        libraryReadersRepository.save(updateLibraryReader);
    }

    @Transactional
    public void delete(int id) {
        libraryReadersRepository.deleteById(id);
    }

    public Map<Book, Boolean> takenBooks(int id) {
        Hibernate.initialize(libraryReadersRepository.findById(id).get().getBooks());
        List<Book> takenBooks = libraryReadersRepository.findById(id).get().getBooks();
        Map<Book, Boolean> takenBooksMap = new HashMap<>();
        for (Book book : takenBooks) {
            takenBooksMap.put(book, isBookOverdue(book));
        }
        return takenBooksMap;
    }

    public boolean isBookOverdue(Book book) {
        long currentDate = new Date().getTime();
        long dateOfTakenBook = book.getDateOfTakenBook().getTime();
        long daysOfUse = TimeUnit.MILLISECONDS.toDays(currentDate - dateOfTakenBook);
        return daysOfUse > maxDaysOfUsingBook;
    }

}
